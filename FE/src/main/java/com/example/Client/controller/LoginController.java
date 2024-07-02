package com.example.Client.controller;


import com.example.Client.consts.ApiPath;
import com.example.Client.consts.UrlPath;
import com.example.Client.dto.CustomerDTO;
import com.example.Client.dto.ForgotPasswordDTO;
import com.example.Client.response.CustomerResponseDTO;
import com.example.Client.response.UserResponseDTO;
import com.example.Client.utils.RestTemplateUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;


@Controller
public class LoginController {
    @Autowired
    private RestTemplateUtils restTemplateUtils;

    @GetMapping(UrlPath.CUSTOMER_LOGIN)
    public String showLoginPage(Model model) {
        CustomerDTO customerDTO = new CustomerDTO();
        model.addAttribute("customerlogin", customerDTO);
        return "login";
    }


    @PostMapping(UrlPath.CUSTOMER_LOGIN)
    public String login(@ModelAttribute("customerlogin") CustomerDTO customerDTO,
                        Model model,
                        HttpServletRequest request,
                        HttpSession session) {

        // Check for empty email and password
        if (customerDTO.getEmail() == null || customerDTO.getEmail().isEmpty() ||
                customerDTO.getPassword() == null || customerDTO.getPassword().isEmpty()) {
            model.addAttribute("error", "Email and password must not be empty!");
            return "login";
        }

        // Authenticate user
        CustomerResponseDTO customerResponse = restTemplateUtils.postData(customerDTO, ApiPath.CUSTOMER_LOGIN,
                request, CustomerResponseDTO.class);

        if (customerResponse.getData() == null) {
            // Authentication failed, email may not exist or password is incorrect
            model.addAttribute("error", customerResponse.getMessage() != null ? customerResponse.getMessage() : "Invalid email or password!");
            return "login";
        }

        // Authentication successful
        // Store user information in the session
        session.setAttribute("loggedInUser", customerResponse.getData());
        System.out.println(customerResponse.getData());
        System.out.println("User logged in: " + session.getAttribute("loggedInUser"));

        // Redirect to home page
        return "redirect:/admin/v1/home";
    }

//    @PostMapping(UrlPath.CUSTOMER_LOGIN)
//    public String login(@ModelAttribute("c ustomerlogin") CustomerDTO customerDTO,
//                        Model model,
//                        HttpServletRequest request,
//                        HttpSession session) {
//
//        // Authenticate user
//        CustomerResponseDTO customerResponse = restTemplateUtils.postData(customerDTO, ApiPath.CUSTOMER_LOGIN, request, CustomerResponseDTO.class);
//
//        if (customerResponse.getData() != null) {
//            // Authentication failed
//            model.addAttribute("error", "Invalid email or password!");
//            return "login";
//        }
//
//        // Authentication successful
//        // Store user information in the session
//        session.setAttribute("loggedInUser", customerResponse.getData());
//
//        // Redirect to home page
//        return "redirect:/home";
//    }


    @GetMapping(UrlPath.CUSTOMER_REGISTER)
    public String showRegisterPage(Model model) {
        CustomerDTO customerDTO = new CustomerDTO();
        model.addAttribute("customer", customerDTO);
        return "register";
    }

    @PostMapping(UrlPath.CUSTOMER_REGISTER)
    public String register(@Valid @ModelAttribute("customer") CustomerDTO customerDTO,
                           BindingResult result,
                           Model model,
                           HttpServletRequest request) {
        try {
            // Check for validation errors
            if (result.hasErrors()) {
                model.addAttribute("customer", customerDTO);
                return "register";
            }

            // Check if passwords match
            if (!customerDTO.getPassword().equals(customerDTO.getConfirmPassword())) {
                model.addAttribute("customer", customerDTO);
                model.addAttribute("errorPass", "Passwords do not match!");
                return "register";
            }

            // Check if email is already registered
            CustomerResponseDTO customerResponseDTO = restTemplateUtils.postData(customerDTO,
                                                                                ApiPath.CUSTOMER_REGISTER,
                                                                                request, CustomerResponseDTO.class);
            System.out.println(customerResponseDTO);
            if (("Have existed!").equals(customerResponseDTO.getMessage()) ) {
                model.addAttribute("customer", customerDTO);
                model.addAttribute("errorEmail", "Email has been registered!");
                return "register";
            }

            model.addAttribute("success", "Registered successfully!");
            return "redirect:/admin/v1/login-customer";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Server error, please try again later.");
            return "register";
        }
    }

    @GetMapping(value = UrlPath.FORGOT_PASSWORD_PAGE)
    public String forgotPasswordPage(Model model) {
        ForgotPasswordDTO forgotPasswordDTO = new ForgotPasswordDTO();
        model.addAttribute("forgotPasswordDTO", forgotPasswordDTO);
        return "forgotPasswordPage.html";
    }

    @PostMapping(value = UrlPath.FORGOT_PASSWORD_PAGE)
    public String forgotPasswordSubmit(
            Model model,
            HttpSession session,
            @Valid @ModelAttribute("forgotPasswordDTO")ForgotPasswordDTO forgotPasswordDTO,
            BindingResult result
    ) {
        // Validate
        if (result.hasErrors()) {
            return "forgotPasswordPage.html";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> requestEntity = new HttpEntity<>(forgotPasswordDTO, headers);
        try {
            UserResponseDTO response = restTemplateUtils.putData(requestEntity, ApiPath.FORGOT_PASSWORD, UserResponseDTO.class);
            if (response != null) {
                session.setAttribute("messageSuccess", "Check your email to take new password!");

            }
            return "redirect:" + UrlPath.CUSTOMER_LOGIN;
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
            model.addAttribute("messageError", "Email does not exist!");
            return  "forgotPasswordPage/html";
        }
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Xóa session hoặc các xử lý khác cho logout
        request.getSession().invalidate();
        return "redirect:/login"; // Chuyển hướng tới trang đăng nhập sau khi logout
    }
}
