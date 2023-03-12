package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.*;
import org.academiadecodigo.javabank.controller.transaction.DepositController;
import org.academiadecodigo.javabank.controller.transaction.WithdrawalController;
import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.*;
import org.academiadecodigo.javabank.view.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for wiring the objects dependencies
 */
public class Bootstrap {

    private CustomerServiceImpl customerService = new CustomerServiceImpl();
    public void generateTestData() {

        //Bank bank = new Bank();

        //bank.setAccountManager(accountManager);

        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        Customer c3 = new Customer(3, "Bruno");
        customerService.add(c1);
        customerService.add(c2);
        customerService.add(c3);


        //return bank;
    }

    /**
     * Wires the necessary object dependencies
     *
     * @param bank the bank to wire
     * @return the login controller
     */
    public LoginController wireObjects() {

        // attach all input to standard i/o
        Prompt prompt = new Prompt(System.in, System.out);

        //create services
        AccountServiceImpl accountService = new AccountServiceImpl();
        //customerService = new CustomerServiceImpl();
        AuthServiceImpl authService = new AuthServiceImpl();

        accountService.setAuthService(authService);
        authService.setCustomerService(customerService);

        // wire login controller and view
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        loginController.setView(loginView);
        loginController.setAuthService(authService);
        loginController.setCustomerService(customerService);
        //loginController.setBank(bank);
        //loginView.setBank(bank);
        loginView.setLoginController(loginController);
        loginView.setPrompt(prompt);


        // wire main controller and view
        MainController mainController = new MainController();
        MainView mainView = new MainView();
        //mainView.setBank(bank);
        mainView.setPrompt(prompt);
        mainView.setMainController(mainController);
        mainController.setView(mainView);
        mainController.setAuthService(authService);
        loginController.setNextController(mainController);

        // wire balance controller and view
        BalanceController balanceController = new BalanceController();
        BalanceView balanceView = new BalanceView();
        balanceView.setBalanceController(balanceController);
        balanceController.setAuthService(authService);
        balanceController.setView(balanceView);
        //balanceView.setBank(bank);

        // wire new account controller and view
        NewAccountView newAccountView = new NewAccountView();
        NewAccountController newAccountController = new NewAccountController();
        //newAccountController.setBank(bank);
        newAccountController.setView(newAccountView);
        newAccountController.setAccountService(accountService);
        newAccountController.setCustomerService(customerService);
        newAccountController.setAuthService(authService);
        newAccountView.setNewAccountController(newAccountController);

        // wire account transactions controllers and views
        DepositController depositController = new DepositController();
        WithdrawalController withdrawalController = new WithdrawalController();
        AccountTransactionView depositView = new AccountTransactionView();
        AccountTransactionView withdrawView = new AccountTransactionView();
        depositController.setAccountService(accountService);
        depositController.setView(depositView);
        depositController.setAuthService(authService);
        withdrawalController.setAccountService(accountService);
        withdrawalController.setView(withdrawView);
        withdrawalController.setAuthService(authService);
        //depositView.setBank(bank);
        depositView.setPrompt(prompt);
        depositView.setTransactionController(depositController);
        //withdrawView.setBank(bank);
        withdrawView.setPrompt(prompt);
        withdrawView.setTransactionController(withdrawalController);

        // setup the controller map
        Map<Integer, Controller> controllerMap = new HashMap<>();
        controllerMap.put(UserOptions.GET_BALANCE.getOption(), balanceController);
        controllerMap.put(UserOptions.OPEN_ACCOUNT.getOption(), newAccountController);
        controllerMap.put(UserOptions.DEPOSIT.getOption(), depositController);
        controllerMap.put(UserOptions.WITHDRAW.getOption(), withdrawalController);

        mainController.setControllerMap(controllerMap);

        return loginController;
    }
}
