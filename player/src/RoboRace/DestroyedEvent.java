package RoboRace;

import java.awt.Point;
import COSC3P40.sound.Sound;
import COSC3P40.sound.SoundManager;

public class DestroyedEvent extends GameEvent {



    public DestroyedEvent(EventCounter counter, int x, int y) {
        super(counter, x, y);
    }

    public DestroyedEvent(EventCounter counter, Point p) {
        super(counter, p.x, p.y);
        
    }

    public void execute(Board board) {
        Player.sound_manager.play(Player.s_des);
        getRobot(board).destroyed();
        
    }

    public String toXMLString() {
        return "<destroyed " + attrXMLString() + "/>";
    }

}
