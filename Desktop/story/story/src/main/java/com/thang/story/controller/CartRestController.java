package com.thang.story.controller;

import com.thang.story.model.dto.Invoice;
import com.thang.story.model.dto.ListQuantityCartDTo;
import com.thang.story.model.dto.Message;
import com.thang.story.model.dto.QuantityCartDTO;
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
    @GetMapping("/list/quantity/user/{userID}")
    public ResponseEntity<ListQuantityCartDTo> getListQuantity(@PathVariable Long userID) {
        Invoice invoice = this.cartService.findCartsByUserIdOrderByDateCreated(userID);
        List<QuantityCartDTO> quantityCartDTOList = new ArrayList<>();
        for (int i = 0; i < invoice.getCartsOfUser().size(); i++) {
            quantityCartDTOList.add(new QuantityCartDTO(invoice.getCartsOfUser().get(i).getId(), invoice.getCartsOfUser().get(i).getQuantity()));
        }
        ListQuantityCartDTo listQuantityCartDTo = new ListQuantityCartDTo(quantityCartDTOList);
        return new ResponseEntity<>(listQuantityCartDTo, HttpStatus.OK);
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
    public ResponseEntity<?> updateCartQuantity(@RequestBody ListQuantityCartDTo listQuantityCartDTo) {
        List<QuantityCartDTO> listQuantity = listQuantityCartDTo.getQuantityCartDTOList();
        for (int i = 0; i < listQuantity.size(); i++) {
            Optional<Cart> cartOptional = this.cartService.findById(listQuantity.get(i).getCartId());
            if (!cartOptional.isPresent()) {
                return new ResponseEntity<>(new Message("Giỏ hàng không tồn tại!"), HttpStatus.BAD_REQUEST);
            }
            cartOptional.get().setQuantity(listQuantity.get(i).getQuantity());
            this.cartService.save(cartOptional.get());
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
