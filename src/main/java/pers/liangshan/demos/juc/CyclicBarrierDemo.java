package pers.liangshan.demos.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier的字面意思是可循环(Cyclic) 使用的屏障(barrier).
 * 它要做的事情是,让一组线程到达一个屏障(也可以叫做同步点)时被阻塞,知道最后一个线程到达屏障时,
 * 屏障才会开门,所有被屏障拦截的线程才会继续干活,线程进入屏障通过CyclicBarrier的await()方法.
 *
 * 生活类比：长跑比赛，所有运动员准备就绪后，裁判开枪，开始比赛
 *
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("裁判开枪，比赛开始。。"));

        for (int i = 1; i <= 8; i++) {
            int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t"+tempInt+"号选手准备就绪");
                try {
                    //屏障拦截
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
