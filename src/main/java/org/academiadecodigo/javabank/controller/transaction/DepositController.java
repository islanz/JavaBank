package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.AuthService;

/**
 * A controller used for deposit transactions
 * @see AbstractAccountTransactionController
 */
public class DepositController extends AbstractAccountTransactionController {

    /**
     * Deposits an amount on the account with the given id
     *
     * @see AbstractAccountTransactionController#submitTransaction(int, double)
     */


    @Override
    public void submitTransaction(int accountId, double amount) {
        accountService.deposit(accountId, amount);
        //bank.getAccountManager().deposit(accountId, amount);
    }

}
