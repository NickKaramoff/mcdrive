package ru.karamoff.mcdrive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karamoff.mcdrive.forms.OrderForm;
import ru.karamoff.mcdrive.models.Foodpiece;
import ru.karamoff.mcdrive.models.FoodpieceInOrder;
import ru.karamoff.mcdrive.models.Order;
import ru.karamoff.mcdrive.repositories.FoodpieceInOrderRepository;
import ru.karamoff.mcdrive.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodpieceService foodpieceService;

    @Autowired
    private FoodpieceInOrderRepository foodpieceInOrderRepository;

    @Override
    public List<Order> getComposedOrders() {
        return orderRepository.findAllByTimeIsNotNull();
    }

    @Override
    public void createOrder(OrderForm orderForm) {
        Order order = Order.builder()
                .time(LocalDateTime.now())
                .ready(false)
                .archived(false)
                .build();
        final Order finalOrder = orderRepository.saveAndFlush(order);
        final float[] sum = {0.0f};

        orderForm.getFoodpieces()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() != 0)
                .forEach(entry -> {
                    Foodpiece foodpiece = foodpieceService.getFoodpiece(entry.getKey());
                    sum[0] += foodpiece.getCost() * entry.getValue();
                    FoodpieceInOrder fio = FoodpieceInOrder.builder()
                            .foodpiece(foodpiece)
                            .order(finalOrder)
                            .amount(entry.getValue())
                            .ready(false)
                            .build();
                    foodpieceInOrderRepository.save(fio);
                });
        foodpieceInOrderRepository.flush();
        finalOrder.setSum(sum[0]);
        orderRepository.save(finalOrder);
    }

    @Override
    public boolean toggleFoodpieceInOrder(Long foodpieceInOrderId) {
        FoodpieceInOrder fio = foodpieceInOrderRepository.getOne(foodpieceInOrderId);
        fio.setReady(!fio.getReady());
        foodpieceInOrderRepository.saveAndFlush(fio);
        return updateOrderReadiness(fio.getOrder());
    }

    @Override
    public boolean toggleOrder(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        order.setReady(!order.getReady());
        orderRepository.saveAndFlush(order);
        return order.getReady();
    }

    @Override
    public boolean updateOrderReadiness(Order order) {
        boolean ready = true;
        for (FoodpieceInOrder fio : order.getFoodpieces()) {
            if (!fio.getReady()) {
                ready = false;
                break;
            }
        }
        order.setReady(ready);
        orderRepository.save(order);
        return ready;
    }
}
