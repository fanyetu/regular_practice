package cn.fanyetu.concurrent.cachetest;

/**
 * 一个长时间的计算
 *
 * Created by Administrator on 2017/4/5.
 */
public class ExpensiveComputable implements Computable<String,Integer> {

    @Override
    public Integer compute(String arg) throws InterruptedException {

        //经过长时间的计算之后
        Thread.sleep(1000);

        return Integer.parseInt(arg);
    }

}
