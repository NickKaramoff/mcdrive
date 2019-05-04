package ru.karamoff.mcdrive.forms;

import lombok.Data;

@Data
public class IngredientForm {
    private Long id;
    private String name;
    private Boolean available;
}
