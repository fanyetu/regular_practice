package cn.fanyetu.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.jboss.netty.channel.ExceptionEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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

    /**
     * 从本地拷贝大文件，并带进度条
     * @throws Exception
     */
    @Test
    public void copyFromLocalFileWithProgress() throws Exception {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File("G:/test.pdf")));

        FSDataOutputStream out = fileSystem.create(new Path("/hdfsapi/test/test.pdf"),
                new Progressable() {
                    public void progress() {
                        System.out.print(".");
                    }
                });

        IOUtils.copyBytes(in, out, 4096);
    }

    /**
     * 从本地拷贝文件
     * @throws Exception
     */
    @Test
    public void copyFromLocalFile() throws Exception{
        Path src = new Path("G:/hellohdfs.txt");
        Path dst = new Path("/hdfsapi/test");
        fileSystem.copyFromLocalFile(src, dst);
    }

    /**
     * 修改文件名
     * @throws Exception
     */
    @Test
    public void rename() throws Exception{
        boolean rename = fileSystem.rename(new Path("/hdfsapi/test/zhn.txt"), new Path("/hdfsapi/test/z1n.txt"));
        System.out.println(rename);
    }

    /**
     * 创建文件
     * @throws Exception
     */
    @Test
    public void create() throws Exception{
        FSDataOutputStream out = fileSystem.create(new Path("/hdfsapi/test/zhn.txt"));
        out.writeUTF("test hdfs create file api");
        out.flush();
        out.close();
    }

    /**
     * 输出文件内容
     * @throws Exception
     */
    @Test
    public void text() throws Exception {
        Path path = new Path("/hdfsapi/test/c.txt");
        FSDataInputStream open = fileSystem.open(path);
        IOUtils.copyBytes(open, System.out, 1024);
    }

    /**
     * 创建文件夹
     * @throws Exception
     */
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

        // 使用hadoop进行访问
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
