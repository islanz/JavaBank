package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerServiceImpl implements CustomerService {

    private List<Customer> customerList;
    //private AccountService accountService;

    public CustomerServiceImpl() {
        customerList = new ArrayList<>();
    }



    @Override
    public Customer get(Integer id) {

        Customer customer = null;
        for (Customer c : customerList) {
            if (c.getId() == id) {
                customer = c;
            }
        }
        return customer;
    }

    @Override
    public List<Customer> customerList() {
        return customerList;
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        Set<Integer> listOfCustomerAccountIds = new HashSet<>();

        for (Customer c : customerList) {
            listOfCustomerAccountIds.add(c.getId());
        }
        return listOfCustomerAccountIds;
    }

    @Override
    public double getBalance(int customerId) {

        double balance = -1;
        for (Customer c : customerList) {
            if (c.getId() == customerId) {
                balance = c.getBalance();
            }
        }

        return balance;
    }

    @Override
    public void add(Customer customer) {
        customerList.add(customer);
    }

}
