package pers.liangshan.demos.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch小demo:六个同学上晚自习离开教室了，最后班长才能关门
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch =new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+"\t 上晚自习，离开教室");
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 班长最后关门");
    }
}
