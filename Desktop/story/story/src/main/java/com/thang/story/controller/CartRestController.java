package com.thang.story.controller;

import com.thang.story.model.dto.CartDTO;
import com.thang.story.model.dto.Invoice;
import com.thang.story.model.dto.Message;
import com.thang.story.model.entity.Cart;
import com.thang.story.model.entity.Product;
import com.thang.story.service.cart.ICartService;
import com.thang.story.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/carts")
public class CartRestController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private IProductService productService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getInvoicesOfUser(@PathVariable Long userId) {
        Invoice invoice = this.cartService.findCartsByUserIdOrderByDateCreated(userId);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createNewCart(@RequestBody Cart cart) {
        Optional<Product> product = this.productService.findById(cart.getProductId());
        Optional<Cart> cartOptional = this.cartService.findCartByUserIdAndProductId(cart.getUserId(), cart.getProductId());
        if (cartOptional.isPresent()) {
            int newQuantity = cartOptional.get().getQuantity() + cart.getQuantity();
            double newPayment = product.get().getPrice() * newQuantity;
            cartOptional.get().setQuantity(newQuantity);
            cartOptional.get().setPayment(newPayment);
            this.cartService.save(cartOptional.get());
            return new ResponseEntity<>(cartOptional.get(), HttpStatus.CREATED);
        }
        Cart newCart = new Cart();
        newCart.setQuantity(cart.getQuantity());
        newCart.setUserId(cart.getUserId());
        newCart.setProductId(cart.getProductId());
        newCart.setDateCreated(new Date());
        if (product.isPresent()) {
            newCart.setPayment(product.get().getPrice() * cart.getQuantity());
        }
        this.cartService.save(newCart);
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<?> updateCartQuantity(@RequestBody Invoice invoice) {
        List<CartDTO> cartDTOS = invoice.getCartsOfUser();
        for (int i = 0; i < cartDTOS.size(); i++) {
            Cart cart = this.cartService.findById(cartDTOS.get(i).getId()).get();
            if (cartDTOS.get(i).getQuantity() == 0) {
                this.cartService.delete(cartDTOS.get(i).getId());
            } else {
                cart.setQuantity(cartDTOS.get(i).getQuantity());
                Product product = this.productService.findById(cart.getProductId()).get();
                cart.setPayment(product.getPrice() * cartDTOS.get(i).getQuantity());
                this.cartService.save(cart);
            }
        }
        return new ResponseEntity<>(new Message("Cập nhật giỏ hàng thành công!"), HttpStatus.OK);
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable Long cartId) {
        Optional<Cart> cartOptional = this.cartService.findById(cartId);
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Giỏ hàng không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        this.cartService.delete(cartId);
        return new ResponseEntity<>(new Message("Xoá thành công!"), HttpStatus.OK);
    }
}
