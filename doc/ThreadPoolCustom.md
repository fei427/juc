## 自定义线程池
> 生产环境不要使用线程池的工具类Executors，线程数量为Integer.MAX_VALUE 容易OOM
所以使用线程池时应自定义线程池，通过 new ThredPoolExecutor()构造方法创建。

***
## 线程池最大线程数如何选择？
> 1. CPU密集型 