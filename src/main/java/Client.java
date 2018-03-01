import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {

        for (int i = 0; i < 100000; i++) {
            try (Socket s = new Socket(InetAddress.getByName("localhost"), 8080)) {
                System.out.println("Send request to server");
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                DataInputStream in = new DataInputStream(s.getInputStream());

                out.write(new String("This is test request\n\r".getBytes()).getBytes());
                out.flush();

                String response = HttpUtil.readHttpRequest(in);
                System.out.println("Response: " + response);
            }
            Thread.sleep(1000);
        }
    }
}
