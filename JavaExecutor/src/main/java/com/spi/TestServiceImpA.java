package com.spi;

public class TestServiceImpA implements TestService {
    @Override
    public String who() {
        return TestServiceImpA.class.getSimpleName();
    }

    @Override
    public void run() {
        System.out.println("ImplA is run");
    }
}
