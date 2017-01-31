package server;
import java.io.*;
import java.net.*;

/**
 * Created by hold 12 on 31-01-2017.
 */
public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream streamIn = null;
    private DataOutputStream streamOut = null;

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
                    String msg = streamIn.readUTF();
                    System.out.println("Received: " + msg);
                    sendMsg(msg);
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

    private void sendMsg(String msg) {
        try {
            streamOut.writeUTF(msg);
            streamOut.flush();
        } catch (IOException e) {
            System.out.println("An error occured while sending message.\n" + e.getMessage());
        }
    }

    private void openStream() throws IOException {
        if (socket == null)
            return;
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    private void closeStream() throws IOException {
        if (socket != null)   socket.close();
        if (streamIn != null) streamIn.close();
        if (streamOut != null) streamOut.close();
    }
}
