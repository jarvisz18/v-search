package cn.ixan.search.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CountTaskForkJoinTest extends RecursiveTask<Long> {
    private static final int threshold =100;
    private long start;
    private long end;

    public CountTaskForkJoinTest(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = end-start <= threshold;
        if(canCompute){
            for(long i=start;i<= end;i++){
                sum+=i;
            }
        }else {
            long middle = (start + end) /2;
            CountTaskForkJoinTest task1 = new CountTaskForkJoinTest(start, middle);
            CountTaskForkJoinTest task2 = new CountTaskForkJoinTest(middle, end);

            task1.fork();
            task2.fork();

            long result1 = task1.join();
            long result2 = task2.join();
            sum = result1 + result2;
        }
        return sum;
    }

    /**
     * ForkJoin实现,返回计算结果
     * @param start 起始值
     * @param end 结束值
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static long forkJoinTest(long start, long end) throws InterruptedException, ExecutionException {
        ForkJoinPool pool = new ForkJoinPool();
        CountTaskForkJoinTest task = new CountTaskForkJoinTest(start, end);

        Future<Long> result = pool.submit(task);
        return result.get();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start_index = 1;
        long end_index = 50;

        long ret = forkJoinTest(start_index, end_index);
        System.out.println("result: " + ret);

    }
}
