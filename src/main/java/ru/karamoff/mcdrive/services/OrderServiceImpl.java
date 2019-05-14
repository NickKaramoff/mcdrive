package ru.karamoff.mcdrive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karamoff.mcdrive.models.Order;
import ru.karamoff.mcdrive.repositories.FoodpieceInOrderRepository;
import ru.karamoff.mcdrive.repositories.FoodpieceRepository;
import ru.karamoff.mcdrive.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodpieceRepository foodpieceRepository;

    @Autowired
    private FoodpieceInOrderRepository foodpieceInOrderRepository;

    @Override
    public List<Order> getComposedOrders() {
        return orderRepository.findAllByTimeIsNotNull();
    }
}
