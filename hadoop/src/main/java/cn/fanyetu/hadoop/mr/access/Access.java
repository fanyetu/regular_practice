package cn.fanyetu.hadoop.mr.access;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 自定义MapReduce的复杂数据类型
 * 1)按照hadoop的规范，实现Writable接口
 * 2)按照hadoop的规范，实现write和readFields方法
 * 3)定义一个默认的构造方法
 *
 * @author zhanghaonan
 * @date 2019/9/17
 */
public class Access implements Writable {

    private String phone;

    private long up;

    private long down;

    private long sum;

    public Access() {
    }

    public Access(String phone, long up, long down) {
        this.phone = phone;
        this.up = up;
        this.down = down;
        this.sum = up + down;
    }

    public void write(DataOutput dataOutput) throws IOException {
        // write和readFields方法对字段的读取顺序必须是一致的

        dataOutput.writeUTF(phone);
        dataOutput.writeLong(up);
        dataOutput.writeLong(down);
        dataOutput.writeLong(sum);
    }

    public void readFields(DataInput dataInput) throws IOException {
        // 和write方法的写顺序一致
        this.phone = dataInput.readUTF();
        this.up = dataInput.readLong();
        this.down = dataInput.readLong();
        this.sum = dataInput.readLong();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getUp() {
        return up;
    }

    public void setUp(long up) {
        this.up = up;
    }

    public long getDown() {
        return down;
    }

    public void setDown(long down) {
        this.down = down;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return phone + "," + up + "," + down + "," + sum;
    }
}
