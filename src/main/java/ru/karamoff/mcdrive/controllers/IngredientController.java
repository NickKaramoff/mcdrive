package ru.karamoff.mcdrive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.karamoff.mcdrive.forms.IngredientForm;
import ru.karamoff.mcdrive.models.Ingredient;
import ru.karamoff.mcdrive.services.IngredientService;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    /**
     * Получение списка всех ингредиентов в системе в формате JSON
     */
    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<List<Ingredient>> ingredientsList() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/available/json")
    @ResponseBody
    public ResponseEntity<List<Ingredient>> availableIngredientsList() {
        return ResponseEntity.ok(ingredientService.getAvailableIngredients());
    }

    @PostMapping("/save")
    public String saveIngredient(IngredientForm ingredientForm) {
        ingredientService.saveIngredient(ingredientForm);
        return "redirect:/";
    }
}
