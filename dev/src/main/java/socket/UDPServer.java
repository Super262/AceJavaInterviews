package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(65001);
        byte[] buffer = new byte[1024];  // 存储从客户端接收到的内容
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length);

        socket.receive(packet);  // 接受客户端发送的内容，并将内容封装进DatagramPacket对象中
        byte[] data = packet.getData();  // 接收到数据
        String content = new String(data,0,packet.getLength());  // 二进制转字符串
        System.out.println(content);

        byte[] sentResponse = String.valueOf(content.length()).getBytes();  // 转换返回的数据为二进制
        // 服务端给客户端发送数据报：从来信获取源地址和端口号
        DatagramPacket packetToClient = new DatagramPacket(sentResponse,sentResponse.length,packet.getAddress(),packet.getPort());
        socket.send(packetToClient);
    }
}
