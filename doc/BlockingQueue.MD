##BlockingQueue-阻塞队列介绍
>
> BlockingQueue的示例demo较多，基于BlockingQueue可实现生产者消费者模型，Synchronized和Lock也能实现，在此统一汇总
>
1. [BlockingQueue-基本操作](../src/main/java/pers/liangshan/demos/juc/BlockingQueueDemo.java) 
2. [生产者消费者模型-基于Synchronized实现](../src/main/java/pers/liangshan/demos/juc/ProdCousumerDemoSync.java)（复习回顾）
3. [生产者消费者模型-基于Lock实现](../src/main/java/pers/liangshan/demos/juc/ProdCousumerDemoLock.java)（小练习：一个初始值为0的变量，两个线程交替操作，一个加1一个减1，循环五次）
4. [生产者消费者模型-基于Lock实现-熟悉condition的使用](../src/main/java/pers/liangshan/demos/juc/LockCondationDemo.java)（多线程按顺序调用，实现 A-->B-->C三个线程启动，要求：A打印5次，B打印10次，C打印15次，循环十次）
5. [生产者消费者模型-基于BlockingQueue实现](../src/main/java/pers/liangshan/demos/juc/ProdCousumerDemoBlockingQueue.java)
6. .....