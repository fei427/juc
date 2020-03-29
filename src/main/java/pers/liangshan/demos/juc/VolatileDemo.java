package pers.liangshan.demos.juc;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 验证Volatile的可见性
 * Volatile：保证可见姓，禁止指令重排，但不保证原子性
 *
 * @auther: LiangShan
 * @date: 2020/3/29 17:44
 */
public class VolatileDemo {

    public static void main(String[] args) {

        VolatileTest volatileTest = new VolatileTest();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "线程启动");
            volatileTest.setFlag();
        }, "t1").start();

        System.out.println(Thread.currentThread().getName() + "\t" + "线程开始，flag：" + volatileTest.flag);
        while (!volatileTest.flag) {
            //主线程一直循环，无法感知到子线程将flag改为true的变化
        }
        //flag加上volatile修饰后，flag才对主线程可见，可正常退出
        System.out.println(Thread.currentThread().getName() + "\t" + "线程结束");
    }
}

class VolatileTest {
    //加上volatile修饰后，主线程可退出，打印：main线程结束
    //volatile boolean flag = false;
    boolean flag = false;

    public void setFlag() {
        this.flag = true;
        System.out.println(Thread.currentThread().getName() + "\t" + "线程调用了setFlag()方法，flag：" + this.flag);
    }
}
