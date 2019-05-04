package ru.karamoff.mcdrive.services;

import ru.karamoff.mcdrive.forms.IngredientForm;
import ru.karamoff.mcdrive.models.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getAllIngredients();
    List<Ingredient> getAvailableIngredients();
    void saveIngredient(IngredientForm form);
    void toggleVisibility(IngredientForm form);
    void updateAvailabilityForParents(Long ingredientId);
}
