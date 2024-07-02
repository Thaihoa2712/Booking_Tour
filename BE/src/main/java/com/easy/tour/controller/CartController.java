package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.Cart.CartItemDTO;
import com.easy.tour.dto.Cart.ShoppingCartDTO;
import com.easy.tour.dto.TourDTO;
import com.easy.tour.response.Cart.CartResponseDTO;
import com.easy.tour.service.Impl.Cart.CartItemServiceImpl;
import com.easy.tour.service.Impl.Cart.ShoppingCartServiceImpl;
import com.easy.tour.service.Impl.TourServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired
    private ShoppingCartServiceImpl cartService;
    @Autowired
    private CartItemServiceImpl cartItemService;
    @Autowired
    private TourServiceImpl tourService;

    @PostMapping(ApiPath.ADD_CART_ITEM_INTO_CART)
    public ResponseEntity<?> insertCartItemIntoCart(@RequestBody TourDTO tourDTO) {
        CartResponseDTO response = new CartResponseDTO();
        try {
            // Get cart
            ShoppingCartDTO shoppingCartDTO = cartService.getByID(1);
            // Check exists
            CartItemDTO checkCartItemDTO = cartItemService.getCartItemByTourCode(tourDTO.getTourCode());
            if(checkCartItemDTO != null){
                response.setMessage("Cart item already exists");
                response.setErrorCode(409);
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
            // Get tour
            TourDTO tourDTOFind = tourService.findByTourCode(tourDTO.getTourCode());
            // Create cart item
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setTour(tourDTOFind);
            cartItemDTO.setCart(shoppingCartDTO);
            // Save cart item into db
            CartItemDTO cartItemDTOSaved = cartItemService.create(cartItemDTO);
            // Add cart item in list cart item of cart
            shoppingCartDTO.getCartItems().add(cartItemDTOSaved);
            // Update cart
            ShoppingCartDTO shoppingCartDTOSaved = cartService.update(shoppingCartDTO);
            // Paste value in response
            response.setMessage("Insert cart item into cart success");
            response.setErrorCode(200);
            System.out.println("CHECKKKK");
            response.setData(shoppingCartDTOSaved);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Insert cart item into cart fail");
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(ApiPath.CART_FIND_BY_ID + "/{id}")
    public ResponseEntity<?> cartFindById(@PathVariable("id") Long id) {
        CartResponseDTO response = new CartResponseDTO();
        try{
            ShoppingCartDTO shoppingCartDTO = cartService.getByID(id);
            response.setMessage("Get cart by id success");
            response.setErrorCode(200);
            response.setData(shoppingCartDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            response.setMessage("Show list cart items fail");
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
