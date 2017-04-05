import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
            Scanner kb = new Scanner(System.in);
    	    OutputStream os = socket.getOutputStream();
    	    PrintStream ps = new PrintStream(os);
    	    System.out.print("Client> ");
    	    String echo;
    	    
    	    while(!(echo = kb.nextLine()).equals("exit")){
    	    	ps.println(echo);
    	    	System.out.println("Server> " + br.readLine());
    	    	System.out.print("Client> ");
    	    }
        }
    }
}














