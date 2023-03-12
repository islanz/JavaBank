package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.AuthService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.view.LoginView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The {@link LoginView} controller
 */
public class LoginController extends AbstractController {

    private Controller nextController;
    private CustomerService customerService;

    /**
     * Sets the next controller
     *
     * @param nextController the next controller to be set
     */
    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    /**
     * Sets the bank
     *
     * @param bank the bank to be set
     */


    /**
     * Identifies the logged in customer
     *
     * @param id the customer id
     */
    public void onLogin(int id) {
        authService.authenticate(id);
        nextController.init();
    }

    public Customer getLoginCustomer() {
        return authService.getAccessingCustomer();
    }

    public Set<Integer> listCustomerIds() {
        Set<Integer> ids = customerService.customerList().stream().map(Customer::getId).collect(Collectors.toSet());
        return ids;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


}
