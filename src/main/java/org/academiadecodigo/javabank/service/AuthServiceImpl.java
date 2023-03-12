package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;

public class AuthServiceImpl implements AuthService {

    private int loginCustomerId;
    private CustomerService customerService;

    @Override
    public boolean authenticate(Integer id) {
        if (customerService.get(id) != null) {
            loginCustomerId = id;
            return true;
        }

        return false;
    }

    @Override
    public Customer getAccessingCustomer() {
        return customerService.get(loginCustomerId);
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
