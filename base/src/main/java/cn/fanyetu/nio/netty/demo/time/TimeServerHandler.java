package cn.fanyetu.nio.netty.demo.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 
 * @author zhanghaonan
 *
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter{
	
	/**
	 * �������������ӱ���������׼������ͨ��ʱ�����á�
	 */
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		final ByteBuf time = ctx.alloc().buffer(4); // (2)
		time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
		
		final ChannelFuture f = ctx.writeAndFlush(time);
		
//		f.addListener(new ChannelFutureListener() {
//			@Override
//			public void operationComplete(ChannelFuture future) throws Exception {
//				assert f==future;
//				ctx.close();
//			}
//		});
		//������Ĵ���ȼ�
		f.addListener(ChannelFutureListener.CLOSE);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace(); 
		ctx.close();
	}

}
