package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.enums.OrderStatus;
import com.ironhack.pauescolabank.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Enumerated;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {
    private String request;
    private OrderStatus orderStatus;
    private HistoryLogDTO historyLogDTO;


    public OrderDTO fromEntity(Order order) {
        OrderDTO orderDTO = new OrderDTO(
                order.getRequest(),
                order.getOrderStatus(),
                historyLogDTO.fromEntity(order.getStatusLog())
        );
        return orderDTO;
    }
}
