package ru.karamoff.mcdrive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.karamoff.mcdrive.models.Foodpiece;
import ru.karamoff.mcdrive.services.FoodpieceService;

import java.util.List;

@Controller
@RequestMapping("/foodpieces")
public class FoodpieceController {

    @Autowired
    private FoodpieceService foodpieceService;

    /**
     * Получение списка всех блюд в системе в формате JSON
     */
    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<List<Foodpiece>> foodpiecesList() {
        return ResponseEntity.ok(foodpieceService.getAllFoodpieces());
    }
}
