package com.bixby.BixbyCommerce.service;

import com.bixby.BixbyCommerce.dto.*;
import com.bixby.BixbyCommerce.model.*;
import com.bixby.BixbyCommerce.repositories.OrderRepository;
import com.bixby.BixbyCommerce.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository ordersRepository;
    private final CustomerRepository customersRepository;
    private final AddressRepository addressesRepository;
    private final ProductRepository productsRepository;


    @Transactional
    public Orders createOrder(CreateOrderDTO dto) {
        Customers customer = customersRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Addresses address = addressesRepository.findById(Long.parseLong(dto.getAddress()))
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Orders order = new Orders();
        order.setCustomers(customer);
        order.setAddresses(address);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderItems(dto.getOrderItems().stream().map(itemDTO -> {
            Products product = productsRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItems orderItem = new OrderItems();
            orderItem.setOrders(order);
            orderItem.setProducts(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(product.getPrice());

            return orderItem;
        }).collect(Collectors.toList()));


        BigDecimal total = order.getOrderItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(total);

        return ordersRepository.save(order);
    }


    public OrderDTO getOrderById(Long id) {
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return mapToDTO(order);
    }


    @Transactional
    public Orders updateOrder(Long id, UpdateOrderDTO dto) {
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Addresses address = addressesRepository.findById(Long.parseLong(dto.getAddress()))
                .orElseThrow(() -> new RuntimeException("Address not found"));

        order.setAddresses(address);


        order.getOrderItems().clear();
        List<OrderItems> updatedItems = dto.getOrderItems().stream().map(itemDTO -> {
            Products product = productsRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItems item = new OrderItems();
            item.setOrders(order);
            item.setProducts(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice());
            return item;
        }).collect(Collectors.toList());

        order.setOrderItems(updatedItems);

        BigDecimal newTotal = updatedItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(newTotal);

        return ordersRepository.save(order);
    }


    public void deleteOrder(Long id) {
        if (!ordersRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        ordersRepository.deleteById(id);
    }


    public List<OrderDTO> getAllOrders() {
        return ordersRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    private OrderDTO mapToDTO(Orders order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderID());
        dto.setCustomerId(order.getCustomers().getCustomerID());
        dto.setShippingAddress(order.getAddresses().getStreet());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus().name());

        dto.setOrderItems(order.getOrderItems().stream().map(orderItem -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setProductId(orderItem.getProducts().getProductID());
            itemDTO.setQuantity(orderItem.getQuantity());
            itemDTO.setPrice(orderItem.getPrice());
            itemDTO.setProductName(orderItem.getProducts().getName());
            return itemDTO;
        }).collect(Collectors.toList()));

        return dto;
    }
}
