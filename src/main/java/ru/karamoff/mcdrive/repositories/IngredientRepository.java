package ru.karamoff.mcdrive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karamoff.mcdrive.models.Ingredient;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByOrderByIdAsc();
    List<Ingredient> findAllByAvailableIsTrueOrderByIdAsc();
}
