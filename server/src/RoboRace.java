/*
this is a server
 */
import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.xml.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class RoboRace {

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        ImageManager.getInstance().setImagePath("./Images/");
        XMLReader.setXMLPath("./");
        XMLReader.setXSDPath("./XSD/");
        int nHuman = 0;
        NetworkPort[] ports;
        Socket[] clients;
        int added_clients = 0;
        
//get number of players
        while (nHuman == 0 || nHuman > 4) {
            try {
                nHuman = Integer.parseInt(GameDialogs.showInputDialog("Number of human players", "Please, input the number of human players (1-4):"));
            } catch (Exception e) {
            };

            //initualize name string and and ports array;
            String[] names = new String[nHuman];
            ports = new NetworkPort[nHuman];
            clients = new Socket[nHuman];

            try {
                ServerSocket server = new ServerSocket(10997);
                for (int i = 0; i < nHuman; i++) {//for each player
                    // names[i] = GameDialogs.showInputDialog("Player #" + (i + 1), "Name of Player #" + (i + 1) + ":");
                    // Channel c = new Channel();
                    // ports[i] = c.asPort1();
                    // new Player(names[i], c.asPort2());
//
                    Socket client = server.accept();
                    System.out.println("here");
                   ports[i]=new NetworkPort(client);
                   //receive the name for each player;
                   names[i]=ports[i].recieve();
//                    port.send(name);
//                    new Player(name, port);
//                    System.out.println("Game player part start");
                }
            } catch (IOException e) {
            };
        (new GameMaster(nHuman,names,ports)).run();
        }
        // (new GameMaster(nHuman, names, ports)).run();
    }
}
