package ru.karamoff.mcdrive.forms;

import lombok.Data;

import java.util.Map;

@Data
public class FoodpieceForm {
    private String name;
    private Float cost;
    private Map<Long, Integer> ingredients;
}
