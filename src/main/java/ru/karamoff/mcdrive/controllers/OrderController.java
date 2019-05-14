package ru.karamoff.mcdrive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.karamoff.mcdrive.forms.OrderForm;
import ru.karamoff.mcdrive.models.Foodpiece;
import ru.karamoff.mcdrive.models.Order;
import ru.karamoff.mcdrive.services.FoodpieceService;
import ru.karamoff.mcdrive.services.OrderService;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodpieceService foodpieceService;

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

    @GetMapping("/new")
    public String newOrderPage(ModelMap map) {
        List<Foodpiece> foodpieces = foodpieceService.getAvailableFoodpieces();
        map.addAttribute("foodpieces", foodpieces);
        return "order_new";
    }

    @PostMapping("/new")
    public String createOrder(OrderForm form) {
        orderService.createOrder(form);
        return "redirect:/orders";
    }
}
