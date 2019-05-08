package ru.karamoff.mcdrive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.karamoff.mcdrive.forms.IdForm;
import ru.karamoff.mcdrive.forms.IngredientForm;
import ru.karamoff.mcdrive.models.Foodpiece;
import ru.karamoff.mcdrive.services.FoodpieceService;

import java.util.List;

@Controller
@RequestMapping("/foodpieces")
public class FoodpieceController {

    @Autowired
    private FoodpieceService foodpieceService;

    @GetMapping
    public String foodpiecesPage(ModelMap map) {
        List<Foodpiece> foodpieces = foodpieceService.getAllFoodpieces();
        map.addAttribute("foodpieces", foodpieces);
        return "foodpieces_list";
    }

    /**
     * Получение списка всех блюд в системе в формате JSON
     */
    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<List<Foodpiece>> foodpiecesList() {
        return ResponseEntity.ok(foodpieceService.getAllFoodpieces());
    }

    @DeleteMapping(value = "/remove", consumes = "application/json")
    public ResponseEntity<String> removeIngredient(@RequestBody IdForm form) {
        foodpieceService.removeFoodpiece(form.getId());
        return ResponseEntity.ok("OK");
    }
}
