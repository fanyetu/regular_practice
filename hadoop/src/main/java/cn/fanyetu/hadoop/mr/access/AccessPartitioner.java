package cn.fanyetu.hadoop.mr.access;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 自定义的Partitioner分区规则
 * 这里将不同开头的手机号输出到不同的文件出
 *
 * @author zhanghaonan
 * @date 2019/9/17
 */
public class AccessPartitioner extends Partitioner<Text, Access> {

    @Override
    public int getPartition(Text text, Access access, int numReduceTasks) {
        if (text.toString().startsWith("13")) {
            return 0;
        } else if (text.toString().startsWith("15")) {
            return 1;
        } else {
            return 2;
        }
    }
}
