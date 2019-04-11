package com.threads;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * @author: zhuyongging
 * @since: 2019-04-11
 * <p>
 * 线程可以在ExecutorService以并发方式调用另一个线程执行耗时操作的同时，去执行一些其他的任务。
 * 接着，如果你已经运行到没有异步操作的结果就无法继续任何有意义的工作时，
 * 可以调用它的get方法去获取操作的结果。如果操作已经完成，该方法会立刻返回操作的结果，
 * 否则它会阻塞你的线程，直到操作完成，返回相应的结果。
 * 如果该长时间运行的操作永远不返回了会怎样?Future提供了一个无需任何参数的get方法，
 * 推荐使用重载版本的get方法，它接受一个超时的参数，可以定义线程等待Future结果的最长时间，避免无休止的等待。
 */
public class FutureDemo {
    private static final int BASE = 1000000;

    public static void main(String[] args) {
//        FutureDemo();
//        SyscDemo();
        AsyncDemo();
    }

    private static void AsyncDemo() {
        long start = System.nanoTime();
//        Future<Double> future = getPriceAsync();
        // 使用工厂方式获得 Future
        Future<Double> future = getPriceAsyncBySupplyAsync();
        long invocationTime = (System.nanoTime() - start) / BASE;
        System.out.println("Invocation retured after " + invocationTime + " msecs");
        System.out.println("do something else");


        try {
            double price = future.get(3, TimeUnit.SECONDS);
            System.out.println("price is " + price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        long retrievalTime = (System.nanoTime() - start) / BASE;
        System.out.println("price returned after " + retrievalTime + " msecs");
    }

    private static void SyscDemo() {
        long start = System.nanoTime();
        double price = 0;
        try {
            price = getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long invocationTime = (System.nanoTime() - start) / BASE;
        System.out.println("Invocation retured after " + invocationTime + " msecs");
        System.out.println("do something else");
        System.out.println("price is " + price);
        long retrievalTime = (System.nanoTime() - start) / BASE;
        System.out.println("price returned after " + retrievalTime + " msecs");
    }

    // <editor-fold defaultstate="collapsed" desc="future demo">
    private static void FutureDemo() {
        // thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws InterruptedException {
                return doComputation();
            }
        });

        System.out.println("this is sysc stuff");

        try {
            Integer result = future.get(1, TimeUnit.SECONDS);
            System.out.println("future back,result is " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.err.println("Future 对象完成之前已过期");
            e.printStackTrace();
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="模拟耗时运算">
    private static Integer doComputation() throws InterruptedException {
        Thread.sleep(2000);
        return new Random().nextInt();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getPrice 同步">
    private static double getPrice() throws InterruptedException {
        int value = doComputation();
        return value / 100.0f;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getPrice 异步">
    private static Future<Double> getPriceAsync() {
        CompletableFuture<Double> future = new CompletableFuture<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                double price = 0;
                try {
                    price = getPrice();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    future.completeExceptionally(e);
                }
                future.complete(price);
            }
        });
        return future;
    }
    // </editor-fold>

    /**
     * supplyAsync方法接受一个生产者(Supplier)作为参数，返回一个CompletableFuture对象，
     * 该对象完成异步执行后会读取调用生产者方法的返回值。生产者方法会交由ForkJoinPool池中的某个执行线程(Executor)运行，
     * 但是你也可以使用supplyAsync方法的重载版本，传递第二个参数指定不同的执行线程执行生产者方法。
     *
     * @return
     */
    private static Future<Double> getPriceAsyncBySupplyAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getPrice();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return (double) 0;
            }
        });
    }
}
