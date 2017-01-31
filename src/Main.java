/**
 * Created by AndersWOlsen on 31-01-2017.
 */
public class Main {
    public static void main(String[] args) {
        switch(args.length) {
            case 1:
                server.Server server = new server.Server(Integer.parseInt(args[0]));
            case 2:
                client.Client client = new client.Client(args[0], Integer.parseInt(args[1]));
        }
    }
}
