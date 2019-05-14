package ru.karamoff.mcdrive.forms;

import lombok.Data;

@Data
public class FoodpieceInOrderForm {
    private Long orderId;
    private Long foodpieceId;
    private Integer amount;
}
