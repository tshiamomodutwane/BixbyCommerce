package com.bixby.BixbyCommerce.service;

import com.bixby.BixbyCommerce.dto.CartDTO;
import com.bixby.BixbyCommerce.dto.CartItemDTO;
import com.bixby.BixbyCommerce.dto.CreateCartDTO;
import com.bixby.BixbyCommerce.model.Cart;
import com.bixby.BixbyCommerce.model.CartItems;
import com.bixby.BixbyCommerce.model.Customers;
import com.bixby.BixbyCommerce.model.Products;
import com.bixby.BixbyCommerce.repositories.CartRepository;
import com.bixby.BixbyCommerce.repositories.CustomerRepository;
import com.bixby.BixbyCommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    private CartDTO mapToDTO(Cart cart){
        CartDTO dto = new CartDTO();
        dto.setCartID(cart.getCartID());
        dto.setCustomerID(cart.getCartID());
        dto.setCreatedAt(cart.getCreatedAt());
        dto.setTotalAmount(cart.getTotalAmount());

        List<CartItemDTO> itemDTOS = cart.getCartItems()
                .stream().map(item ->{
                    CartItemDTO i = new CartItemDTO();
                    i.setCartItemID(item.getCartItemID());
                    i.setProductID(item.getProducts().getProductID());
                    i.setProductName(item.getProducts().getName());
                    i.setPrice(item.getProducts().getPrice());
                    i.setQuantity(item.getQuantity());
                    i.setImageURL(item.getImageURL());
                    return i;
                }).collect(Collectors.toList());
        dto.setCartItems(itemDTOS);
        return dto;
    }

    public Optional<CartDTO> createCart(CreateCartDTO createCartDTO, Long customerId){
        Optional<Customers> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()){
            return Optional.empty();
        }

        Cart cart = new Cart();
        cart.setCustomer(customerOpt.get());
        cart.setTotalAmount(createCartDTO.getTotalAmount());

        List<CartItems> items = createCartDTO.getCartItems().stream()
                .map(dto->{
                    Products product = productRepository.findById(dto.getProductID())
                            .orElseThrow(()-> new RuntimeException(("Product not found with ID: " + dto.getProductID())));

                    CartItems item = new CartItems();
                    item.setProducts(product);
                    item.setQuantity((dto.getQuantity()));
                    item.setCart(cart);
                    item.setImageURL(product.getImageURL());
                    return item;
                }).collect(Collectors.toList());

        cart.setCartItems(items);
        Cart savedCart = cartRepository.save(cart);

        return Optional.of(mapToDTO(savedCart));
    }

    public Optional<CartDTO> getCartById(Long cartId){
        return cartRepository.findById(cartId).map(this::mapToDTO);
    }

    public void deleteCart(Long cartId){
        cartRepository.deleteById(cartId);
    }
}

