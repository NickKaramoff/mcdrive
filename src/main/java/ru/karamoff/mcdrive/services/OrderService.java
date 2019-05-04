package ru.karamoff.mcdrive.services;

import ru.karamoff.mcdrive.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> getComposedOrders();
    Order startComposingOrder();
    Order addFoodpieceToOrder(Long orderId, Long foodpieceId, Integer amount);
    Order finishComposingOrder(Long orderId);
    void updateOrderPrice(Long orderId);
}
