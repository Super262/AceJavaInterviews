package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",65000);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        os.write("Hello world, Fengwei! ".getBytes(StandardCharsets.UTF_8));
        byte[] buffer = new byte[1024];
        int dataLength = is.read(buffer);
        String content = new String(buffer,0,dataLength);
        System.out.println(content);

        is.close();
        os.close();
        socket.close();
    }
}
