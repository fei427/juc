package pers.liangshan.demos.juc;

import java.util.concurrent.TimeUnit;

public class DeadLock {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLock(lockA,lockB),"ThredA").start();
        new Thread(new HoldLock(lockB,lockA),"ThredB").start();
    }
}

class HoldLock implements Runnable{
    //两把锁
    private String lockA;
    private String lockB;

    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t持有锁:"+lockA+"\t尝试获得锁:"+lockB);
            try { TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace(); }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t持有锁:"+lockB+"\t尝试获得锁:"+lockA);
            }
        }
    }
}
