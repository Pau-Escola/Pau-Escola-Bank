package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.OrderDTO;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.enums.OrderStatus;
import com.ironhack.pauescolabank.model.Order;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAll(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PostMapping
    public Order create(@RequestBody OrderDTO orderDTO){
        return orderService.save(orderDTO);
    }
    //todo pensar si implementem lopcio desborrar la orderi deixar nomes elhistorylog

    @PatchMapping("/update_status/{id}")
    public Order updateNamePatch(@PathVariable Long id,
                                  @RequestBody OrderStatus status){
        return orderService.updateStatus(id, status);
    }
}
