package ru.karamoff.mcdrive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karamoff.mcdrive.forms.FoodpieceForm;
import ru.karamoff.mcdrive.models.Foodpiece;
import ru.karamoff.mcdrive.models.Ingredient;
import ru.karamoff.mcdrive.models.IngredientInFoodpiece;
import ru.karamoff.mcdrive.repositories.FoodpieceRepository;
import ru.karamoff.mcdrive.repositories.IngredientInFoodpieceRepository;

import java.util.List;

@Service
public class FoodpieceServiceImpl implements FoodpieceService {

    @Autowired
    private FoodpieceRepository foodpieceRepository;

    @Autowired
    private IngredientInFoodpieceRepository ingredientInFoodpieceRepository;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public List<Foodpiece> getAllFoodpieces() {
        return foodpieceRepository.findAllByOrderByIdAsc();
    }

    @Override
    public void updateAvailability(Long foodpieceId) {
        Foodpiece foodpiece = foodpieceRepository.getOne(foodpieceId);
        boolean available = true;
        for (IngredientInFoodpiece iif : foodpiece.getIngredients()) {
            if (!iif.getIngredient().getAvailable()) {
                available = false;
                break;
            }
        }
        foodpiece.setAvailable(available);
        foodpieceRepository.save(foodpiece);
    }

    @Override
    public void createFoodpiece(FoodpieceForm foodpieceForm) {
        Foodpiece foodpiece = Foodpiece.builder()
                .name(foodpieceForm.getName())
                .cost(foodpieceForm.getCost())
                .build();
        final Foodpiece finalFoodpiece = foodpieceRepository.save(foodpiece);
        final boolean[] available = {true};

        foodpieceForm.getIngredients()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() != 0)
                .forEach(entry -> {
                    Ingredient in = ingredientService.getIngredient(entry.getKey());
                    if (!in.getAvailable()) {
                        available[0] = false;
                    }
                    IngredientInFoodpiece iif = IngredientInFoodpiece.builder()
                            .ingredient(in)
                            .foodpiece(finalFoodpiece)
                            .amount(entry.getValue())
                            .build();
                    ingredientInFoodpieceRepository.save(iif);
                });
        ingredientInFoodpieceRepository.flush();
        finalFoodpiece.setAvailable(available[0]);
        foodpieceRepository.save(finalFoodpiece);
    }

    @Override
    public void removeFoodpiece(Long foodpieceId) {
        foodpieceRepository.deleteById(foodpieceId);
    }

    @Override
    public Foodpiece getFoodpiece(Long foodpieceId) {
        return foodpieceRepository.getOne(foodpieceId);
    }

    @Override
    public List<Foodpiece> getAvailableFoodpieces() {
        return foodpieceRepository.findAllByAvailableIsTrueOrderByIdAsc();
    }
}
