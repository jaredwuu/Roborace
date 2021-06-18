package RoboRace;

import COSC3P40.sound.Sound;
import COSC3P40.sound.SoundManager;
import java.awt.Point;


public class BumpEvent extends GameEvent {
	
	private Direction direction;
        
        
       

	public BumpEvent(EventCounter counter, int x, int y, Direction direction) {
		super(counter,x,y);
		this.direction = direction;
	}
	
	public BumpEvent(EventCounter counter, Point p, Direction direction) {
		this(counter,p.x,p.y,direction);
	}
	
	public void execute(Board board) {
//            sound = sound_manager.getSound("/bump.wav");
//            sound_manager.setSoundPath("../Sounds&Midi");
            //sound_manager.play(sound);
           Player.sound_manager.play(Player.s_bump);
            
	}
	
	public String toXMLString() {
		return "<bump " + attrXMLString() + " direction=\"" + direction + "\"/>";
	}
	
}