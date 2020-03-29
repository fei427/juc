package pers.liangshan.demos.juc;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 功能描述: 生产者消费者模型：多个生产者和消费者同时生产和消费
 * 缺点：在于在生产者唤醒消费者或者消费者唤醒生产者时，由于生产者和消费者使用同一个锁，所以生产者也会将生产者唤醒，消费者也会将消费者唤醒。
 * 举例：假设现在池子满了，然后有3个生产者被阻塞，现在一个消费者拿走一个item，调用notify，此时一个被阻塞的生产者被唤醒了。这个生产者向池子里放入一个产品，并执行notify意图唤醒被阻塞的消费者，此时这个notify有可能唤醒另外2个被阻塞的生产者中的一个
 * Condition可以指定多个等待的条件，因此使用Condition可以解决这一问题
 * 参考博客：https://www.cnblogs.com/yfzhou/p/10996801.html
 * @auther: LiangShan
 * @date: 2020/3/29 12:34
 *
 */
public class ProdCousumerDemoSync {
    public static void main(String[] args) {
        Pool pool = new Pool(6);
        Executor executor = Executors.newCachedThreadPool();
        //十个生产者线程
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    pool.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        //十个消费者线程
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    pool.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
//资源池
class Pool {

    private int MAX;
    private int cnt = 0;

    public Pool(int MAX) {
        this.MAX = MAX;
    }

    //加锁的生产方法
    public synchronized void produce() throws InterruptedException {
        while (cnt == MAX) {
            wait();
        }
        System.out.println("Produce one.. Now:" + ++cnt);
        notify();
    }

    //加锁的消费方法
    public synchronized void consume() throws InterruptedException {
        while (cnt == 0) {
            wait();
        }
        System.out.println("Consume one.. Now:" + --cnt);
        notifyAll();
    }
}
