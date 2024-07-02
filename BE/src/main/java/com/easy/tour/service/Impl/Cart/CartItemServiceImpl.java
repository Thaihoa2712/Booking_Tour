package com.easy.tour.service.Impl.Cart;

import com.easy.tour.dto.Cart.CartItemDTO;
import com.easy.tour.entity.Cart.CartItem;
import com.easy.tour.mapper.AbstractMapper;
import com.easy.tour.repository.Cart.CartItemRepository;
import com.easy.tour.service.CartItemService;
import com.easy.tour.service.Impl.AbstractBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl extends AbstractBaseServiceImpl<CartItemDTO> implements CartItemService {
    @Autowired
    CartItemRepository repo;

    public CartItemServiceImpl() {
        AbstractMapper mapper = new AbstractMapper(CartItem.class, CartItemDTO.class);
        super.setMapper(mapper);
    }
    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(this.repo);
    }


    @Override
    public CartItemDTO getCartItemByTourCode(String tourCode) {
        CartItem cartItem = (CartItem) repo.findByTourCode(tourCode);
        if (cartItem == null){
            return null;
        }
        return (CartItemDTO) mapper.convertEntityToDTO(cartItem);
    }
}
