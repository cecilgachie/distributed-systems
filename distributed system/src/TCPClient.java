import java.io.*;
import java.net.*;

public class TCPClient {
    private static final String SERVER_IP = "127.0.0.1"; // Change to server IP if needed
    private static final int SERVER_PORT = 5154;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to the server. Type messages to send (type 'exit' to quit):");

            String userMessage;
            while (true) {
                System.out.print("You: ");
                userMessage = userInput.readLine();

                if (userMessage.equalsIgnoreCase("exit")) {
                    break;
                }

                output.println(userMessage);
                String serverResponse = input.readLine();
                System.out.println("Server: " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client disconnected.");
    }
}
