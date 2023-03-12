package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.controller.AbstractController;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.AuthService;

/**
 * A generic account transaction controller to be used as a base for concrete transaction controller implementations
 * @see AbstractController
 * @see AccountTransactionController
 */
public abstract class AbstractAccountTransactionController extends AbstractController implements AccountTransactionController {

    protected AccountService accountService;

    private AuthService authService;

    public Customer getLoginCustomer() {
        return authService.getAccessingCustomer();
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
