package cn.fanyetu.hadoop.mr.wc;

import org.apache.avro.ValidateAll;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * KEYIN：Map任务读数据的Key类型，一般情况是指offset，是每行数据起始位置的偏移量，通常是Long类型
 * VALUEIN：Map任务读数据的value类型，通常就是一行行的字符串，String类型
 *
 * KEYOUT：map方法实现输出的key的类型
 * VALUEOUT：map方法实现输出的value的类型
 *
 * Long,String等是Java里面的数据类型
 * Hadoop自定义数据类型：序列化和反序列化
 *  LongWritable => Long
 *  Text => String
 *  IntWritable => Integer
 *
 * @author zhanghaonan
 * @date 2019/9/16
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 把value对应的行数据，按照指定的分隔符进行拆分
        String[] words = value.toString().split("\t");

        // 输出
        for (String word : words) {
            // (hello, 1) (world, 1)
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
