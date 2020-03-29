package pers.liangshan.demos.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述: 基于BlockingQueue实现生产者消费者模型
 *
 * @auther: LiangShan
 * @date: 2020/3/29 15:34
 */
public class ProdCousumerDemoBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource(new ArrayBlockingQueue(3));
        //t1线程生产
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "生产线程启动。。");
            try {
                myResource.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "prod").start();
        //t线程消费
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "消费线程启动。。");
            try {
                myResource.cousumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "cousumer").start();
        System.out.println();
        TimeUnit.SECONDS.sleep(5);
        System.out.println();
        System.out.println("5秒钟时间到，叫停生产");
        myResource.stop();
    }

}

//共享资源类
class MyResource {
    //默认开启，表示进行生产+消费
    private volatile boolean flag = true;
    //队列中可存放的元素
    private AtomicInteger atomicInteger = new AtomicInteger();
    //阻塞队列
    private BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    //生产方法
    public void prod() throws InterruptedException {
        String data = "";
        boolean relFlag;
        while (flag) {
            System.out.println();
            System.out.println();
            //标志为ture-开启生产
            data = atomicInteger.incrementAndGet() + "";
            relFlag = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (relFlag) {
                System.out.println(Thread.currentThread().getName() + "添加队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "添加队列" + data + "失败");
            }
            //停一秒：一秒添加一个
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\tflag = false，表示生产停止");
    }

    public void cousumer() throws InterruptedException {
        String result = "";
        while (flag) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                //取到的结果为null或者为空，停止线程
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t两秒没消费到,退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列" + result + "成功");
        }
        System.out.println("消费停止。。");
    }

    public void stop() {
        flag = false;
    }
}
