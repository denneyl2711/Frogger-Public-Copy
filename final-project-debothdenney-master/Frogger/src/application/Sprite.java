/**
 * 
 */
package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author DeBothCasey
 *
 */
public class Sprite {
	private Image allSprites;
	protected ImageView imageView;

	// location of sprite on canvas
	protected int xPos;
	protected int yPos;

	// position of sprite location/size of window on froggerSpritesTransparent.png
	protected double x;
	protected double y;
	protected double w;
	protected double h;

	// Constructor
	public Sprite(double x, double y, double w, double h) {
		allSprites = new Image("froggerSpritesTransparent.png");
		imageView = new ImageView(allSprites);
		imageView.setViewport(new Rectangle2D(x, y, w, h));
		imageView.setScaleX(3);
		imageView.setScaleY(3);

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	// returns imageView
	public ImageView getImgView() {
		return this.imageView;
	}

	public void setPosition(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public void draw() {
		imageView.setX(xPos);
		imageView.setY(yPos);
	}

	public boolean intersects(Sprite s) {
		return s.getImgView().getBoundsInParent().intersects(this.getImgView().getBoundsInParent());
		// return true;
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(xPos, yPos, w, h);

	}
}
