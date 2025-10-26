package com.example.real_estate.controller;

import com.example.real_estate.model.Customer;
import com.example.real_estate.repository.CustomerRepository;
import com.example.real_estate.repository.BusinessRepository;
import com.example.real_estate.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PostMapping("/with-business/{businessId}")
    public Customer createCustomerWithBusiness(@PathVariable Long businessId, @RequestBody Customer customer) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        customer.setBusiness(business);
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(updatedCustomer.getName());
                    customer.setPhone(updatedCustomer.getPhone());
                    customer.setEmail(updatedCustomer.getEmail());
                    customer.setAddress(updatedCustomer.getAddress());
                    customer.setBusiness(updatedCustomer.getBusiness());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    updatedCustomer.setId(id);
                    return customerRepository.save(updatedCustomer);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
