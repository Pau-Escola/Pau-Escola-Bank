package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.OrderDTO;
import com.ironhack.pauescolabank.enums.OrderStatus;
import com.ironhack.pauescolabank.model.Order;
import com.ironhack.pauescolabank.repositories.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(OrderDTO orderDTO) {
        Order order = new Order();
        return orderRepository.save(order.fromDTO(orderDTO));
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Order with id: " + id));
    }

    public Order updateStatus(Long id, OrderStatus status) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Order with id: " + id));

        order.setOrderStatus(status);
        return orderRepository.save(order);

    }
}
