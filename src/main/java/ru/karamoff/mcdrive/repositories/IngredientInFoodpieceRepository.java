package ru.karamoff.mcdrive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karamoff.mcdrive.models.IngredientInFoodpiece;

import java.util.List;

public interface IngredientInFoodpieceRepository extends JpaRepository<IngredientInFoodpiece, Long> {
    List<IngredientInFoodpiece> findAllByIngredient_Id(Long ingredientId);
}
