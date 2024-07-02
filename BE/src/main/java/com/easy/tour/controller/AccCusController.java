package com.easy.tour.controller;


import com.easy.tour.dto.UserDTO;
import com.easy.tour.response.UserResponseDTO;
import com.easy.tour.service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.easy.tour.consts.ApiPath.*;

@RestController
@Slf4j
public class AccCusController {
    @Autowired
    UserServiceImpl service;

    // Method GET for find account Customer by uuid
    @GetMapping(ACCOUNT_DETAIL_FIND_BY_ID + "/{userId}")
    public ResponseEntity<?> findById(@PathVariable("userId") Long userId){
        UserResponseDTO response = new UserResponseDTO();
        try {
            UserDTO userDTO= service.getByID(userId);
            if (userDTO != null) {
                response.setMessage("Successfully to get Account by uuid: " + userId);
                response.setErrorCode(200);
                response.setData(userDTO);
                return  new ResponseEntity<>( response, HttpStatus.OK);
            } else {
                response.setMessage("Account with uuid " + userId + " does not exist!");
                response.setErrorCode(404);
                return  new ResponseEntity<>( response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMessage("Error when get Account with by uuid: " + userId);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method GET for show form update by id
    @GetMapping(FIND_BY_ID_UPDATE + "/{userId}")
    public ResponseEntity<?> updateById(@PathVariable("userId") Long userId){
        UserResponseDTO response = new UserResponseDTO();
        try {
            UserDTO userDTO= service.getByID(userId);
            if (userDTO != null) {
                response.setMessage("Successfully to get Account by uuid: " + userId);
                response.setErrorCode(200);
                response.setData(userDTO);
                return  new ResponseEntity<>( response, HttpStatus.OK);
            } else {
                response.setMessage("Account with uuid " + userId + " does not exist!");
                response.setErrorCode(404);
                return  new ResponseEntity<>( response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMessage("Error when get Account with by uuid: " + userId);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method PUT for update/edit account Cus info
    @PutMapping(ACCOUNT_DETAIL_UPDATE + "/{userId}")
    public ResponseEntity<?> update(@PathVariable("userId") Long userId,
                                    @Valid @RequestBody UserDTO userDTO){
        UserResponseDTO response = new UserResponseDTO();
        try {
            userDTO.setUserId(userId);
            UserDTO userDTO1 = service.update(userDTO);
                response.setMessage("Update Account Successfully");
                response.setErrorCode(200);
                response.setData(userDTO1);
                return  new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.trace(String.valueOf(e));
            response.setMessage("Error when update Account , Please try again");
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@DeleteMapping(ACCOUNT_DETAIL_DELETE + "/{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId") Integer userId,@RequestBody UserDTO userDTO){
        UserResponseDTO response = new UserResponseDTO();
        log.info("delete account: {}", userId);
        try{
            userDTO.setUserId(userId);
            boolean deleteResult = service.delete(userId);
            if(deleteResult) {
                response.setMessage("Successfully deleted Tour with tour code: " + userId);
                response.setErrorCode(200);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setMessage("Failed to deleted Tour with tour code: " + userId);
                response.setErrorCode(400);
                return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
            }
        } catch (Exception e) {
            log.trace(String.valueOf(e.getMessage()));
            response.setMessage("Error when deleted Tour, Please try again");
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
