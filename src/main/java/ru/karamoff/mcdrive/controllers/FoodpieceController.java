package ru.karamoff.mcdrive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.karamoff.mcdrive.forms.FoodpieceForm;
import ru.karamoff.mcdrive.forms.IdForm;
import ru.karamoff.mcdrive.models.Foodpiece;
import ru.karamoff.mcdrive.models.Ingredient;
import ru.karamoff.mcdrive.services.FoodpieceService;
import ru.karamoff.mcdrive.services.IngredientService;

import java.util.List;

@Controller
@RequestMapping("/foodpieces")
public class FoodpieceController {

    @Autowired
    private FoodpieceService foodpieceService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String foodpiecesListPage(ModelMap map) {
        List<Foodpiece> foodpieces = foodpieceService.getAllFoodpieces();
        map.addAttribute("foodpieces", foodpieces);
        return "foodpieces_list";
    }

    @GetMapping("/new")
    public String newFoodpiecePage(ModelMap map) {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        map.addAttribute("ingredients", ingredients);
        return "foodpiece_new";
    }

    @PostMapping("/new")
    public String createFoodpiece(FoodpieceForm form) {
        foodpieceService.createFoodpiece(form);
        return "redirect:/foodpieces";
    }

    @DeleteMapping(value = "/remove", consumes = "application/json")
    public ResponseEntity<String> deleteFoodpiece(@RequestBody IdForm form) {
        foodpieceService.removeFoodpiece(form.getId());
        return ResponseEntity.ok("OK");
    }
}
