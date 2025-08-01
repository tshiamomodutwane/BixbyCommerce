package com.bixby.BixbyCommerce.controller;

import com.bixby.BixbyCommerce.dto.CreateOrderDTO;
import com.bixby.BixbyCommerce.dto.OrderDTO;
import com.bixby.BixbyCommerce.dto.UpdateCategoryDTO;
import com.bixby.BixbyCommerce.dto.UpdateOrderDTO;
import com.bixby.BixbyCommerce.model.Orders;
import com.bixby.BixbyCommerce.service.OrderService;
import jakarta.validation.Valid;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Orders> createOrder(@Valid @RequestBody CreateOrderDTO dto){
        Orders created = orderService.createOrder(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(Long id){
        OrderDTO order = orderService.getOrderById(id);

        if (order==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @Valid @RequestBody UpdateOrderDTO dto){
        Orders updated = orderService.updateOrder(id,dto);
        if(updated==null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updated);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        if (orderService.deleteOrder(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
