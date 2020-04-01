package pers.liangshan.demos.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述: 线程池demo
 *
 * @auther: LiangShan
 * @date: 2020/4/1 23:12
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {

       //ExecutorService threadPool = Executors.newFixedThreadPool(10);//固定数量的线程
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//只有一个线程
        ExecutorService threadPool2 = Executors.newCachedThreadPool();//不固定数据，带缓冲的线程
        try {
            //模拟二十个人办理业务
            for (int i = 0; i < 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        } finally {
            threadPool.shutdown();
        }

    }
}
