package cn.fanyetu.nio.netty.project.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务端
 * 
 * @author zhanghaonan
 * 
 */
public class SimpleChatServer {

	private int port;

	public SimpleChatServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		EventLoopGroup bossGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap sb = new ServerBootstrap();
			sb.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new SimpleChatServerInitializer())
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);

			System.out.println("SimpelChatServer启动了");

			// 绑定端口，开始接收进来的连接
			ChannelFuture future = sb.bind(port).sync();

			// 等待服务器 socket 关闭 。
			// 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
			future.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();

			System.out.println("SimpleChatServer 关闭了");
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new SimpleChatServer(port).run();
	}

}
