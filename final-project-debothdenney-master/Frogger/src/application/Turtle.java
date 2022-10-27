package application;

import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Turtle extends Waterbound {

	private boolean swimming = false;
	private int divingStages = 1;
	private boolean diving = false;

	public Turtle(double x, double y, double w, double h, boolean direction, int xPos, int yPos, double speed) {
		super(x, y, w, h, direction, xPos, yPos, speed);
		if (direction) {
			this.imageView.setRotate(180);
		}
	}

	public void swimOrDive(double elapsedTime) {
		Random generator = new Random();
		int randNum = generator.nextInt(10);

		if (!diving) {//switch between two swimming sprites if not diving

			if (randNum != 9) {

				if (swimming) {
					this.imageView.setViewport(new Rectangle2D(19, 150, 15, 15));
				}

				else
					this.imageView.setViewport(new Rectangle2D(1, 150, 15, 15));

				swimming = !swimming;

			}

			else {//randomly decides when to have a turtle dive
				diving = true;
			}

		}

		else {//if diving, cycle though diving animation sprites
			

			if (divingStages == 1) {
				this.imageView.setViewport(new Rectangle2D(55, 151, 15, 15));
				divingStages++;
			}

			else if (divingStages == 2) {
				this.imageView.setViewport(new Rectangle2D(73, 151, 15, 15));
				divingStages++;
			}

			else if (divingStages == 3) {
				//turtle is completely submerged at this stage
				this.imageView.setViewport(new Rectangle2D(150, 151, 15, 15));
				divingStages++;
				drownable = true;
			}

			else if (divingStages == 4) { //turtle coming back up
				this.imageView.setViewport(new Rectangle2D(73, 151, 15, 15));
				divingStages++;
				drownable = false;
			}

			else {
				this.imageView.setViewport(new Rectangle2D(55, 150, 15, 15));
				divingStages = 1;
				diving = false;
			}

		}

	}

	public ImageView getImgView() {
		return this.imageView;
	}

	public void draw() {
		imageView.setX(xPos);
		imageView.setY(yPos);
	}
	
	public boolean getDrownable() {
		return drownable;
	}
	


}
