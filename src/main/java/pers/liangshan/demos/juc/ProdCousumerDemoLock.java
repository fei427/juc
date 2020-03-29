package pers.liangshan.demos.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述: 基于Lock实现生产者消费者模型
 * 一个初始值为0的变量，两个线程交替操作，一个加1一个减一，循环五次
 *
 *  Synchronized和Lock区别：
 *  1、原始构成：
 *     Synchronized是关键字，属于JVM层面的锁：底层基于JVM指令：monitorenter和monitorexit，需结合notify和wait方法使用
 *     Lock是API层面的锁
 *
 *  2、使用方法：
 *     Synchronized不需要手动释放锁
 *     Lock则需要手动的释放锁，否则容易方法死锁，lock和unLock方法需要配合try/finally语句块完成
 *
 *  3、等待是否可中断：
 *     Synchronized不可中断
 *     Lock可以中断， 1.超时方法：lock.tryLock(Long timeOut,TimeUnit unit);
 *                   2.lock.lockInterruptibly()方法放入代码块中，调用interrupt方法可中断
 *
 *  4、是否公平
 *      Synchronized：非公平的可重入锁
 *      Lock：公平和非公平锁都能设置
 *
 *  5、锁绑定多个Condition
 *     Synchronized：没有
 *     Lock：可实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不想Synchronized要么唤醒一个线程要么唤醒多个线程
 *
 *
 * @auther: LiangShan
 * @date: 2020/3/29 0:31
 */
public class ProdCousumerDemoLock {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareResource.increment();
            }
        },"t1").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareResource.decrement();
            }
        },"t2").start();
    }


}

//共享资源类
class ShareResource {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    //添加资源的方法
    public void increment(){
        lock.lock();
        try {
            // 1、判断
            while (number !=0){
                //阻塞，不能生产
                condition.await();
            }
            //2、生产
            number++;
            System.out.println(Thread.currentThread().getName()+"\t:"+number);
            //3、通知 唤醒
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //添加资源的方法
    public void decrement(){
        lock.lock();
        try {
            // 1、判断
            while (number ==0){
                //阻塞，无法获取资源
                condition.await();
            }
            //2、生产
            number--;
            System.out.println(Thread.currentThread().getName()+"\t:"+number);
            //3、通知 唤醒
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
