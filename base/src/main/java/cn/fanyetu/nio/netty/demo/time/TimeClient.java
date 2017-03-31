package cn.fanyetu.nio.netty.demo.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 时间客户端
 * @author zhanghaonan
 *
 */
public class TimeClient {
	
	public static void main(String[] args) throws InterruptedException {
//		String host = args[0];
//		int port = Integer.parseInt(args[1]);
		String host = "localhost";
		int port = 8080;
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			Bootstrap boot = new Bootstrap();
			boot.group(workerGroup).channel(NioSocketChannel.class)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new TimeClientHandler());
					}
				});
			
			//启动客户端
			ChannelFuture future = boot.connect(host, port).sync();
			
			//等待关闭
			future.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

}
