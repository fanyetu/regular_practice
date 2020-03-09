package cn.fanyetu.hadoop.mr.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author zhanghaonan
 * @date 2019/9/16
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    /**
     * map输出到reducer之后，按照相同的key分发到同一个reducer上执行
     *
     * reducer1: (hello, 1) (hello, 1) (hello, 1)
     *
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable value : values) {
            count += value.get();
        }

        context.write(key, new IntWritable(count));
    }
}
