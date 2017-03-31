package cn.fanyetu.nio.netty.demo.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ���������
 * @author zhanghaonan
 *
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter{
	
	/**
	 * ����ͻ������������
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
		
//		ctx.write(msg);
//		ctx.flush();
		ctx.writeAndFlush(msg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
