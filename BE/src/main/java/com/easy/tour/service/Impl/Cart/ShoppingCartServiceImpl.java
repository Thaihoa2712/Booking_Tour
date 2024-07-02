package com.easy.tour.service.Impl.Cart;

import com.easy.tour.dto.Cart.ShoppingCartDTO;
import com.easy.tour.entity.Cart.ShoppingCart;
import com.easy.tour.mapper.AbstractMapper;
import com.easy.tour.repository.Cart.ShoppingCartRepository;
import com.easy.tour.service.Impl.AbstractBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends AbstractBaseServiceImpl<ShoppingCartDTO> {

    @Autowired
    ShoppingCartRepository repo;

    public ShoppingCartServiceImpl() {
        AbstractMapper mapper = new AbstractMapper(ShoppingCart.class, ShoppingCartDTO.class);
        super.setMapper(mapper);
    }

    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(this.repo);
    }
}
