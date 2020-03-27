package pers.liangshan.demos.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore：信号量
 * 信号量的主要用户两个目的：
 *  一个是用于多喝共享资源的相互排斥使用,
 *  另一个用于并发资源数的控制.
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟三个停车位

        for (int i = 1; i < 10; i++) {//模拟十辆汽车
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    //线程暂停3秒
                    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName()+"\t停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //三秒车走了，释放车位
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
