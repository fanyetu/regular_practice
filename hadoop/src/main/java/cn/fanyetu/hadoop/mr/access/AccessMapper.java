package cn.fanyetu.hadoop.mr.access;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Access的自定义mapper
 *
 * @author zhanghaonan
 * @date 2019/9/17
 */
public class AccessMapper extends Mapper<LongWritable, Text, Text, Access> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 拆分日志，组装返回
        String[] arr = value.toString().split("\t");

        String phone = arr[1];
        long up = Long.parseLong(arr[arr.length - 3]);
        long down = Long.parseLong(arr[arr.length - 2]);

        context.write(new Text(phone), new Access(phone, up, down));
    }
}
