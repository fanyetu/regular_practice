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
public class TestBlockingNIO2 {

	//客户端
	@Test
	public void client() throws IOException {
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

		FileChannel inChannel = FileChannel.open(Paths.get("f:/1.jpg"), StandardOpenOption.READ);

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		while (inChannel.read(buffer) != -1) {
			buffer.flip();
			sChannel.write(buffer);
			buffer.clear();
		}

		sChannel.shutdownOutput();

		int len;
		while ((len = sChannel.read(buffer)) != -1) {
			buffer.flip();
			System.out.println(new String(buffer.array(), 0, len));
			buffer.clear();
		}

		inChannel.close();
		sChannel.close();

	}

	//服务端
	@Test
	public void server() throws IOException {
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		ssChannel.bind(new InetSocketAddress(9898));

		FileChannel outChannel = FileChannel.open(Paths.get("f:/2.jpg"), StandardOpenOption.WRITE,
				StandardOpenOption.CREATE);

		SocketChannel sChannel = ssChannel.accept();

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		while (sChannel.read(buffer) != -1){
			buffer.flip();
			outChannel.write(buffer);
			buffer.clear();
		}

		buffer.put("服务端接受数据成功".getBytes());
		buffer.flip();
		sChannel.write(buffer);

		outChannel.close();
		sChannel.close();
		ssChannel.close();
	}
}
