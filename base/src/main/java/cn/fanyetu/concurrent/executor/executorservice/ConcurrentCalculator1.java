package cn.fanyetu.concurrent.executor.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并行计算
 *
 * Created by Administrator on 2017/4/17.
 */
public class ConcurrentCalculator1 {

    private ExecutorService es;
    private int cpuCoreNumber;
    private List<Future<Long>> tasks = new ArrayList<>();

    public ConcurrentCalculator1() {
        cpuCoreNumber = Runtime.getRuntime().availableProcessors();//获取cpu核心数
        //System.out.println(cpuCoreNumber);
        this.es = Executors.newFixedThreadPool(cpuCoreNumber);//创建一个固定数目的线程池
    }

    //内部类
    class SumCalculator implements Callable<Long>{

        private int[] numbers;
        private int start;
        private int end;

        public SumCalculator(int[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() throws Exception {
            Long sum = 0L;
            for (int i=start;i<end;i++){
                sum += numbers[i];
            }
            return sum;
        }
    }

    public Long sum(final int[] numbers){
        //根据cpu核心数查分任务，创建FutureTask并提交到Executor
        for (int i=0;i<cpuCoreNumber;i++){
            int increment = numbers.length / cpuCoreNumber + 1;
            int start = increment * i;
            int end = increment * i + increment;
            if (end > numbers.length)
                end = numbers.length;
            SumCalculator sumCalculator = new SumCalculator(numbers, start, end);
            FutureTask<Long> task = new FutureTask<>(sumCalculator);
            tasks.add(task);
            if (!es.isShutdown()){
                es.submit(task);
            }
        }
        return getResult();
    }

    /**
     * 迭代每个子任务，获取部分和，相加返回
     * @return
     */
    public Long getResult() {
        Long result = 0L;
        for (Future<Long> task : tasks){
            try {
                //如果计算未完成则阻塞
                Long aLong = task.get();
                result += aLong;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void close(){
        es.shutdown();
    }


    //测试
    public static void main(String[] args) {
        int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 10, 11 };
        ConcurrentCalculator1 calculator1 = new ConcurrentCalculator1();
        Long result = calculator1.sum(numbers);

        System.out.println(result);

        calculator1.close();
    }
}
