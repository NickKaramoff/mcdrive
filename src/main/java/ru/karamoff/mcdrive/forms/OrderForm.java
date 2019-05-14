package ru.karamoff.mcdrive.forms;

import lombok.Data;

import java.util.Map;

@Data
public class OrderForm {
    private Map<Long, Integer> foodpieces;
}
