package com.avaj.threadtest.executor;

import java.util.concurrent.Executor;

/**
 * Created by rookie on 2017/2/21.
 */

public class SubExecutor implements Executor {
    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
