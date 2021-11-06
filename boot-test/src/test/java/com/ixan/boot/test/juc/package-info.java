package com.ixan.boot.test.juc;
/*
 * 1.JUC 概述
 * juc 并发编程工具包
 *
 * 进程和线程的概念
 * 进程指的是一个正在运行的应用程序
 * 进程与线程
 * 进程的状态
 * wait和sleep
 * 并发和并行
 * 管程
 *
 * 用户线程和守护线程
 * 用户线程：自定义线程
 * 守护线程：运行在后台，比如垃圾回收线程
 *
 * 2.Lock接口
 * 2.1 synchronized
 * 2.2 什么是Lock接口
 *
 * 3.线程间通信
 * 3.1 使用wait、notify、notifyAll
 * 3.2 使用lock.newCondition()中的方法
 *
 * 4.线程间定制化通信
 * 4.1 线程优先级
 *
 *
 * synchronized 实现同步的基础：Java 中的每一个对象都可以作为锁
 * 具体表现为以下三种形式：
 * 对于普通同步方法，锁是当前实例对象
 * 对于静态同步方法，锁是当前类的Class 对象
 * 对于同步方法，锁是synchronized 括号里配置的对象
 *
 * 公平锁和非公平锁
 *
 * 可重入锁和不可重入锁
 * synchronized 显式可重入锁
 * Lock 可重入锁
 *
 * 死锁
 * 1.什么是死锁
 * 2、产生死锁的原因
 * 第一 系统资源不足
 * 第二 进程运行推进顺序不合适
 * 第三 资源分配不当
 *
 * 3、验证是否是死锁
 * (1).使用 jps -l 获取进程号
 * (2).使用 jstack [pid] 查看是否存在死锁
 *
 * 创建线程的多种方式
 * 1、继承 Thread 类
 * 2、实现 Runnable 接口
 * 3、使用 Callable 接口
 * 4、线程池方式
 *
 * 8、JUC 强大的辅助类
 * CountDownLatch
 * CyclicBarrier
 * Semaphore
 * Exchanger
 *
 * 9、ReentrantReadWriteLock 读写锁
 *
 * 10、BlockingQueue 阻塞队列
 * 10.1、阻塞队列概述
 * 10.2、阻塞队列的架构
 * 10.3、阻塞队列分类
 * 10.4、阻塞队列核心方法
 *
 * 11、ThreadPool 线程池
 * 11.1、线程池概述
 * 11.2、线程池架构
 * 11.3、线程池使用方式
 * 11.4、线程池底层原理
 * 11.5、线程池的七个参数
 * 核心线程数、最大线程数、线程存活时间、阻塞队列、线程工厂、拒绝策略
 *
 * 11.6、线程池底层的工作流程
 * 11.7、自定义线程池
 *
 * 12、Fork/Join 分支合并框架
 *
 * 13.CompletableFuture 异步回调
 *
 * */