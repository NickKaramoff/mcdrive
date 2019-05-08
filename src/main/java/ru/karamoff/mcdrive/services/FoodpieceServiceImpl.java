package ru.karamoff.mcdrive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karamoff.mcdrive.models.Foodpiece;
import ru.karamoff.mcdrive.models.IngredientInFoodpiece;
import ru.karamoff.mcdrive.repositories.FoodpieceRepository;

import java.util.List;

@Service
public class FoodpieceServiceImpl implements FoodpieceService {

    @Autowired
    private FoodpieceRepository foodpieceRepository;

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
    public void removeFoodpiece(Long foodpieceId) {
        foodpieceRepository.deleteById(foodpieceId);
    }
}
