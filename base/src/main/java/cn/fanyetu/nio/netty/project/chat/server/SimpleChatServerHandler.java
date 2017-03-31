package cn.fanyetu.nio.netty.project.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * SimpleChannelInboundHandler�������ʵ����ChannelInboundHa
 * ndler�ӿڣ�ChannelInboundHandler �ṩ������¼�����Ľӿڷ�����Ȼ������Ը�����Щ���������ڽ��� ֻ��Ҫ�̳�
 * SimpleChannelInboundHandler ����������Լ�ȥʵ�ֽӿڷ�����
 * 
 * @author zhanghaonan
 * 
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {

	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	/**
	 * ������ channelRead0() �¼���������ÿ���ӷ���˶����ͻ���д����Ϣʱ������Ϣת���������ͻ��˵� C
	 * hannel�����������ʹ�õ��� Netty 5.x �汾ʱ����Ҫ�� channelRead0() ������ΪmessageReceived()
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
	 * ������ handlerAdded() �¼���������ÿ���ӷ�����յ��µĿͻ�������ʱ���ͻ��˵� Channel ����Chan
	 * nelGroup�б��У���֪ͨ�б��е������ͻ��� Channel
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();// ��ȡ�½����Ŀͻ���

		for (Channel channel : channels) {// ֪ͨ�����ͻ���
			channel.writeAndFlush("[SERVER]-" + incoming.remoteAddress() + "����\n");
		}

		channels.add(ctx.channel());
	}

	/**
	 * ������ handlerRemoved() �¼���������ÿ���ӷ�����յ��ͻ��˶Ͽ�ʱ���ͻ��˵� Channel �Ƴ� Chan nelGroup
	 * �б��У���֪ͨ�б��е������ͻ��� Channel
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel leave = ctx.channel();
		for (Channel channel : channels) {
			channel.writeAndFlush("[SERVER]-" + leave.remoteAddress() + "�뿪\n");
		}
		channels.add(ctx.channel());
	}

	/**
	 * ����˼������ͻ��˻
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"����");
	}

	/**
	 * ����˼������ͻ��˲��
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"����");
	}

	/**
	 * �����쳣
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClent" + incoming.remoteAddress() + "�쳣");

		cause.printStackTrace();
		ctx.close();
	}

}
