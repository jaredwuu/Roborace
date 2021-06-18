
import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.sound.Sound;
import COSC3P40.sound.SoundManager;
import COSC3P40.midi.*;
import COSC3P40.xml.*;
import javax.sound.midi.Sequence;
import javax.sound.sampled.AudioFormat;

public class RoboRace {
    
    public static void main(String[] args) {
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        ImageManager.getInstance().setImagePath("./Images/");
        XMLReader.setXMLPath("./");
        XMLReader.setXSDPath("./XSD/");
        int nHuman = 0;
        
        //testing
//        Sound s_bump;
//        Sound s_vic;
//        Sound s_des;
//        SoundManager sound_manager = new SoundManager(new AudioFormat(8000, 8, 1, false, false));
//        sound_manager.setSoundPath("./Sounds&Midi");
//        s_bump = sound_manager.getSound("/bump.wav");
//        s_vic = sound_manager.getSound("/explosion.wav");
//        s_des = sound_manager.getSound("/fanfare.wav");
//        sound_manager.play(s_des);
//        //end of testing
        MidiManager midi_manager = MidiManager.getInstance();
        
        midi_manager.setMidiPath("./Sounds&Midi");
        Sequence sequ = midi_manager.getSequence("/animaniacs.mid");
        midi_manager.play(midi_manager.getSequence("/animaniacs.mid"));
        midi_manager.play(sequ);
       
        while (nHuman == 0 || nHuman > 4) {
            try {
                nHuman = Integer.parseInt(GameDialogs.showInputDialog("Number of human players", "Please, input the number of human players (1-4):"));
            } catch (Exception e) {
            };
        };
        String[] names = new String[nHuman];
        Port[] ports = new Port[nHuman];
        for (int i = 0; i < nHuman; i++) {
            names[i] = GameDialogs.showInputDialog("Player #" + (i + 1), "Name of Player #" + (i + 1) + ":");
            Channel c = new Channel();
            ports[i] = c.asPort1();
            new Player(names[i], c.asPort2());
        };
        (new GameMaster(nHuman, names, ports)).run();
        
    }
}
