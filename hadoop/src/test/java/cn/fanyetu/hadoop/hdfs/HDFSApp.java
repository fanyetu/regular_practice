package cn.fanyetu.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

/**
 * 使用Java api操作文件系统
 * <p>
 * 关键点：
 * 1.创建Configuration
 * 2.创建FileSystem
 * 3.执行API的相关操作
 *
 * @author zhanghaonan
 * @date 2019/9/4
 */
public class HDFSApp {

    public static final String HDFS_URI = "hdfs://192.168.1.136:8020";

    private FileSystem fileSystem = null;
    private Configuration configuration = null;

    @Test
    public void mkdir() throws Exception{
        Path path = new Path("/hdfsapi/test");
        boolean mkdirs = fileSystem.mkdirs(path);
        System.out.println(mkdirs);
    }

    @Before
    public void setUp() throws Exception{
        configuration = new Configuration();
        // 设置hdfs的副本系数为1
        configuration.set("dfs.replication", "1");

        fileSystem = FileSystem.newInstance(URI.create(HDFS_URI), configuration, "hadoop");

        System.out.println("===========set up============");

    }

    @After
    public void setDown() throws IOException {
        if (fileSystem != null){
            fileSystem.close();
        }
        System.out.println("===========set down============");
    }
}
