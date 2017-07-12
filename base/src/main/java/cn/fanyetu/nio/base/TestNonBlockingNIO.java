package cn.fanyetu.nio.base;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by zhanghaonan on 2017/7/12.
 */
public class TestNonBlockingNIO {

	@Test
	public void client() throws IOException {
		//获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

		//设置channel为非阻塞模式
		sChannel.configureBlocking(false);

		//	分配指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);

		buf.put(new Date().toString().getBytes());
		buf.flip();//将缓冲区切换为读模式
		sChannel.write(buf);//将缓冲区写入channel
		buf.clear();

//		Scanner scanner = new Scanner(System.in);
//
//		while (scanner.hasNext()) {
//			String str = scanner.next();
//
//			buf.put((new Date() + "\n" + str).getBytes());
//
//			buf.flip();//将缓冲区切换为读模式
//			sChannel.write(buf);//将缓冲区写入channel
//			buf.clear();
//		}


		sChannel.close();//关闭channel
	}

	@Test
	public void server() throws IOException {
		//获取通道
		ServerSocketChannel ssChannel = ServerSocketChannel.open();

		//将通道设置为非阻塞模式
		ssChannel.configureBlocking(false);

		//绑定端口
		ssChannel.bind(new InetSocketAddress(9898));

		//获取选择器
		Selector selector = Selector.open();

		//将通道注册到选择器上，并且监听接收事件
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);

		//轮询获取选择器上已经准备就绪的事件
		while (selector.select() > 0) {

			//获取当前选择器上所注册的已经就绪的事件
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();

			while (it.hasNext()) {
				//获取准备就绪的事件
				SelectionKey sk = it.next();

				//判断具体是什么事件准备就绪了
				if (sk.isAcceptable()) {
					//若接收就绪，就获取客户端连接
					SocketChannel sChannel = ssChannel.accept();

					//设置为非阻塞模式
					sChannel.configureBlocking(false);

					//将该通道注册到选择器上
					sChannel.register(selector, SelectionKey.OP_READ);

					//如果是读时间就绪
				} else if (sk.isReadable()) {
					//获取通道
					SocketChannel sChannel = (SocketChannel) sk.channel();

					//创建缓冲区
					ByteBuffer buf = ByteBuffer.allocate(1024);

					//写出数据
					int len;
					while ((len = sChannel.read(buf)) != -1) {
						buf.flip();
						System.out.println(new String(buf.array(), 0, len));
						buf.clear();
					}

					sChannel.close();//不关闭会死循环，占用资源
				}

				//取消选择键
				it.remove();
			}
		}
	}
}
