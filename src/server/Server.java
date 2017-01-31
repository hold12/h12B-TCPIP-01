package server; /**
 * Created by AndersWOlsen on 31-01-2017.
 */

import java.io.*;
import java.net.*;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream streamIn = null;

    public Server(int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait.");
            server = new ServerSocket(port);

            System.out.println("A server just started: " + server.getInetAddress() + " on port " + server.getLocalPort());
            System.out.println("Waiting for a client...");
            socket = server.accept();

            openStream();

            boolean msgReceived = false;
            while (!msgReceived) {
                try {
                    String msg = streamIn.readLine();
                    System.out.println("Received: " + msg);
                    msgReceived = msg.equals("exit") || msg.equals("quit");
                } catch (IOException e) {
                    msgReceived = true;
                }
            }
            closeStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openStream() throws IOException {
        if (socket == null)
            return;
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    private void closeStream() throws IOException {
        if (socket != null)   socket.close();
        if (streamIn != null) streamIn.close();
    }
}
