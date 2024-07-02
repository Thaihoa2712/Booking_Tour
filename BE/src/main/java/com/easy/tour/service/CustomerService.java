package com.easy.tour.service;

import com.easy.tour.dto.CustomerDTO;
import com.easy.tour.dto.LoginDTO;

public interface CustomerService {
    CustomerDTO addCustomer(CustomerDTO customerDTO);
    CustomerDTO loginCustomer(LoginDTO loginDTO);
}
