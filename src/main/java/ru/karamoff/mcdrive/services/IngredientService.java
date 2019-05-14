package ru.karamoff.mcdrive.services;

import ru.karamoff.mcdrive.forms.IngredientForm;
import ru.karamoff.mcdrive.models.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getAllIngredients();
    Ingredient getIngredient(Long ingredientId);
    void saveIngredient(IngredientForm form);
    void toggleAvailability(Long ingredientId);
    void removeIngredient(Long ingredientId);
    void updateAvailabilityForParents(Long ingredientId);
}
