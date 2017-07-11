package cn.fanyetu.nio.base;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by zhanghaonan on 2017/7/11.
 */
public class TestBlockingNIO {

	//客户端
	@Test
	public void client() throws IOException {
		//创建socketChannel
		SocketChannel socketChannel = SocketChannel.open(
				new InetSocketAddress("127.0.0.1", 9898));

		//创建fileChannel
		FileChannel inChannel = FileChannel.open(Paths.get("f:/1.jpg"), StandardOpenOption.READ);

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		//将文件中的数据读取到socketChannel中
		while (inChannel.read(buffer) != -1){
			buffer.flip();
			socketChannel.write(buffer);
			buffer.clear();
		}

		inChannel.close();
		socketChannel.close();

	}

	//服务端
	@Test
	public void server() throws IOException {
		//创建serverSocketChannel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(new InetSocketAddress(9898));//为serverSocketChannel绑定端口

		//创建fileChannel
		FileChannel outChannel = FileChannel.open(Paths.get("f:/2.jpg"), StandardOpenOption.WRITE,
				StandardOpenOption.CREATE);

		//监听客户端，获取socketChannel
		SocketChannel socketChannel = serverSocketChannel.accept();

		//创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		//从socketChannel中读取数据并写入到文件中
		while (socketChannel.read(buffer) != -1){
			buffer.flip();
			outChannel.write(buffer);
			buffer.clear();
		}

		outChannel.close();
		socketChannel.close();
		serverSocketChannel.close();
	}

}
