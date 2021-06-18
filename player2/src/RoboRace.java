/*
this is a player
 */
import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.midi.MidiManager;
import COSC3P40.xml.*;
import java.net.Socket;
import javax.sound.midi.Sequence;

public class RoboRace {

    static String name;
    static Socket client;
    static NetworkPort port;

    public static void main(String[] args) {
        MidiManager midi_manager = MidiManager.getInstance();
        midi_manager.setMidiPath("./Sounds&Midi");
        Sequence sequ = midi_manager.getSequence("/animaniacs.mid");
        midi_manager.play(midi_manager.getSequence("/animaniacs.mid"));
        midi_manager.play(sequ);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        ImageManager.getInstance().setImagePath("./Images/");
        XMLReader.setXMLPath("./");
        XMLReader.setXSDPath("./XSD/");
//        while (nHuman == 0 || nHuman > 4) {
//            try {
//                nHuman = Integer.parseInt(GameDialogs.showInputDialog("Number of human players", "Please, input the number of human players (1-4):"));
//            } catch (Exception e) {
//            };
//        };
        //String[] names = new String[nHuman];
        // NetworkPort[] ports = new NetworkPort[nHuman];
        name = null;
        name = GameDialogs.showInputDialog("Name Dialog", "Name of Player:");
        try {
             client = new Socket("localhost", 10997);
            NetworkPort port = new NetworkPort(client);
            port.send(name);
            System.out.println("Game player part start");
            new Player(name, port);
        } catch (Exception e) {
        }

    }
}
