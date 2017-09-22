package com.example.threadtest.reaecondition;

/**
 * Created by engineer on 2017/5/29.
 */

public class BankAccount {
    private int balance = 50;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amoutn) {
        balance = balance - amoutn;
    }
}
