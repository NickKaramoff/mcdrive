package ru.karamoff.mcdrive.services;

import ru.karamoff.mcdrive.forms.FoodpieceForm;
import ru.karamoff.mcdrive.models.Foodpiece;

import java.util.List;

public interface FoodpieceService {
    void createFoodpiece(FoodpieceForm foodpieceForm);
    void updateAvailability(Long foodpieceId);
    void removeFoodpiece(Long foodpieceId);

    Foodpiece getFoodpiece(Long foodpieceId);

    List<Foodpiece> getAvailableFoodpieces();
    List<Foodpiece> getAllFoodpieces();
}
