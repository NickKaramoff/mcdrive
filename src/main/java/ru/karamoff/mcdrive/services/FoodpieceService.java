package ru.karamoff.mcdrive.services;

import ru.karamoff.mcdrive.models.Foodpiece;

import java.util.List;

public interface FoodpieceService {
    List<Foodpiece> getAllFoodpieces();
    void updateAvailability(Long foodpieceId);

    void removeFoodpiece(Long foodpieceId);
}
