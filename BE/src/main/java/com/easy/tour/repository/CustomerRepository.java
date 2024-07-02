package com.easy.tour.repository;

import com.easy.tour.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findOneByEmailAndPassword(String email, String password);
    Customer findByEmail(String email);


}
