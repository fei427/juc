package pers.liangshan.demos.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程按顺序调用，实现 A-->B-->C三个线程启动，要求：A打印5次，B打印10次，C打印15次，循环十次
 *
 * @auther: LiangShan
 * @date: 2020/3/29 13:32
 *
 */
public class LockCondationDemo {
    public static void main(String[] args) {

        PrintContion printContion = new PrintContion();

            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    printContion.printA();
                }
            },"A").start();
            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    printContion.printB();
                }
            },"B").start();
            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    printContion.printC();
                }
            },"C").start();

    }
}
class PrintContion{
    private int number = 1;//A：1，B：2，C:3
    Lock lock = new ReentrantLock();
    Condition cA = lock.newCondition();
    Condition cB = lock.newCondition();
    Condition cC = lock.newCondition();

    //打印A的方法
    public void printA(){
        lock.lock();
        try {
            // 判断
            while (number!=1){
                cA.await();
            }
            // 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //修改标志
            number = 2;
            //通知
            cB.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //打印A的方法
    public void printB(){
        lock.lock();
        try {
            // 判断
            while (number!=2){
                cB.await();
            }
            // 干活
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //修改标志
            number = 3;
            //通知
            cC.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //打印A的方法
    public void printC(){
        lock.lock();
        try {
            // 判断
            while (number!=3){
                cC.await();
            }
            // 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //修改标志
            number = 1;
            //通知
            cA.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
