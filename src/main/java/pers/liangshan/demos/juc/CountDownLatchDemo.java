package pers.liangshan.demos.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch:让一些线程阻塞直到另外一些完成后才被唤醒。
 * CountDownLatch主要有两个方法,当一个或多个线程调用await方法时,调用线程会被阻塞.
 * 其他线程调用countDown方法计数器减1(调用countDown方法时线程不会阻塞),
 * 当计数器的值变为0,因调用await方法被阻塞的线程会被唤醒,继续执行
 *
 *  生活类比：六个同学上晚自习离开教室了，最后班长才能关门
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
