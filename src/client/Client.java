package client;
import java.io.*;
import java.net.*;

/**
 * Created by hold12 on 31-01-2017.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        String msg;
        String msgModified;

        BufferedReader inUser = new BufferedReader(new InputStreamReader(System.in));

        Socket sck = new Socket(args[0], Integer.parseInt(args[1]));

        DataOutputStream outServer = new DataOutputStream(sck.getOutputStream());
        BufferedReader inServer = new BufferedReader(new InputStreamReader(sck.getInputStream()));

        msg = inUser.readLine();
        outServer.writeBytes(msg + "\n");

        msgModified = inServer.readLine();
        System.out.println("FROM SERVER: " + msgModified);

        sck.close();
    }
}
