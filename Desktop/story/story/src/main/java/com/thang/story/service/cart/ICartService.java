package com.thang.story.service.cart;

import com.thang.story.model.dto.CartDTO;
import com.thang.story.model.dto.Invoice;
import com.thang.story.model.entity.Cart;
import com.thang.story.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICartService extends IGeneralService<Cart> {
    CartDTO mappingCartToCartDTO(Cart cart);

    Invoice findCartsByUserIdOrderByDateCreated(Long userId);

    Optional<Cart> findCartByUserIdAndProductId(Long userId, Long cartId);


}
