package pers.liangshan.demos.juc;

/**
 * DCL单例的实现
 */
public class SingletonDCLDemo {

    private static volatile SingletonDCLDemo instance = null;

    //私有化构造方法
    private SingletonDCLDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法");
    }

    //提供的获取实例的方法
    public static SingletonDCLDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDCLDemo.class) {
                if (instance == null) {
                    instance = new SingletonDCLDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDCLDemo s = SingletonDCLDemo.getInstance();
                System.out.println(s);
            }, String.valueOf(i)).start();
        }
    }

}
