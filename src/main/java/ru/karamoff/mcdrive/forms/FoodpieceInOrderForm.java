package ru.karamoff.mcdrive.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodpieceInOrderForm {
    private Long orderId;
    private Long foodpieceId;
    private Integer amount;
}
