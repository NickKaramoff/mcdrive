package ru.karamoff.mcdrive.services;

import ru.karamoff.mcdrive.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> getComposedOrders();
}
