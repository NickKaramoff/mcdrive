package ru.karamoff.mcdrive.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.karamoff.mcdrive.forms.FoodpieceInOrderForm;
import ru.karamoff.mcdrive.models.Order;
import ru.karamoff.mcdrive.services.OrderService;

@Controller
@RequestMapping("/orders/compose")
public class ComposeController {

    @Data
    @AllArgsConstructor
    private class Response {
        private Long orderId;
    }

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/start")
    @ResponseBody
    public ResponseEntity<Response> createOrder() {
        Order order = orderService.startComposingOrder();
        return ResponseEntity.ok(new Response(order.getId()));
    }

    @PutMapping(value = "/add", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Response> addFoodpieceToOrder(@RequestBody FoodpieceInOrderForm form) {
        Order order = orderService.addFoodpieceToOrder(
                form.getOrderId(),
                form.getFoodpieceId(),
                form.getAmount()
        );
        return ResponseEntity.ok(new Response(order.getId()));
    }

    @PostMapping(value = "/finish", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Response> finishCreatingOrder(@RequestBody FoodpieceInOrderForm form) {
        Order order = orderService.finishComposingOrder(form.getOrderId());
        return ResponseEntity.ok(new Response(order.getId()));
    }
}