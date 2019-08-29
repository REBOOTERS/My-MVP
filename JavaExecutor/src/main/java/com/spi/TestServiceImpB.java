package com.spi;

public class TestServiceImpB implements TestService {
    @Override
    public String who() {
        return TestServiceImpB.class.getSimpleName();
    }

    @Override
    public void run() {
        System.out.println("ImplB is run");
    }
}
