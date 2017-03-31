package cn.fanyetu.nio.netty.demo.discard;

import javax.sound.midi.Soundbank;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * ֻ��һ���򵥵Ľ��ܿͻ�����Ϣ�������κεķ���
 * 
 * �������˵�channel
 * @author zhanghaonan
 *
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// ����(discard)����
		//((ByteBuf) msg).release();//����ByteBuf��release�����ͷţ������ֶ�����
		
		//��ӡ����ͻ��˴��������
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
		//�������쳣ʱ�͹ر�����
		cause.printStackTrace();
		ctx.close();
	}

}
