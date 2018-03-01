import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpHandler extends Thread {

    private static int threadNum = 1;

    public HttpHandler() {
        super("Thread#" + threadNum++);
        System.out.println(this.getName() + " was created");
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " wait a job");
        while (true) {
            try (Socket socket = HttpUtil.taskQuery.take();
                 InputStream in = socket.getInputStream();
                 OutputStream out = socket.getOutputStream()) {

                synchronized (this) {
                    System.out.println(this.getName() + " have a job !!!");

//                System.out.println(this.getName() + " get request: ");
//                    String request = HttpUtil.readHttpRequest(in);
//                System.out.println(request);

                    HttpUtil.writeHttpResponse(out);
//                System.out.println(this.getName() + " send response");
                    System.out.println("=================================");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
