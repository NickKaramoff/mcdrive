package ru.karamoff.mcdrive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karamoff.mcdrive.forms.IngredientForm;
import ru.karamoff.mcdrive.models.Ingredient;
import ru.karamoff.mcdrive.models.IngredientInFoodpiece;
import ru.karamoff.mcdrive.repositories.IngredientInFoodpieceRepository;
import ru.karamoff.mcdrive.repositories.IngredientRepository;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientInFoodpieceRepository ingredientInFoodpieceRepository;

    @Autowired
    private FoodpieceService foodpieceService;

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAllByOrderByAvailableDescIdAsc();
    }

    @Override
    public List<Ingredient> getAvailableIngredients() {
        return ingredientRepository.findAllByAvailableIsTrueOrderByIdAsc();
    }

    @Override
    public void saveIngredient(IngredientForm form) {
        Ingredient ingredient = Ingredient.builder()
                .id(form.getId())
                .name(form.getName())
                .available(form.getAvailable())
                .build();
        ingredient = ingredientRepository.save(ingredient);
        updateAvailabilityForParents(ingredient.getId());
    }


    @Override
    public void updateAvailabilityForParents(Long ingredientId) {
        List<IngredientInFoodpiece> ingredientInFoodpieces = ingredientInFoodpieceRepository.findAllByIngredient_Id(ingredientId);
        for (IngredientInFoodpiece iif : ingredientInFoodpieces) {
            foodpieceService.updateAvailability(iif.getFoodpiece().getId());
        }
    }
}
