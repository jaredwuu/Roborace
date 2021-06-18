package RoboRace;

import java.awt.Point;

public class DestroyedEvent extends GameEvent {
	
	public DestroyedEvent(EventCounter counter, int x, int y) {
		super(counter,x,y);
	}
	
	public DestroyedEvent(EventCounter counter, Point p) {
		super(counter,p.x,p.y);
	}
	
	public void execute(Board board) {
		getRobot(board).destroyed();
                Player.sound_manager.play(Player.s_des);
	}

	public String toXMLString() {
		return "<destroyed " + attrXMLString() + "/>";
	}
	
}