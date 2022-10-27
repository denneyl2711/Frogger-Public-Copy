package application;

public class Vehicle extends Sprite {

	private double speed;
	private boolean direction; // true = right, false = left

	final int CANVAS_X = 600;
	final int CANVAS_Y = 700;

	public Vehicle(double x, double y, double w, double h, double speed, boolean direction, int xPos, int yPos) {
		super(x, y, w, h);
		this.speed = speed;
		this.direction = direction;
		this.xPos = xPos;
		this.yPos = yPos;

	}

	public boolean getDirection() {
		return direction;
	}

	public void drive(double time) {// move the vehicles

		if (direction) {// vehicle is traveling right

			if (xPos >= CANVAS_X + 20) {// vehicle returns to the left side once it
				xPos = 0; // reaches the edge of the screen
			}

			else {// round up, otherwise the int will keep adding 0
					// because it rounds down by default
				xPos += Math.ceil(time * speed);
			}
		}

		else {// vehicle is traveling left
			if (xPos <= -30) {// inverse of right-traveling vehicle
				xPos = CANVAS_X;
			}
			xPos -= Math.ceil(time * speed);
		}
		draw();
	}

	public void setSpeed(double x) {
		speed = x;
	}
}
