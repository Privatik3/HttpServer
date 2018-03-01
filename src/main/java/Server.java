import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Thread> threadsPool = new ArrayList<>(1000);

        for (int i = 0; i < 50; i++) {
            threadsPool.add(new HttpHandler());
        }

        threadsPool.forEach(Thread::start);
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            Socket socket = serverSocket.accept();
            HttpUtil.taskQuery.put(socket);
        }
    }
}
