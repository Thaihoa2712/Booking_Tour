package com.easy.tour.service.Impl;

import com.easy.tour.dto.CustomerDTO;
import com.easy.tour.dto.LoginDTO;
import com.easy.tour.entity.customer.Customer;
import com.easy.tour.repository.CustomerRepository;
import com.easy.tour.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        if (customerRepository.findByEmail(customerDTO.getEmail()) != null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setUserName(customerDTO.getUserName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEnabled(true);
        Customer customerSaved = customerRepository.save(customer);
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setUserName(customer.getUserName());
        customerDTO1.setEmail(customer.getEmail());
        customerDTO1.setPassword(customer.getPassword());
        customerDTO1.setFirstName(customer.getFirstName());
        customerDTO1.setLastName(customer.getLastName());
        customerDTO1.setEnabled(customer.isEnabled());
        return customerDTO1;
    }



    @Override
    public CustomerDTO loginCustomer(LoginDTO loginDTO) {
        String msg = "";
        Customer customer = customerRepository.findByEmail(loginDTO.getEmail());
        CustomerDTO customerDTO1 = new CustomerDTO();
        if (customer != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = customer.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                //Optional<Customer> customer1 = customerRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);

                Customer customer1 = customerRepository.findByEmail(loginDTO.getEmail());

//                if (customer1.isPresent()) {
//                    return new LoginMessage("Login Success", true);
//                } else {
//                    return new LoginMessage("Login Failed", false);
//                }
//            } else {
//                return new LoginMessage("password Not Match", false);
//            }
//        }else {
//            return new LoginMessage("Email not exits", false);

                customerDTO1.setUserName(customer1.getUserName());
                customerDTO1.setEmail(customer1.getEmail());
                customerDTO1.setPassword(customer1.getPassword());
                customerDTO1.setFirstName(customer1.getFirstName());
                customerDTO1.setLastName(customer1.getLastName());
                customerDTO1.setEnabled(customer1.isEnabled());

            }

        }
        return customerDTO1;
    }
}
