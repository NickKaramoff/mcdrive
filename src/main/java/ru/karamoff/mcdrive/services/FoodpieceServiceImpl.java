package ru.karamoff.mcdrive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karamoff.mcdrive.models.Foodpiece;
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
}
