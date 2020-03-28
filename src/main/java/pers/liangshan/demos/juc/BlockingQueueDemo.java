package pers.liangshan.demos.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列：顾名思义,首先它是一个队列,则必须是先进先出的FIFO，常见有以下七种
 * 1、ArrayBlockingQueue: 由数组结构组成的有界阻塞队列.
 * 2、LinkedBlockingDeque: 由链表结构组成的有界(但大小默认值Integer>MAX_VALUE)阻塞队列.
 * 3、PriorityBlockingQueue:支持优先级排序的无界阻塞队列.
 * 4、DelayQueue: 使用优先级队列实现的延迟无界阻塞队列.
 * 5、SynchronousQueue:不存储元素的阻塞队列,也即是单个元素的队列.
 * 6、LinkedTransferQueue:由链表结构组成的无界阻塞队列.
 * 7、LinkedBlockingDeque:由了解结构组成的双向阻塞队列.
 * <p>
 * 为什么需要使用BlockingQueue？
 * 好处是我们不需要关心什么时候需要阻塞线程,什么时候需要唤醒线程,因为BlockingQueue都一手给你包办好了
 * 在concurrent包 发布以前,在多线程环境下,我们每个程序员都必须自己去控制这些细节,尤其还要兼顾效率和线程安全,而这会给我们的程序带来不小的复杂度.
 * <p>
 * 操作方法：共四组：抛异常，特殊值（true,false）,阻塞，超时
 * 插入： add(e),offer(e),put(e),offer(e,time,unit)
 * 移处： remove(),poll(),take(),poll(time,unit)
 * 检查： element(),peek(),无，无
 *
 * @auther: LiangShan
 * @date: 2020/3/28 21:30
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        // add(e)方法
        BlockingQueue aadBlockingQueue = new ArrayBlockingQueue(3);
        aadBlockingQueue.add("aaa");
        aadBlockingQueue.add("bbb");
        aadBlockingQueue.add("ccc");
        //添加第四个：抛出异常：IllegalStateException: Queue full
        //System.out.println(blockingQueue.add("ddd"));

        // offer(e)方法
        BlockingQueue offerBlockingQueue = new ArrayBlockingQueue(3);
        offerBlockingQueue.offer("aaa");
        offerBlockingQueue.offer("bbb");
        offerBlockingQueue.offer("ccc");
        //添加第四个：不抛出异常,返回：false
        System.out.println(offerBlockingQueue.offer("ddd"));

        // put(e)方法
        BlockingQueue putBlockingQueue = new ArrayBlockingQueue(3);
        putBlockingQueue.put("aaa");
        putBlockingQueue.put("bbb");
        putBlockingQueue.put("ccc");
        //添加第四个：阻塞
        // offerBlockingQueue.put("ddd");

        // offer(e,time,unit)方法
        BlockingQueue offerTimeBlockingQueue = new ArrayBlockingQueue(3);
        offerTimeBlockingQueue.offer("aaa", 2, TimeUnit.SECONDS);
        offerTimeBlockingQueue.offer("bbb", 2, TimeUnit.SECONDS);
        offerTimeBlockingQueue.offer("ccc", 2, TimeUnit.SECONDS);
        //添加第四个:阻塞两秒，放入不了，主动停止
        offerTimeBlockingQueue.offer("ccc", 2, TimeUnit.SECONDS);


    }

}
