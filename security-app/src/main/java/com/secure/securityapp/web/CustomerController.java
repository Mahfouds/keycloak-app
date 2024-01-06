package com.secure.securityapp.web;

import com.secure.securityapp.entities.Customer;
import com.secure.securityapp.repositories.CustomerRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {
    private CustomerRepository customerRepository;
    private ClientRegistrationRepository clientRegistrationRepository;

    public CustomerController(CustomerRepository customerRepository,ClientRegistrationRepository clientRegistrationRepository){
    this.customerRepository=customerRepository;
    this.clientRegistrationRepository=clientRegistrationRepository;
    }
    @GetMapping("/")
    public String welcome(){
        return "index";
    }
    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String customers(Model model){
        List<Customer> customerList=customerRepository.findAll();
        model.addAttribute("customers", customerList);
        return "customers"; //customers.html
    }
    @GetMapping("/products")
    public String products(Model model){
        return "products"; //customers.html
    }
    @GetMapping("/auth")
    @ResponseBody
    public Authentication authentication(Authentication authenticationc){
        return authenticationc;
    }
    @GetMapping("/notAuthorize")
    public String notAuthorize(){
        return "notAuthorize";
    }
    @GetMapping("/oauth2Login")
    public String oauth2Login(Model model){
        String authorizationRequestBaseUri="oauth2/authorization";
        Map<String,String> oauth2AuthencationUrls=new HashMap<>();
        Iterable<ClientRegistration> clientRegistrations=(Iterable<ClientRegistration>) clientRegistrationRepository;
        clientRegistrations.forEach(registration->{
            oauth2AuthencationUrls.put(registration.getClientName(),authorizationRequestBaseUri+"/"+registration.getRegistrationId());
        });
        model.addAttribute("urls",oauth2AuthencationUrls);
        return "oauth2Login";
    }
}
