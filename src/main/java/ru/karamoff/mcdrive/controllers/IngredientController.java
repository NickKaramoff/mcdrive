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
    public String newIngredientPage() {
        return "ingredient_new";
    }

    @PostMapping("/new")
    public String createIngredient(IngredientForm form) {
        ingredientService.saveIngredient(form);
        return "redirect:/ingredients";
    }

    @PostMapping(value = "/toggle", consumes = "application/json")
    public ResponseEntity<String> toggleIngredientAvailability(@RequestBody IdForm form) {
        ingredientService.toggleAvailability(form.getId());
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping(value = "/remove", consumes = "application/json")
    public ResponseEntity<String> deleteIngredient(@RequestBody IdForm form) {
        ingredientService.removeIngredient(form.getId());
        return ResponseEntity.ok("OK");
    }
}
