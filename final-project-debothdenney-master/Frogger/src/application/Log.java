package application;

public class Log extends Waterbound {

	public Log(double x, double y, double w, double h, boolean direction, int xPos, int yPos, double speed) {
		super(x, y, w, h, direction, xPos, yPos, speed);
		drownable = false;
	}

}
