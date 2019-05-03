package ru.karamoff.mcdrive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.karamoff.mcdrive.models.Order;
import ru.karamoff.mcdrive.services.OrderService;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String ordersPage() {
        return "orders";
    }

    /**
     * Получение списка всех заказов в системе в формате JSON
     */
    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<List<Order>> composedOrdersList() {
        List<Order> orders = orderService.getComposedOrders();
        return ResponseEntity.ok(orders);
    }
}
