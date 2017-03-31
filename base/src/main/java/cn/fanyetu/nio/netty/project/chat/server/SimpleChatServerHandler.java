package cn.fanyetu.nio.netty.project.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * SimpleChannelInboundHandler，这个类实现了ChannelInboundHa
 * ndler接口，ChannelInboundHandler 提供了许多事件处理的接口方法，然后你可以覆盖这些方法。现在仅仅 只需要继承
 * SimpleChannelInboundHandler 类而不是你自己去实现接口方法。
 * 
 * @author zhanghaonan
 * 
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {

	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	/**
	 * 覆盖了 channelRead0() 事件处理方法。每当从服务端读到客户端写入信息时，将信息转发给其他客户端的 C
	 * hannel。其中如果你使用的是 Netty 5.x 版本时，需要把 channelRead0() 重命名为messageReceived()
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
		Channel incoming = ctx.channel();
		for (Channel channel : channels) {
			if (channel != incoming) {
				channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + s + "\n");
			} else {
				channel.writeAndFlush("[you]" + s + "\n");
			}
		}
	}

	/**
	 * 覆盖了 handlerAdded() 事件处理方法。每当从服务端收到新的客户端连接时，客户端的 Channel 存入Chan
	 * nelGroup列表中，并通知列表中的其他客户端 Channel
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();// 获取新进来的客户端

		for (Channel channel : channels) {// 通知其他客户端
			channel.writeAndFlush("[SERVER]-" + incoming.remoteAddress() + "加入\n");
		}

		channels.add(ctx.channel());
	}

	/**
	 * 覆盖了 handlerRemoved() 事件处理方法。每当从服务端收到客户端断开时，客户端的 Channel 移除 Chan nelGroup
	 * 列表中，并通知列表中的其他客户端 Channel
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel leave = ctx.channel();
		for (Channel channel : channels) {
			channel.writeAndFlush("[SERVER]-" + leave.remoteAddress() + "离开\n");
		}
		channels.add(ctx.channel());
	}

	/**
	 * 服务端监听到客户端活动
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"在线");
	}

	/**
	 * 服务端监听到客户端不活动
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"掉线");
	}

	/**
	 * 处理异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClent" + incoming.remoteAddress() + "异常");

		cause.printStackTrace();
		ctx.close();
	}

}
