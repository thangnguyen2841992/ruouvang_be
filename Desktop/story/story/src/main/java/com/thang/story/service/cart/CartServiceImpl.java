package com.thang.story.service.cart;

import com.thang.story.model.dto.CartDTO;
import com.thang.story.model.dto.Invoice;
import com.thang.story.model.entity.Cart;
import com.thang.story.model.entity.Product;
import com.thang.story.repository.ICartRepository;
import com.thang.story.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private IProductService productService;

    @Override
    public List<Cart> findAll() {
        return this.cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return this.cartRepository.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return this.cartRepository.save(cart);
    }

    @Override
    public void delete(Long id) {
        this.cartRepository.deleteById(id);
    }

    @Override
    public CartDTO mappingCartToCartDTO(Cart cart) {
        Product product = this.productService.findById(cart.getProductId()).get();
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setProductName(product.getName());
        cartDTO.setProductImg(product.getImage());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str = currencyVN.format(cart.getPayment());
        String str1 = currencyVN.format(product.getPrice());
        cartDTO.setProductPrice(str1);
        cartDTO.setPayment(str);
        return cartDTO;
    }

    @Override
    public Invoice findCartsByUserIdOrderByDateCreated(Long userId) {
        List<Cart> carts = this.cartRepository.findCartsByUserIdOrderByDateCreated(userId);
        List<CartDTO> cartDTOS = new ArrayList<>();
        double totalPayment = 0;
        for (int i = 0; i < carts.size(); i++) {
            cartDTOS.add(mappingCartToCartDTO(carts.get(i)));
            totalPayment = totalPayment + carts.get(i).getPayment();
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str = currencyVN.format(totalPayment);
        Invoice newInvoice = new Invoice(cartDTOS, str);
        return newInvoice;
    }

    @Override
    public Optional<Cart> findCartByUserIdAndProductId(Long userId, Long cartId) {
        return this.cartRepository.findCartByUserIdAndProductId(userId, cartId);
    }
}
