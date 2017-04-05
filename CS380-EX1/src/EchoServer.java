import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream; 
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    String address = socket.getInetAddress().getHostAddress();
                    System.out.printf("Client connected: %s%n", address);
                    OutputStream os = socket.getOutputStream();
                    PrintStream out = new PrintStream(os, true, "UTF-8");
                    out.printf("Hi %s, thanks for connecting!%n", address);
                    
                    InputStream is = socket.getInputStream();
					InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			       	BufferedReader br = new BufferedReader(isr);
				    String message;
				    
					while((message = br.readLine())!=null){
						out.println(message);
				    }
					
				    System.out.printf("Client disconnected: %s%n", address);
					socket.close();
                }
                catch (Exception e){}
            }
        }
    }
}