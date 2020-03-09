package cn.fanyetu.hadoop.mr.access;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Access的自定义Reducer
 *
 * 使用NullWritable作为结果的key，这样输出结果的时候就不会输出key
 *
 * @author zhanghaonan
 * @date 2019/9/17
 */
public class AccessReducer extends Reducer<Text, Access, NullWritable, Access> {

    @Override
    protected void reduce(Text key, Iterable<Access> values, Context context) throws IOException, InterruptedException {
        long ups = 0;
        long downs = 0;

        for (Access value : values) {
            ups += value.getUp();
            downs += value.getDown();
        }

        context.write(NullWritable.get(), new Access(key.toString(), ups, downs));
    }
}
