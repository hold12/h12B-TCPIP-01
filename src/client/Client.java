package client;
import java.io.*;
import java.net.*;

/**
 * Created by hold 12 on 31-01-2017.
 */
public class Client {
    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;

    public Client(String host, int port) {
        System.out.println("Connecting to server. Please wait...");
        try {
            socket = new Socket(host, port);
            System.out.println("Connected to a server: " + socket.getLocalAddress() + " on port " + socket.getLocalPort());
            startConnection();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + e.getMessage());
        } catch (IOException e) {
            e.getStackTrace();
        }

        String msg = "";
        while(!quit(msg)) {
            try {
                msg = console.readUTF();
                streamOut.writeUTF(msg);
                streamOut.flush();
                System.out.println("FROM SERVER: " + console.readUTF());
            } catch (IOException e) {
                e.getStackTrace();
            }
        }

        stopConnection();
    }

    private boolean quit(String msg) {
        return msg.equals("exit") || msg.equals("quit");
    }

    private void startConnection() throws IOException {
        if (socket == null)
            return;
        console = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
    }

    private void stopConnection() {
        try {
            if (console != null) console.close();
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
//    public static void main(String[] args) throws Exception {
//        String msg;
//        String msgModified;
//
//        BufferedReader inUser = new BufferedReader(new InputStreamReader(System.in));
//
//        Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
//
//        DataOutputStream outServer = new DataOutputStream(socket.getOutputStream());
//        BufferedReader inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//        msg = inUser.readLine();
//        outServer.writeBytes(msg + "\n");
//
//        msgModified = inServer.readLine();
//        System.out.println("FROM SERVER: " + msgModified);
//
//        socket.close();
//    }
}
