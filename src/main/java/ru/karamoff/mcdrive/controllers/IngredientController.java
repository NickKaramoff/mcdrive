package ru.karamoff.mcdrive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.karamoff.mcdrive.forms.IdForm;
import ru.karamoff.mcdrive.forms.IngredientForm;
import ru.karamoff.mcdrive.models.Ingredient;
import ru.karamoff.mcdrive.services.IngredientService;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String ingredientsListPage(ModelMap map) {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        map.addAttribute("ingredients", ingredients);
        return "ingredients_list";
    }

    @GetMapping("/new")
    public String ingredientAddPage() {
        return "ingredient_new";
    }

    @PostMapping("/new")
    public String ingredientAddForm(IngredientForm form) {
        ingredientService.saveIngredient(form);
        return "redirect:/ingredients";
    }

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
        return "redirect:/ingredients";
    }

    @PostMapping(value = "/toggle", consumes = "application/json")
    public ResponseEntity<String> toggleAvailability(@RequestBody IdForm form) {
        ingredientService.toggleVisibility(form.getId());
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping(value = "/remove", consumes = "application/json")
    public ResponseEntity<String> removeIngredient(@RequestBody IdForm form) {
        ingredientService.removeIngredient(form.getId());
        return ResponseEntity.ok("OK");
    }
}
