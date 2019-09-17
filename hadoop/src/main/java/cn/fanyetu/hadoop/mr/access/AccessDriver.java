package cn.fanyetu.hadoop.mr.access;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author zhanghaonan
 * @date 2019/9/17
 */
public class AccessDriver {

    static {
        try {
            System.load("G:\\Hadoop\\hadoop-2.6.0-cdh5.16.2\\bin\\hadoop.dll");
        }catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);

        }
    }

    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);

        job.setJarByClass(AccessDriver.class);

        job.setMapperClass(AccessMapper.class);
        job.setReducerClass(AccessReducer.class);

        // 设置partitioner
        job.setPartitionerClass(AccessPartitioner.class);
        // 设置reduce的个数
        job.setNumReduceTasks(3);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Access.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Access.class);

        FileInputFormat.setInputPaths(job, new Path("hadoop/input/access"));
        FileOutputFormat.setOutputPath(job, new Path("hadoop/output"));

        // 提交job
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : -1);

    }
}
