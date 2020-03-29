package pers.liangshan.demos.juc;

/**
 * 功能描述: Atomic不保证原子性的demo
 *
 * @auther: LiangShan
 * @date: 2020/3/29 18:19
 */
public class ValatileNotAtomicDemo {

    public static void main(String[] args) {
        MyCount myCount = new MyCount();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    myCount.increaseCount();
                }
            }, String.valueOf(i)).start();
        }
        //需要等待上面的十个线程执行结束
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"最后的count值\t"+myCount.count);
    }
}

class MyCount {
    //加上volatile不能保证最终结果为200000
    volatile int count = 0;

    public void increaseCount() {
        count++;
    }
}

