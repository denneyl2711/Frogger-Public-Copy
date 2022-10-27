package application;

import java.io.File;

import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
//TODO: add noises

public class Frog extends Sprite {

	private int score;
	private int lives;
	private int xPos;
	private int yPos;
	private int moveNumX = 60;
	private int moveNumY = 60;
	private boolean hasFloated = false; // returns true if frog has floated on an object
										// used to stop the frog from floating multiple times in one frame
										// is reset to false by resetHasFloated() at every frame

	// Ribbit sound taken from orangefreesounds.com
	private File ribbitFile = new File("ribbit.mp3");
	private AudioClip ribbitAudioClip = new AudioClip(ribbitFile.toURI().toString());

	// Splash sound taken from fesliyanstudios.com
	private File splashFile = new File("splash.mp3");
	private AudioClip splashAudioClip = new AudioClip(splashFile.toURI().toString());

	// Death sound taken from bigsoundbank.com
	private File dieFile = new File("die.mp3");
	private AudioClip dieAudioClip = new AudioClip(dieFile.toURI().toString());

	public Frog(double x, double y, double w, double h) {
		super(x, y, w, h);
		xPos = 300;
		yPos = 665;
		score = 0;
		lives = 7;
	}

	public void spawn() {
		this.getImgView().setX(300);
		this.getImgView().setY(665);
		xPos = 300;
		yPos = 665;

		this.imageView.setScaleX(3);
		this.imageView.setScaleY(3);

	}

	public void die() {
		lives--;
		spawn();
		this.imageView.setRotate(0);
		this.imageView.setViewport(new Rectangle2D(2, 1, 13, 13));

		dieAudioClip.stop();
		dieAudioClip.play();

	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public int getScore() {
		return score;
	}

	public int getLives() {
		return lives;
	}

	public void draw() {
		imageView.setX(xPos);
		imageView.setY(yPos);
	}

	public void setPosition(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public void move(KeyEvent event) {
		switch (event.getCode()) {
		case RIGHT:
			if (this.getXPos() < 540) {// if statements stop frog from traveling off the canvas

				this.setPosition(xPos + moveNumX, yPos);
				this.imageView.setRotate(90);
			}
			break;
		case LEFT:
			if (this.getXPos() > 30) {
				this.setPosition(xPos - moveNumX, yPos);
				this.imageView.setRotate(-90);
			}
			break;
		case UP:
			if (this.getYPos() > 20) {
				if (getYPos() > 370) {// this changes the vertical movement amount when the frog is in the water
										// section to line up with the 5 rows of logs/turtles as opposed to the 4 rows
										// of vehicles
					moveNumY = 60;
				} else {
					moveNumY = 50;
				}
				score += 10;
				this.setPosition(xPos, yPos - moveNumY);
				this.imageView.setRotate(0);
			}
			break;
		case DOWN:
			if (this.getYPos() < 665) {
				if (getYPos() < 320) {// same as UP section but for going the opposite direction.
					moveNumY = 50;
				} else {
					moveNumY = 60;
				}
				score -= 10;
				this.setPosition(xPos, yPos + moveNumY);
				this.imageView.setRotate(180);
			}
			break;
		default:
			break;
		}
		ribbitAudioClip.stop();
		ribbitAudioClip.play();
		draw();
	}

	public boolean floatWithWaterbound(Waterbound w, double elapsedTime) {

		if (w.getDrownable()) {// if turtle is underwater, drown the frog
			return false;
		}

		else if (this.getXPos() < 630 && this.getXPos() > -5) {

			if (this.intersects(w)) {
				if (!hasFloated) {// if frog will not travel offscreen and hasn't already been moved by a
									// Waterbound object

					if (w.getDirection()) {
						if (this.xPos < 630)
							this.xPos += Math.ceil(w.getSpeed() * elapsedTime);
						else {
							return false; // frog does not move because it would go offscreen
						}
					}

					else {
						if (this.xPos > -5)
							this.xPos -= Math.ceil(w.getSpeed() * elapsedTime);
						else {
							return false;
						}
					}

					hasFloated = true;
					draw();
					return true;
				}

			}

			else {
				return false;// frog doesn't intersect with waterbound sprite, so it doesn't move
			}
		}

		return false; // frog would exit bounds if it moved, so it doesn't move
	}

	public void drown() {
		this.imageView.setScaleX(2.3);
		this.imageView.setScaleY(2.3);

		splashAudioClip.stop();
		splashAudioClip.play();

		die();
	}

	public void resetHasFloated() {
		hasFloated = false;
	}

	public void addScore(int score) {
		this.score += score;
	}
}
