package com.secure.securityapp;

import com.secure.securityapp.entities.Customer;
import com.secure.securityapp.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityAppApplication.class, args);
    }
@Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(Customer.builder().name("Mahfoud").email("mahfoud.chdaoui@gmail.com").build());
            customerRepository.save(Customer.builder().name("karim").email("karim.chdaoui@gmail.com").build());
            customerRepository.save(Customer.builder().name("amine").email("amine.chdaoui@gmail.com").build());
        };
}
}
