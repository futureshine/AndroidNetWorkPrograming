import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lei Guo on 2016/10/29.
 */
public class SimpleServer {
    public static void main(String[] args) throws IOException{
        //创建一个ServeSocket，用于监听客户端Socket的连接请求
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("127.0.0.1",30001));
        //用死循环不断接受来自客户端的请求
        while (true){
            //每当接收到客户端Socket的请求，服务器端也对应产生一个Socket
            Socket s = ss.accept();
            OutputStream os = s.getOutputStream();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String str = sdf.format(date);
            os.write(str.getBytes("utf-8"));
            //关闭输出流，关闭Socket
            os.close();
            s.close();
        }
    }
}
