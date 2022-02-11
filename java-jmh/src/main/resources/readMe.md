#### 注解说明:
````java
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
````



##### @BenchmarkMode(Mode.AverageTime) 

表示统计平均响应时间，不仅可以用在类上，也可用在测试方法上。

除此之外还可以取值：

+ Throughput：统计单位时间内可以对方法测试多少次。
+ SampleTime：统计每个响应时间范围内的响应次数，比如 0-1ms，3次；1-2ms，5次。
+ SingleShotTime：跳过预热阶段，直接进行一次****微基准测试。



##### @State(Scope.Thread)

每个进行基准测试的线程都会独享一个对象示例。

除此之外还能取值：

+ Benchmark：多线程共享一个示例。
+ Group：线程组共享一个示例，在测试方法上使用 @Group 设置线程组。



##### @Fork(1)：

表示开启一个线程进行测试。



##### OutputTimeUnit(TimeUnit.MILLISECONDS)

输出的时间单位，这里写的是毫秒。



##### @Warmup(iterations = 3)

微基准测试前进行三次预热执行，也可用在测试方法上。



##### @Measurement(iterations = 5)

进行 5 次微基准测试，也可用在测试方法上。



在两个测试方法上只使用了一个注解 @Benchmark，这个注解表示这个方法是要进行基准测试的方法，
它类似于 Junit 中的 @Test 注解。上面还提到某些注解还可以用到测试方法上，
也就是使用了 @Benchmark 的方法之上，如果类上和测试方法同时存在注解，会以方法上的注解为准。



#### 参考链接

- https://www.ibm.com/developerworks/cn/java/j-benchmark1.html
- http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/
