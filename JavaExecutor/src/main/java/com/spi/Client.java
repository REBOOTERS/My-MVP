package com.spi;

import java.util.ServiceLoader;

public class Client {
    public static void main(String[] args) {
        ServiceLoader<TestService> services = ServiceLoader.load(TestService.class);
        for (TestService service : services) {
            System.out.println("service is "+service.who());
            service.run();
        }
    }
}
