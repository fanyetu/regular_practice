package cn.fanyetu.socket.udp;

import java.io.IOException;
import java.net.*;

/**
 * UDP提供者
 *
 * @author zhanghaonan
 * @date 2019/5/6
 */
public class UDPProvider {

    public static void main(String[] args) throws IOException {
        System.out.println("UDPProvider started.");

        // 创建DatagramSocket，监听端口
        DatagramSocket datagramSocket = new DatagramSocket(20000);

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
        System.out.println("UDPProvider received. ip: " + address.getHostAddress() + "; port: " + port + "; " +
                "dataLength: " + dataLength + "; " +
                "data: " + result);

        // 发送数据
        String sendData = "UPDProvider received data length: " + dataLength;

        // 创建发送packet
        DatagramPacket sendPacket = new DatagramPacket(sendData.getBytes(), sendData.getBytes().length, address,
                port);

        // 发送数据
        datagramSocket.send(sendPacket);

        // 关闭
        System.out.println("UDPProvider closed.");
        datagramSocket.close();
    }

}
