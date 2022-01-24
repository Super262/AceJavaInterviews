package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class LengthCalculator extends Thread {
    private final Socket socket;

    public LengthCalculator(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream os = socket.getOutputStream();  // 获取socket的输出流
            InputStream is = socket.getInputStream();  // 获取socket的输入流

            byte[] buffer = new byte[1024];  // buffer主要用来存储输入内容
            int dataLength = is.read(buffer);  // 获取数据长度
            String content = new String(buffer,0,dataLength); // 转换byte[]为字符串
            System.out.println(content);
            os.write(String.valueOf(content.length()).getBytes(StandardCharsets.UTF_8));  // 返回客户端字符串长度

            is.close();  // 最后，释放资源
            os.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
