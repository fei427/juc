package pers.liangshan.demos.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 功能描述: Callable接口
 * 有返回值的线程任务，通过FutureTask传递线程任务，获取返回值
 *
 * @auther: LiangShan
 * @date: 2020/3/29 20:43
 *
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new MyCallable());
        new Thread(task).start();
        //拿到返回值
        System.out.println(task.get());
    }
}

class MyCallable implements Callable{
    @Override
    public Object call() throws Exception {
        return 2020;
    }
}
