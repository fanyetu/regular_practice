package cn.fanyetu.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

/**
 * hdfs 测试操作测试类
 *
 * @author zhanghaonan
 * @date 2018/5/31
 */
public class HDFSTest {

    public static final String HDFS_PATH = "hdfs://192.168.200.128:8020";

    FileSystem fileSystem = null;
    Configuration configuration = null;



    /**
     * 创建文件夹
     * @throws Exception
     */
    @Test
    public void mkdir() throws Exception{
        boolean mkdirs = fileSystem.mkdirs(new Path("/hdfsapi/test"));
    }

    /**
     * 创建hdfs filesystem
     * @throws Exception
     */
    @Before
    public void before() throws Exception{
        System.out.println("turn on");

        configuration = new Configuration();
        // 第三个参数是设置用户，这里的用户我们设置了ssh免登陆
        fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, "haonan");
    }

    @After
    public void after(){
        configuration = null;
        fileSystem = null;

        System.out.println("turn off");
    }

}