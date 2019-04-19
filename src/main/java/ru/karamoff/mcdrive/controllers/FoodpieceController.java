package ru.karamoff.mcdrive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.karamoff.mcdrive.models.Foodpiece;
import ru.karamoff.mcdrive.repositories.FoodpieceRepository;

import java.util.List;

@Controller
public class FoodpieceController {

    @Autowired
    private FoodpieceRepository foodpieceRepository;

    @GetMapping("/foodpieces/json")
    @ResponseBody
    public ResponseEntity<List<Foodpiece>> foodpiecesList() {
        List<Foodpiece> foodpieces = foodpieceRepository.findAll();
        return ResponseEntity.ok(foodpieces);
    }
}
