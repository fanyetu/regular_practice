package cn.fanyetu.nio.netty.demo.discard;

import javax.sound.midi.Soundbank;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 只是一个简单的接受客户端消息，不做任何的返回
 * 
 * 处理服务端的channel
 * @author zhanghaonan
 *
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 丢弃(discard)数据
		//((ByteBuf) msg).release();//调用ByteBuf的release方法释放，必须手动调用
		
		//打印输出客户端传入的内容
		ByteBuf in = (ByteBuf) msg;
		try {
			while (in.isReadable()) {
				System.out.print((char) in.readByte());
				System.out.flush();
			}
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//当出现异常时就关闭连接
		cause.printStackTrace();
		ctx.close();
	}

}
