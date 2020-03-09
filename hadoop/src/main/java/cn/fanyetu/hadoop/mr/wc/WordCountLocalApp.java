package cn.fanyetu.hadoop.mr.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Driver：配置Mapper，Reducer的相关属性
 *
 * @author zhanghaonan
 * @date 2019/9/17
 */
public class WordCountLocalApp {

    static {
        try {
            System.load("G:\\Hadoop\\hadoop-2.6.0-cdh5.16.2\\bin\\hadoop.dll");
        }catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);

        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();

        // 创建一个Job，并传入configuration
        Job job = Job.getInstance(configuration);

        // 设置Job相关的参数：主类
        job.setJarByClass(WordCountLocalApp.class);

        // 设置Job相关的参数：Mapper和Reducer对应的类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 设置Job相关的参数：Mapper的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 设置Job相关的参数：Reducer的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置输入和输出的文件
        FileInputFormat.setInputPaths(job, new Path("hadoop/input"));
        FileOutputFormat.setOutputPath(job, new Path("hadoop/output"));

        // 提交job
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : -1);
    }
}
