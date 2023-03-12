package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService {

    private Map<Integer, Account> accountMap;
    private AccountFactory accountFactory;
    private AuthService authService;


    public AccountServiceImpl() {
        accountFactory = new AccountFactory();
        accountMap = new HashMap<>();
    }

    @Override
    public Account create(AccountType accountType) {
        Account account = accountFactory.createAccount(accountType);
        accountMap.put(account.getId(), account);
        authService.getAccessingCustomer().addAccount(account);
        return account;
    }

    @Override
    public void deposit(int id, double amount) {
        accountMap.get(id).credit(amount);
    }

    @Override
    public void withdraw(int id, double amount) {
        Account account = accountMap.get(id);

        if (!account.canWithdraw()) {
            return;
        }

        accountMap.get(id).debit(amount);
    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {
        Account srcAccount = accountMap.get(srcId);
        Account dstAccount = accountMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }
    }
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

}
