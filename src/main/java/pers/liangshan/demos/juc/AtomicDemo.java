package pers.liangshan.demos.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述: Atomic保证原子性
 *
 * @auther: LiangShan
 * @date: 2020/3/29 18:39
 */
public class AtomicDemo {

    public static void main(String[] args) {
        AtomicCount atomicCount = new AtomicCount();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    atomicCount.increaseCount();
                }
            }, String.valueOf(i)).start();
        }
        //需要等待上面的十个线程执行结束
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "最后的atomicCount值\t" + atomicCount.atomicInteger);
    }
}

class AtomicCount {

    //使用JUC提供的Atomic类保证原子行
    AtomicInteger atomicInteger = new AtomicInteger();

    public void increaseCount() {
        atomicInteger.incrementAndGet();
    }

}
