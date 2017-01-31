package server; /**
 * Created by AndersWOlsen on 31-01-2017.
 */

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        String msg;
        String msgModified;
        ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));

        while(true) {
            Socket connection = server.accept();

            BufferedReader inClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            DataOutputStream outClient = new DataOutputStream(connection.getOutputStream());

            msg = inClient.readLine();
            System.out.println("Received " + msg);

            msgModified = msg.toUpperCase() + "\n";
            outClient.writeBytes(msgModified);
        }
    }
}
