/**
 * Created by AndersWOlsen on 31-01-2017.
 */
public class Main {
    public static void main(String[] args) {
        switch(args.length) {
            case 1:
                try {
                    server.Server server = new server.Server(Integer.parseInt(args[0]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case 2:
                try {
                    client.Client.main(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
