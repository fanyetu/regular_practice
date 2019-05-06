package cn.fanyetu.socket.udp;

import java.io.IOException;
import java.net.*;

/**
 * UDP搜索方
 *
 * @author zhanghaonan
 * @date 2019/5/6
 */
public class UDPSearcher {

    public static void main(String[] args) throws IOException {
        System.out.println("UDPSearcher started.");

        // 创建DatagramSocket，搜索方，无需监听端口
        DatagramSocket datagramSocket = new DatagramSocket();

        // 发送数据
        String sendData = "Hello world!";

        // 创建发送packet，发送到本机的20000端口
        DatagramPacket sendPacket = new DatagramPacket(sendData.getBytes(), sendData.getBytes().length,
                InetAddress.getLocalHost(),
                20000);

        // 发送数据
        datagramSocket.send(sendPacket);

        // 创建接收数据的datagramPacket
        byte[] buf = new byte[512];
        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);

        // 接收数据
        datagramSocket.receive(receivePacket);

        // 读取接收数据的信息
        InetAddress address = receivePacket.getAddress();
        int port = receivePacket.getPort();
        int dataLength = receivePacket.getLength();
        byte[] data = receivePacket.getData();

        String result = new String(data);
        System.out.println("UDPSearcher received. ip: " + address.getHostAddress() + "; port: " + port + "; " +
                "dataLength: " + dataLength + "; " +
                "data: " + result);

        // 关闭
        System.out.println("UDPSearcher closed.");
        datagramSocket.close();
    }
}
