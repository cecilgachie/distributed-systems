import java.io.*;
import java.net.*;

public class TCPServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 5154;

    public static void main(String[] args) {
        System.out.println("Opening the server port...\n");

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            runServer();
        }
    }

    public static void runServer() {
        Socket link = null;
        try {
            link = serverSocket.accept();

            BufferedReader input = new BufferedReader(new InputStreamReader(link.getInputStream()));
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            String in = input.readLine();
            int numMess = 0;

            while (in != null && !in.equalsIgnoreCase("exit")) {
                numMess++;
                System.out.println(numMess + " received from client \n");
                output.println("\n Message no: " + numMess + " has been received");
                in = input.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (link != null) {
                    System.out.println("\n Closing the port");
                    link.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
