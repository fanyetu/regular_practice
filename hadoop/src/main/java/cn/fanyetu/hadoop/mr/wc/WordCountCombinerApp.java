package cn.fanyetu.hadoop.mr.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

/**
 * 使用Combiner进行提前聚合
 *
 * @author zhanghaonan
 * @date 2019/9/17
 */
public class WordCountCombinerApp {

    static {
        try {
            System.load("G:\\Hadoop\\hadoop-2.6.0-cdh5.16.2\\bin\\hadoop.dll");
        }catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);

        }
    }

    public static void main(String[] args) throws Exception {

        // 配置hadoop用户
        System.setProperty("HADOOP_USER_NAME", "hadoop");

        // 配置hdfs
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.1.136:8020");

        // 创建一个Job，并传入configuration
        Job job = Job.getInstance(configuration);

        // 设置Job相关的参数：主类
        job.setJarByClass(WordCountApp.class);

        // 设置Job相关的参数：Mapper和Reducer对应的类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 设置Combiner的class，这里Combiner的操作和reduce的操作是一样的
        job.setCombinerClass(WordCountReducer.class);

        // 设置Job相关的参数：Mapper的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 设置Job相关的参数：Reducer的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 判断输出路径是否存在，如果存在则删除
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.136:8020"), configuration, "hadoop");
        Path output = new Path("/wordcount/output");
        if (fileSystem.exists(output)) {
            fileSystem.delete(output, true);
        }

        // 设置输入和输出的文件
        FileInputFormat.setInputPaths(job, new Path("/wordcount/input"));
        FileOutputFormat.setOutputPath(job, output);

        // 提交job
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : -1);
    }
}
