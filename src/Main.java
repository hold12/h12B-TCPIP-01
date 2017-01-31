import java.io.IOException;

/**
 * Created by AndersWOlsen on 31-01-2017.
 */
public class Main {
    public static void main(String[] args) {
        switch(args.length) {
            case 1:
                try {server.Server.main(args); } catch (IOException e) { e.getStackTrace(); }
//                server.Server server = new server.Server(Integer.parseInt(args[0]));
            case 2:
                try { client.Client.main(args); } catch (IOException e ) { e.getStackTrace(); }
//                client.Client client = new client.Client(args[0], Integer.parseInt(args[1]));
        }
    }
}
