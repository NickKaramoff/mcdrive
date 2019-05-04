package ru.karamoff.mcdrive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karamoff.mcdrive.models.Foodpiece;
import ru.karamoff.mcdrive.models.FoodpieceInOrder;
import ru.karamoff.mcdrive.models.Order;
import ru.karamoff.mcdrive.repositories.FoodpieceInOrderRepository;
import ru.karamoff.mcdrive.repositories.FoodpieceRepository;
import ru.karamoff.mcdrive.repositories.OrderRepository;

import java.time.LocalDateTime;
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

    @Override
    public Order startComposingOrder() {
        Order order = Order.builder()
                .ready(false)
                .archived(false)
                .sum(0.0f)
                .time(null)
                .build();
        orderRepository.saveAndFlush(order);
        return order;
    }

    @Override
    public Order addFoodpieceToOrder(Long orderId, Long foodpieceId, Integer amount) {
        Order order = orderRepository.getOne(orderId);
        Foodpiece foodpiece = foodpieceRepository.getOne(foodpieceId);
        FoodpieceInOrder fio = FoodpieceInOrder.builder()
                .order(order)
                .foodpiece(foodpiece)
                .amount(amount)
                .ready(false)
                .build();
        foodpieceInOrderRepository.saveAndFlush(fio);
        updateOrderPrice(orderId);
        return order;
    }

    @Override
    public Order finishComposingOrder(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        order.setTime(LocalDateTime.now());
        orderRepository.saveAndFlush(order);
        return order;
    }

    @Override
    public void updateOrderPrice(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        float sum = 0.0f;
        for (FoodpieceInOrder fio : order.getFoodpieces()) {
            sum += fio.getFoodpiece().getCost() * fio.getAmount();
        }
        order.setSum(sum);
        orderRepository.saveAndFlush(order);
    }
}
