package application;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Waterbound extends Sprite {

	private double speed;
	private boolean direction; // true = right, false = left
	protected boolean drownable; // if turtle is diving, it will drop the frog in the water and drown it
									// logs always return drownable = false

	final int CANVAS_X = 600;
	final int CANVAS_Y = 700;


	public Waterbound(double x, double y, double w, double h, boolean direction, int xPos, int yPos, double speed) {
		super(x, y, w, h);
		this.speed = speed;
		this.direction = direction;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/*
	 * Moves logs and turtles
	 */
	public void waterFloat(double time) {//moves turtles/logs

		if (direction) {// object is traveling right

			if (xPos >= CANVAS_X + 20) {// object returns to the left side once it
				xPos = -60; // reaches the edge of the screen
			}

			else {// round up, otherwise the int will keep adding 0
					// because it rounds down by default
				xPos += Math.ceil(time * speed);
			}
		}

		else {// object is traveling left
			if (xPos <= -30) {// inverse of right-traveling object
				xPos = CANVAS_X + 60;
			}
			xPos -= Math.ceil(time * speed);
		}
		draw();
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean getDirection() {
		return direction;
	}

	public void setPosition(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public void draw() {
		imageView.setX(xPos);
		imageView.setY(yPos);
	}

	public double getSpeed() {
		return speed;
	}

	public boolean getDrownable() {
		return drownable;
	}

}
