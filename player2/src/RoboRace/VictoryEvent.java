package RoboRace;

import java.awt.Point;

public class VictoryEvent extends GameEvent {
	
	private String name;
	
	public VictoryEvent(EventCounter counter, int x, int y, String name) {
		super(counter,x,y);
		this.name = name;
	}	
	
	public VictoryEvent(EventCounter counter, Point p, String name) {
		this(counter,p.x,p.y,name);
	}
	
	public void execute(Board board) {
            Player.sound_manager.play(Player.s_vic);
	}
	
	public String getName() {
		return name;
	}
	
	public String toXMLString() {
		return "<victory " + attrXMLString() + " name=\"" + name + "\"/>";
	}

}