package ru.karamoff.mcdrive.services;

import ru.karamoff.mcdrive.forms.OrderForm;
import ru.karamoff.mcdrive.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> getComposedOrders();
    void createOrder(OrderForm orderForm);
}
