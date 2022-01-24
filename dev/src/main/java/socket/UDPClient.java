package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] data = "Hello world! ".getBytes();

        InetAddress address = Inet4Address.getByName("127.0.0.1");  // 封装IP地址
        DatagramPacket packet = new DatagramPacket(data,data.length,address,65001);  // 需要IP地址和端口号
        socket.send(packet);

        byte[] buffer = new byte[1024];
        DatagramPacket receivedPacket = new DatagramPacket(buffer,buffer.length);  // 存储服务器返回的数据
        socket.receive(receivedPacket);
        String content = new String(receivedPacket.getData(),0,receivedPacket.getLength());
        System.out.println(content);
    }
}
