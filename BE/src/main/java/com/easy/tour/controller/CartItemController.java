package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.Cart.CartItemDTO;
import com.easy.tour.response.Cart.CartItemResponseDTO;
import com.easy.tour.service.Impl.Cart.CartItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartItemController {
    @Autowired
    private CartItemServiceImpl cartItemService;

    @PostMapping(ApiPath.CART_ITEM_CREATE)
    public ResponseEntity<?> create(@RequestBody CartItemDTO carItemDTO){
        CartItemResponseDTO response = new CartItemResponseDTO();
        try{
            CartItemDTO cartItemDTOCreated = cartItemService.create(carItemDTO);
            response.setMessage("Create cart item success");
            response.setErrorCode(200);
            response.setData(cartItemDTOCreated);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            response.setMessage("Create cart item fail");
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
