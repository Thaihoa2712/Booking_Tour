package com.easy.tour.service;

import com.easy.tour.dto.Cart.CartItemDTO;

public interface CartItemService {
    public CartItemDTO getCartItemByTourCode(String tourCode);
}
