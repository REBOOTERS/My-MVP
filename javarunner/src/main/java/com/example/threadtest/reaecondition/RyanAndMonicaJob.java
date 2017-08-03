package com.example.threadtest.reaecondition;

/**
 * Created by engineer on 2017/5/29.
 */

public class RyanAndMonicaJob implements Runnable {
    private BankAccount mBankAccount = new BankAccount();

    public static void main(String[] args) {
        RyanAndMonicaJob job=new RyanAndMonicaJob();
        Thread one = new Thread(job,"Ryan");
        Thread two = new Thread(job,"Monica");
        one.start();
        two.start();
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            makeWithdraw(10);
            if (mBankAccount.getBalance() < 0){
                System.out.println("over drawn");
            }
        }
    }

    private synchronized void makeWithdraw(int i) {
        if (mBankAccount.getBalance() >= i) {
            System.out.println(Thread.currentThread().getName() + " is about to withdraw");
            System.out.println(Thread.currentThread().getName() + " is going to sleep");

            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " wake up");
            mBankAccount.withdraw(i);
            System.out.println(Thread.currentThread().getName() + " finish withdraw");
            System.out.println("Account's balance ="+mBankAccount.getBalance());
        } else {
            System.err.println("not enough for "+Thread.currentThread().getName());
        }
    }
}
