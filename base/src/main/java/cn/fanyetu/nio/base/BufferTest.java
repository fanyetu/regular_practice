package cn.fanyetu.nio.base;


import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 缓冲区实例
 * <p>
 * Created by Administrator on 2017/7/5.
 */
public class BufferTest {

    public static void main(String[] args) {

        IntBuffer buffer = IntBuffer.allocate(10);

        int[] arr = new int[]{2, 3, 4, 5};

        buffer = IntBuffer.wrap(arr);

//      buffer = IntBuffer.wrap(arr,0,2);

        buffer.put(0,10);

        for (int i = 0; i < buffer.limit(); i++) {
            System.out.print(buffer.get() + " ");
        }

//        buffer.clear();
        buffer.flip();

        System.out.println("\n");

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");

        System.out.println("buffer:" + buffer);

        IntBuffer buffer2 = buffer.duplicate();
        System.out.println("buffer2:" + buffer2);

        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("===========allocate()");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        String s = "abcd";
        buf.put(s.getBytes());
        System.out.println("============put()");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        buf.flip();
        System.out.println("============flip()");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst,0,dst.length));
        System.out.println("============get()");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        buf.rewind();
        System.out.println("============rewind()");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        buf.clear();
        System.out.println("============clear()");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        System.out.println(buf.get());
    }
}
