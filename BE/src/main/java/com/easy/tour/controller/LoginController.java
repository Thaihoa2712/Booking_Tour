package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.CustomerDTO;
import com.easy.tour.dto.LoginDTO;
import com.easy.tour.response.CustomerResponseDTO;
import com.easy.tour.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private CustomerService customerService;


    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Endpoint to add a new customer
     * @param customerDTO the customer details from the request body
     * @return the username of the newly created customer
     */
    @PostMapping(value = ApiPath.CUSTOMER_REGISTER)
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerResponseDTO responseDTO = new CustomerResponseDTO();
        try {
            CustomerDTO customerCreate = customerService.addCustomer(customerDTO);
            if(customerCreate != null){
                responseDTO.setMessage("Success");
                responseDTO.setErrorCode(200);
                responseDTO.setData(customerCreate);
                return new ResponseEntity(responseDTO, HttpStatus.OK);
            }else {
                responseDTO.setMessage("Have existed!");
                responseDTO.setErrorCode(400);
                return  new ResponseEntity(responseDTO, HttpStatus.OK);
            }
        }catch (Exception e){
            responseDTO.setMessage("Have Error!");
            responseDTO.setErrorCode(500);
            return  new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Endpoint to login a customer
     * @param loginDTO the login details from the request body
     * @return a message indicating the login status
     */
    @PostMapping(value = ApiPath.CUSTOMER_LOGIN)
    public ResponseEntity<?> loginCustomer(@RequestBody LoginDTO loginDTO)
    {
        CustomerDTO loginResponse = customerService.loginCustomer(loginDTO);
        CustomerResponseDTO responseDTO = new CustomerResponseDTO();
        responseDTO.setData(loginResponse);
        return ResponseEntity.ok(responseDTO);

    }

}
