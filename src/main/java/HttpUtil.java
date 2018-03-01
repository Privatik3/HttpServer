import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class HttpUtil {

    public static BlockingQueue<Socket> taskQuery = new ArrayBlockingQueue<>(10);

    public synchronized static void writeHttpResponse(OutputStream out) throws IOException {
        out.write((new Date().toString() + "\n\r").getBytes());
    }

    public synchronized static String readHttpRequest(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String result = "";
        while(true) {
            String s = br.readLine();
            if(s == null || s.trim().length() == 0) {
                break;
            }
            result += s;
        }
        return result;
    }
}
