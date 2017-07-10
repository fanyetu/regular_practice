package cn.fanyetu.nio.base;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by zhanghaonan on 2017/7/10.
 */
public class ChannelTest {

	//charset
	@Test
	public void test5() throws CharacterCodingException {
		Charset cs1 = Charset.forName("GBK");

		//获取编码器
		CharsetEncoder ce = cs1.newEncoder();
		//获取解码器
		CharsetDecoder cd = cs1.newDecoder();

		CharBuffer cBuf1 = CharBuffer.allocate(1024);
		cBuf1.put("测试字符集");
		cBuf1.flip();

		//编码
		ByteBuffer bBuf = ce.encode(cBuf1);

		for (int i=0;i<bBuf.limit();i++){
			System.out.println(bBuf.get());
		}

		bBuf.flip();
		//解码
		CharBuffer cBuf2 = cd.decode(bBuf);
		System.out.println(cBuf2.toString());

		//测试乱码
		Charset cs2 = Charset.forName("UTF-8");
		bBuf.flip();
		CharBuffer cBuf3 = cs2.decode(bBuf);
		System.out.println(cBuf3.toString());
	}

	//分散和聚集
	@Test
	public void test4() throws IOException {
		RandomAccessFile raf1 = new RandomAccessFile("f:/1.txt", "rw");

		FileChannel channel1 = raf1.getChannel();

		ByteBuffer buf1 = ByteBuffer.allocate(100);
		ByteBuffer buf2 = ByteBuffer.allocate(1024);

		ByteBuffer[] bufs = {buf1,buf2};

		channel1.read(bufs);//分散读取

		for (ByteBuffer buf : bufs){
			buf.flip();//读模式
		}

		System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));

		RandomAccessFile raf2 = new RandomAccessFile("f:/2.txt", "rw");

		FileChannel channel2 = raf2.getChannel();

		channel2.write(bufs);//聚集写入

		channel1.close();
		channel2.close();
	}

	//通道之间的数据传输（直接缓冲区）
	@Test
	public void test3() throws IOException {
		long start = System.currentTimeMillis();

		FileChannel inChannel = FileChannel.open(Paths.get("f:\\1.pdf"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("f:\\4.pdf"), StandardOpenOption.WRITE,
				StandardOpenOption.READ, StandardOpenOption.CREATE);

//		inChannel.transferTo(0,inChannel.size(),outChannel);//传输到
		outChannel.transferFrom(inChannel,0,inChannel.size());//从...传输

		inChannel.close();
		outChannel.close();

		long end = System.currentTimeMillis();
		System.out.println("耗费时间:"+(end-start));
	}

	//使用直接缓冲区复制文件(内存映射文件)
	@Test
	public void test2() throws IOException {//583 513 480 496
		long start = System.currentTimeMillis();

		//jdk1.7 nio2提供的open方法
		FileChannel inChannel = FileChannel.open(Paths.get("f:\\1.pdf"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("f:\\3.pdf"), StandardOpenOption.WRITE,
				StandardOpenOption.READ, StandardOpenOption.CREATE);

		//通过channel的map方法获取直接缓冲区，和ByteBuffer.allocateDirect()效果相同
		MappedByteBuffer inBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

		//因为是内存映射文件，直接操作缓冲区即可完成IO操作
		byte[] dst = new byte[inBuf.limit()];

		inBuf.get(dst);
		outBuf.put(dst);

		inChannel.close();
		outChannel.close();

		long end = System.currentTimeMillis();
		System.out.println("耗费时间:"+(end-start));
	}

	//使用非直接缓冲区复制文件
	@Test
	public void test1(){//9934 8377 6597 6877
		long start = System.currentTimeMillis();

		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			fis = new FileInputStream("f:\\1.pdf");
			fos = new FileOutputStream("f:\\2.pdf");

			//创建通道
			inChannel = fis.getChannel();
			outChannel = fos.getChannel();

			//创建缓冲区
			ByteBuffer buf = ByteBuffer.allocate(1024);

			//读取数据到缓冲区
			while (inChannel.read(buf) != -1){
				buf.flip();//设置缓冲区为读模式
				outChannel.write(buf);//将缓冲区写入到通道中
				buf.clear();//清空缓冲区
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos!= null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("耗费时间:"+(end-start));

	}
}
