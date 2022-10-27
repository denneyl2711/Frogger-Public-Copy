package application;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.AudioClip;

public class Frogger extends Application {
	private long lastNanoTime;
	private long startNanoTime;
	private Frog frog;
	private Font font, font2;
	private FlowPane scorePane, livesPane, timePane, endgameTxtPane;
	private Text outputScoreTxt, outputLivesTxt, outputScoreNum, outputLivesNum, timeOutputNum, timeOutputTxt,
			endgameTxt;
	private int timeCount = 45;
	private boolean playAgain, gameOver;

	// Game over sound taken from soundboard.com
	private File gameOverFile = new File("gameOver.mp3");
	private AudioClip gameOverAudioClip = new AudioClip(gameOverFile.toURI().toString());

	// Win sound taken from mixkit.co
	private File winFile = new File("win.wav");
	private AudioClip winAudioClip = new AudioClip(winFile.toURI().toString());

	@Override
	public void start(Stage primaryStage) {
		try {

			final int CANVAS_X = 600;
			final int CANVAS_Y = 700;

			Rectangle spawn = new Rectangle(0, CANVAS_Y - 50, CANVAS_X, 50);
			spawn.setFill(Color.PURPLE);

			Rectangle road = new Rectangle(0, CANVAS_Y - 300, CANVAS_X, 250);
			road.setFill(Color.BLACK);

			Rectangle middle = new Rectangle(0, CANVAS_Y - 350, CANVAS_X, 50);
			middle.setFill(Color.PURPLE);

			Rectangle water = new Rectangle(0, CANVAS_Y - 600, CANVAS_X, 250);
			water.setFill(Color.DARKBLUE);

			Rectangle end = new Rectangle(0, CANVAS_Y - 650, CANVAS_X, 50);
			end.setFill(Color.GREEN);

			Rectangle scoreBoard = new Rectangle(0, 0, CANVAS_X, 50);
			scoreBoard.setFill(Color.DARKBLUE);

			Group background = new Group(spawn, road, middle, water, end, scoreBoard);

			lastNanoTime = System.nanoTime(); // initialize starting time

			Vehicle hotRod1 = new Vehicle(19, 116, 16, 16, 75, false, 100, 605);
			Vehicle hotRod2 = new Vehicle(19, 116, 16, 16, 75, false, 250, 605);
			Vehicle hotRod3 = new Vehicle(19, 116, 16, 16, 75, false, 400, 605);

			Vehicle snowPlow1 = new Vehicle(55, 116, 16, 16, 10, true, 50, 545);
			Vehicle snowPlow2 = new Vehicle(55, 116, 16, 16, 10, true, 450, 545);

			Vehicle sportsCar1 = new Vehicle(1, 116, 16, 16, 50, false, 475, 485);
			Vehicle sportsCar2 = new Vehicle(1, 116, 16, 16, 50, false, 325, 485);
			Vehicle sportsCar3 = new Vehicle(1, 116, 16, 16, 50, false, 25, 485);

			Vehicle raceCar1 = new Vehicle(37, 116, 16, 16, 250, true, 80, 425);
			Vehicle raceCar2 = new Vehicle(37, 116, 16, 16, 250, true, 480, 425);

			Group vehicles = new Group(hotRod1.getImgView(), hotRod2.getImgView(), hotRod3.getImgView(),
					snowPlow1.getImgView(), snowPlow2.getImgView(), sportsCar1.getImgView(), sportsCar2.getImgView(),
					sportsCar3.getImgView(), raceCar1.getImgView(), raceCar2.getImgView());

			Log leftEndLog1 = new Log(1, 134, 16, 16, false, 40, 265, 1);
			Log middleLog1 = new Log(19, 134, 16, 16, false, 86, 265, 1);
			Log rightEndLog1 = new Log(37, 134, 16, 16, false, 132, 265, 1);

			Group smallLog1 = new Group(leftEndLog1.getImgView(), middleLog1.getImgView(), rightEndLog1.getImgView());

			Log leftEndLog6 = new Log(1, 134, 16, 16, false, 272, 265, 1);
			Log middleLog6 = new Log(19, 134, 16, 16, false, 318, 265, 1);
			Log rightEndLog6 = new Log(37, 134, 16, 16, false, 364, 265, 1);

			Group smallLog6 = new Group(leftEndLog6.getImgView(), middleLog6.getImgView(), rightEndLog6.getImgView());

			Log leftEndLog7 = new Log(1, 134, 16, 16, false, 504, 265, 1);
			Log middleLog7 = new Log(19, 134, 16, 16, false, 550, 265, 1);
			Log rightEndLog7 = new Log(37, 134, 16, 16, false, 596, 265, 1);

			Group smallLog7 = new Group(leftEndLog7.getImgView(), middleLog7.getImgView(), rightEndLog7.getImgView());

			Group bottomLogs = new Group(smallLog1, smallLog6, smallLog7);

			// ***********************************************************************************

			Log leftEndLog2 = new Log(1, 134, 16, 16, true, 0, 215, 1);
			Log middleLog2 = new Log(19, 134, 16, 16, true, 46, 215, 1);
			Log rightEndLog2 = new Log(37, 134, 16, 16, true, 92, 215, 1);

			Group smallLog2 = new Group(leftEndLog2.getImgView(), middleLog2.getImgView(), rightEndLog2.getImgView());

			Log leftEndLog3 = new Log(1, 134, 16, 16, true, 180, 215, 1);
			Log middleLog3 = new Log(19, 134, 16, 16, true, 226, 215, 1);
			Log rightEndLog3 = new Log(37, 134, 16, 16, true, 272, 215, 1);

			Group smallLog3 = new Group(leftEndLog3.getImgView(), middleLog3.getImgView(), rightEndLog3.getImgView());

			Log leftEndLog4 = new Log(1, 134, 16, 16, true, 360, 215, 1);
			Log middleLog4 = new Log(19, 134, 16, 16, true, 406, 215, 1);
			Log rightEndLog4 = new Log(37, 134, 16, 16, true, 452, 215, 1);

			Group smallLog4 = new Group(leftEndLog4.getImgView(), middleLog4.getImgView(), rightEndLog4.getImgView());

			Log leftEndLog5 = new Log(1, 134, 16, 16, true, 540, 215, 1);
			Log middleLog5 = new Log(19, 134, 16, 16, true, 586, 215, 1);
			Log rightEndLog5 = new Log(37, 134, 16, 16, true, 632, 215, 1);

			Group smallLog5 = new Group(leftEndLog5.getImgView(), middleLog5.getImgView(), rightEndLog5.getImgView());

			Group middleLogs = new Group(smallLog2, smallLog3, smallLog4, smallLog5);

			// ********************************************************************************

			Log leftEndLog8 = new Log(1, 134, 16, 16, true, 40, 115, 1);
			Log middleLog8 = new Log(19, 134, 16, 16, true, 86, 115, 1);
			Log rightEndLog8 = new Log(37, 134, 16, 16, true, 132, 115, 1);

			Group smallLog8 = new Group(leftEndLog8.getImgView(), middleLog8.getImgView(), rightEndLog8.getImgView());

			Log leftEndLog9 = new Log(1, 134, 16, 16, true, 272, 115, 1);
			Log middleLog9 = new Log(19, 134, 16, 16, true, 318, 115, 1);
			Log rightEndLog9 = new Log(37, 134, 16, 16, true, 364, 115, 1);

			Group smallLog9 = new Group(leftEndLog9.getImgView(), middleLog9.getImgView(), rightEndLog9.getImgView());

			Log leftEndLog0 = new Log(1, 134, 16, 16, true, 504, 115, 1);
			Log middleLog0 = new Log(19, 134, 16, 16, true, 550, 115, 1);
			Log rightEndLog0 = new Log(37, 134, 16, 16, true, 596, 115, 1);

			Group smallLog0 = new Group(leftEndLog0.getImgView(), middleLog0.getImgView(), rightEndLog0.getImgView());

			Group topLogs = new Group(smallLog8, smallLog9, smallLog0);

			// ***********************************************************************************

			Group logs = new Group(bottomLogs, middleLogs, topLogs);

			Turtle turtle1 = new Turtle(1, 150, 16, 16, false, 40, 157, 100);
			Turtle turtle2 = new Turtle(1, 150, 16, 16, false, 90, 157, 100);
			Turtle turtle3 = new Turtle(1, 150, 16, 16, false, 140, 157, 100);

			Turtle turtle4 = new Turtle(1, 150, 16, 16, true, 50, 320, 10);
			Turtle turtle5 = new Turtle(1, 150, 16, 16, true, 100, 320, 10);
			Turtle turtle6 = new Turtle(1, 150, 16, 16, true, 150, 320, 10);

			Turtle turtle7 = new Turtle(1, 150, 16, 16, true, 200, 320, 10);
			Turtle turtle8 = new Turtle(1, 150, 16, 16, true, 250, 320, 10);
			Turtle turtle9 = new Turtle(1, 150, 16, 16, true, 300, 320, 10);

			Turtle turtle10 = new Turtle(1, 150, 16, 16, false, 350, 157, 100);
			Turtle turtle11 = new Turtle(1, 150, 16, 16, false, 400, 157, 100);
			Turtle turtle12 = new Turtle(1, 150, 16, 16, false, 450, 157, 100);

			Turtle turtle13 = new Turtle(1, 150, 16, 16, true, 350, 320, 10);
			Turtle turtle14 = new Turtle(1, 150, 16, 16, true, 400, 320, 10);
			Turtle turtle15 = new Turtle(1, 150, 16, 16, true, 450, 320, 10);

			Group turtles = new Group(turtle1.getImgView(), turtle2.getImgView(), turtle3.getImgView(),
					turtle4.getImgView(), turtle5.getImgView(), turtle6.getImgView(), turtle7.getImgView(),
					turtle8.getImgView(), turtle9.getImgView(), turtle10.getImgView(), turtle11.getImgView(),
					turtle12.getImgView(), turtle13.getImgView(), turtle14.getImgView(), turtle15.getImgView());

				frog = new Frog(2, 1, 13, 13);

				frog.draw();

				// ****************************************************************
				// Score -- Displays the score (Score: ##)

				font = new Font("Helvetica", 30);
				font2 = new Font("Helvetica", 25);
				outputScoreTxt = new Text("Score: ");
				outputScoreTxt.setFont(font);
				outputScoreTxt.setFill(Color.WHITE);
				outputScoreNum = new Text("" + frog.getScore());
				outputScoreNum.setFont(font);
				outputScoreNum.setFill(Color.RED);
				outputScoreNum.setTranslateY(2);
				scorePane = new FlowPane(outputScoreTxt, outputScoreNum);
				scorePane.setAlignment(Pos.TOP_LEFT);
				scorePane.setOnKeyPressed(this::processKeyPressed);

				// ****************************************************************
				// Lives -- Displays the lives (Lives: ##)
				outputLivesTxt = new Text("Lives: ");
				outputLivesTxt.setFont(font);
				outputLivesTxt.setFill(Color.WHITE);
				outputLivesNum = new Text("" + frog.getLives());
				outputLivesNum.setFont(font2);
				outputLivesNum.setFill(Color.RED);
				outputLivesNum.setTranslateY(2);
				livesPane = new FlowPane(outputLivesTxt, outputLivesNum);
				livesPane.setAlignment(Pos.TOP_CENTER);
				livesPane.setTranslateX(20);
				livesPane.setOnKeyPressed(this::processKeyPressed);

				// ****************************************************************
				// Time -- Displays the time (seconds)
				timeOutputTxt = new Text("Time: ");
				timeOutputTxt.setFont(font);
				timeOutputTxt.setFill(Color.WHITE);
				timeOutputNum = new Text("" + timeCount);
				timeOutputNum.setFont(font2);
				timeOutputNum.setFill(Color.RED);
				timeOutputNum.setTranslateY(2);
				timePane = new FlowPane(timeOutputTxt, timeOutputNum);
				timePane.setAlignment(Pos.TOP_RIGHT);
				timePane.setTranslateX(20);
				timePane.setOnKeyPressed(this::processKeyPressed);

				// *****************************************************************
				// Endgame text -- Displays the final score at the end of the game
				endgameTxt = new Text("");
				endgameTxt.setFill(Color.WHITE);
				endgameTxt.setFont(font);
				endgameTxtPane = new FlowPane(endgameTxt);
				endgameTxtPane.setAlignment(Pos.CENTER);
				endgameTxtPane.setTranslateY(350);
				endgameTxtPane.setTranslateX(50);

				// *****************************************************************

				startNanoTime = System.nanoTime(); // initializes to the system time
				new AnimationTimer() {
					// must override this method that is called 60 times per second (frame rate)
					public void handle(long currentNanoTime) {
						// calculates time since last frame
						double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
						lastNanoTime = currentNanoTime;

						double time = (currentNanoTime - startNanoTime) / 1000000000.0;

						// check for hostile collision
						if (frog.intersects(hotRod1) || frog.intersects(hotRod2) || frog.intersects(hotRod3)
								|| frog.intersects(sportsCar1) || frog.intersects(sportsCar2)
								|| frog.intersects(sportsCar3) || frog.intersects(raceCar1) || frog.intersects(raceCar2)
								|| frog.intersects(snowPlow1) || frog.intersects(snowPlow2)) {
							frog.die();
							outputLivesNum.setText("" + (frog.getLives()));
						}

						// check for waterbound collision
						// if frog hits a waterbound object and hasn't moved yet, float with it
						// else, drown in the water
						if (!frog.floatWithWaterbound(turtle1, elapsedTime)
								& !(frog.floatWithWaterbound(turtle2, elapsedTime))
								& !(frog.floatWithWaterbound(turtle3, elapsedTime))
								& !(frog.floatWithWaterbound(turtle4, elapsedTime))
								& !(frog.floatWithWaterbound(turtle5, elapsedTime))
								& !(frog.floatWithWaterbound(turtle6, elapsedTime))
								& !(frog.floatWithWaterbound(turtle7, elapsedTime))
								& !(frog.floatWithWaterbound(turtle8, elapsedTime))
								& !(frog.floatWithWaterbound(turtle9, elapsedTime))
								& !(frog.floatWithWaterbound(turtle10, elapsedTime))
								& !(frog.floatWithWaterbound(turtle11, elapsedTime))
								& !(frog.floatWithWaterbound(turtle12, elapsedTime))
								& !(frog.floatWithWaterbound(turtle13, elapsedTime))
								& !(frog.floatWithWaterbound(turtle14, elapsedTime))
								& !(frog.floatWithWaterbound(turtle15, elapsedTime))

								& !(frog.floatWithWaterbound(leftEndLog1, elapsedTime))
								& !(frog.floatWithWaterbound(middleLog1, elapsedTime))
								& !(frog.floatWithWaterbound(rightEndLog1, elapsedTime))

								& !(frog.floatWithWaterbound(leftEndLog2, elapsedTime))
								& !(frog.floatWithWaterbound(middleLog2, elapsedTime))
								& !(frog.floatWithWaterbound(rightEndLog2, elapsedTime))

								& !(frog.floatWithWaterbound(leftEndLog3, elapsedTime))
								& !(frog.floatWithWaterbound(middleLog3, elapsedTime))
								& !(frog.floatWithWaterbound(rightEndLog3, elapsedTime))

								& !(frog.floatWithWaterbound(leftEndLog4, elapsedTime))
								& !(frog.floatWithWaterbound(middleLog4, elapsedTime))
								& !(frog.floatWithWaterbound(rightEndLog4, elapsedTime))

								& !(frog.floatWithWaterbound(leftEndLog5, elapsedTime))
								& !(frog.floatWithWaterbound(middleLog5, elapsedTime))
								& !(frog.floatWithWaterbound(rightEndLog5, elapsedTime))

								& !(frog.floatWithWaterbound(leftEndLog6, elapsedTime))
								& !(frog.floatWithWaterbound(middleLog6, elapsedTime))
								& !(frog.floatWithWaterbound(rightEndLog6, elapsedTime))

								& !(frog.floatWithWaterbound(leftEndLog7, elapsedTime))
								& !(frog.floatWithWaterbound(middleLog7, elapsedTime))
								& !(frog.floatWithWaterbound(rightEndLog7, elapsedTime))

								& !(frog.floatWithWaterbound(leftEndLog8, elapsedTime))
								& !(frog.floatWithWaterbound(middleLog8, elapsedTime))
								& !(frog.floatWithWaterbound(rightEndLog8, elapsedTime))

								& !(frog.floatWithWaterbound(leftEndLog9, elapsedTime))
								& !(frog.floatWithWaterbound(middleLog9, elapsedTime))
								& !(frog.floatWithWaterbound(rightEndLog9, elapsedTime))

								& !(frog.floatWithWaterbound(leftEndLog0, elapsedTime))
								& !(frog.floatWithWaterbound(middleLog0, elapsedTime))
								& !(frog.floatWithWaterbound(rightEndLog0, elapsedTime))) {

							// if frog is in the water and not floating, drown
							if (frog.getYPos() < 350 && frog.getYPos() > 100) {
								frog.drown();
							}

						}
						frog.resetHasFloated();

						hotRod1.drive(elapsedTime);
						hotRod2.drive(elapsedTime);
						hotRod3.drive(elapsedTime);

						snowPlow1.drive(elapsedTime);
						snowPlow2.drive(elapsedTime);

						sportsCar1.drive(elapsedTime);
						sportsCar2.drive(elapsedTime);
						sportsCar3.drive(elapsedTime);

						raceCar1.drive(elapsedTime);
						raceCar2.drive(elapsedTime);

						// *********************************************

						leftEndLog1.waterFloat(elapsedTime);
						middleLog1.waterFloat(elapsedTime);
						rightEndLog1.waterFloat(elapsedTime);

						leftEndLog2.waterFloat(elapsedTime);
						middleLog2.waterFloat(elapsedTime);
						rightEndLog2.waterFloat(elapsedTime);

						leftEndLog3.waterFloat(elapsedTime);
						middleLog3.waterFloat(elapsedTime);
						rightEndLog3.waterFloat(elapsedTime);

						leftEndLog4.waterFloat(elapsedTime);
						middleLog4.waterFloat(elapsedTime);
						rightEndLog4.waterFloat(elapsedTime);

						leftEndLog5.waterFloat(elapsedTime);
						middleLog5.waterFloat(elapsedTime);
						rightEndLog5.waterFloat(elapsedTime);

						leftEndLog6.waterFloat(elapsedTime);
						middleLog6.waterFloat(elapsedTime);
						rightEndLog6.waterFloat(elapsedTime);

						leftEndLog7.waterFloat(elapsedTime);
						middleLog7.waterFloat(elapsedTime);
						rightEndLog7.waterFloat(elapsedTime);

						leftEndLog8.waterFloat(elapsedTime);
						middleLog8.waterFloat(elapsedTime);
						rightEndLog8.waterFloat(elapsedTime);

						leftEndLog9.waterFloat(elapsedTime);
						middleLog9.waterFloat(elapsedTime);
						rightEndLog9.waterFloat(elapsedTime);

						leftEndLog0.waterFloat(elapsedTime);
						middleLog0.waterFloat(elapsedTime);
						rightEndLog0.waterFloat(elapsedTime);

						turtle1.waterFloat(elapsedTime);
						turtle2.waterFloat(elapsedTime);
						turtle3.waterFloat(elapsedTime);
						turtle4.waterFloat(elapsedTime);
						turtle5.waterFloat(elapsedTime);
						turtle6.waterFloat(elapsedTime);
						turtle7.waterFloat(elapsedTime);
						turtle8.waterFloat(elapsedTime);
						turtle9.waterFloat(elapsedTime);
						turtle10.waterFloat(elapsedTime);
						turtle11.waterFloat(elapsedTime);
						turtle12.waterFloat(elapsedTime);
						turtle13.waterFloat(elapsedTime);
						turtle14.waterFloat(elapsedTime);
						turtle15.waterFloat(elapsedTime);

						// swimming animation
						if (time > 1) {
							turtle1.swimOrDive(elapsedTime);
							turtle2.swimOrDive(elapsedTime);
							turtle3.swimOrDive(elapsedTime);
							turtle4.swimOrDive(elapsedTime);
							turtle5.swimOrDive(elapsedTime);
							turtle6.swimOrDive(elapsedTime);
							turtle7.swimOrDive(elapsedTime);
							turtle8.swimOrDive(elapsedTime);
							turtle9.swimOrDive(elapsedTime);
							turtle10.swimOrDive(elapsedTime);
							turtle11.swimOrDive(elapsedTime);
							turtle12.swimOrDive(elapsedTime);
							turtle13.swimOrDive(elapsedTime);
							turtle14.swimOrDive(elapsedTime);
							turtle15.swimOrDive(elapsedTime);

							// Counts timer down and creates time. Frog dies when time runs out or time
							// reset when frog wins.
							timeCount--;
							timeOutputNum.setText("" + timeCount);
							if (timeCount <= 0) {
								frog.die();
								outputLivesNum.setText("" + frog.getLives());
								timeCount = 45;
							}

							startNanoTime = currentNanoTime; // resets the beginning so we can start counting again.
						}

						// if frog reaches the end
						if (frog.getYPos() <= 80) {
							frog.spawn();
							frog.addScore(50 + (timeCount * 10));

							outputScoreNum.setText("" + frog.getScore());
							winAudioClip.play();

							timeCount = 45;

						}
						// Checks if lives run out
						if (frog.getLives() <= 0) {
							endgameTxt.setText("Game Over!     You scored " + frog.getScore() + " points!");
							stop();
							gameOverAudioClip.play();
							gameOver = true;
						}
						else {
							gameOver = false;
						}
					}
				}.start();
			
			
			
			
			Group root = new Group(background, vehicles, logs, turtles, frog.getImgView(), scorePane, livesPane,
					timePane, endgameTxtPane);
			Scene scene = new Scene(root, CANVAS_X, CANVAS_Y);

			scene.setOnKeyPressed(this::processKeyPressed);
			primaryStage.setTitle("Highway and River Crossing Green Amphibian!");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	// Handles movement and updates score/lives
	public void processKeyPressed(KeyEvent event) {
		frog.move(event);

		outputScoreNum.setText("" + frog.getScore());
		outputLivesNum.setText("" + frog.getLives());

	}

	public static void main(String[] args) {
		launch(args);
	}

}
