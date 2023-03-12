package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.AuthService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.view.NewAccountView;

/**
 * The {@link NewAccountView} controller
 */
public class NewAccountController extends AbstractController {

    private Bank bank;
    private Integer newAccountId;
    private AccountService accountService;
    private CustomerService customerService;


    /**
     * Gets the new account id
     *
     * @return the new account id
     */
    public Integer getNewAccountId() {
        return newAccountId;
    }

    /**
     * Creates a new {@link Account}
     *
     * @see Controller#init()
     *
     */
    @Override
    public void init() {

        newAccountId = createAccount();
        super.init();
    }

    private int createAccount() {


        Account newAccount = accountService.create(AccountType.CHECKING);
        customerService.add(authService.getAccessingCustomer());
        return newAccount.getId();
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

}
