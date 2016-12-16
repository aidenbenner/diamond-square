package diamond_square;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Screen extends JPanel {
	private static final int width = 500;
	private static final int height = 500;
	public double pVal = Math.PI/4;
	public double waitVal = 1000;
	public static double[][] board;
	public Screen(double[][] data) {
		board = data;
	}

	long lastRefresh = 0;
	double[][] currGrid = null;
	double[][] nextGrid = null;

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (System.currentTimeMillis() - lastRefresh > waitVal) {
			if (nextGrid != null) {

			} else {
				currGrid = DiamondSquare.diamondSquare();
			}
			nextGrid = DiamondSquare.diamondSquare();
			lastRefresh = System.currentTimeMillis();
		}

		g.drawImage(getImage(currGrid), 0, 0, null);
		tweenArray(currGrid, nextGrid, pVal);
	}

	public void tweenArray(double[][] init, double[][] goal, double increment) {
		for (int i = 0; i < init.length; i++) {
			for (int k = 0; k < init[i].length; k++) {
				double error = goal[i][k] - init[i][k];
				// if(init[i][k] < goal[i][k])
				// {

				// init[i][k] += error * increment;
				// }
				// else{

				init[i][k] += error > 0 ?  increment : -increment;
				// }
			}
		}

	}

	public void saveImage(double[][] data) {
		BufferedImage output = new BufferedImage(data.length, data.length,
				BufferedImage.TYPE_INT_ARGB);
		board = data;
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				// System.out.println("called");
				// System.out.println(board[x][y]);
				board[x][y] = board[x][y];
				if (board[x][y] < 0) {
					board[x][y] = 0;
				}
				if (board[x][y] > 254) {
					board[x][y] = 254;
				}

				int xSquared = ((int) board[x][y] * (int) board[x][y]) / 255;
				int xCubed = ((int) board[x][y] * (int) board[x][y] * (int) board[x][y])
						/ (255 * 255);
				int xIn = (int) board[x][y];
				int sinX = Math.abs((int) (Math.sin(board[x][y]) * 255));
				int tanX = Math.abs((int) (Math.sin(board[x][y]) * 255));
				if (tanX > 255) {
					tanX = 255;
				}
				int red = xCubed;
				int green = xSquared;
				int blue = xIn;// sinX; //255 - xSquared;//(int) board[x][y];
				if (blue < 0) {
					blue = 0;
				}

				Color pixelColor = new Color(red, green, blue);
				x = x * 2;
				y = y * 2;
				output.setRGB(x, y, pixelColor.getRGB());
				output.setRGB(x+1, y, pixelColor.getRGB());
				output.setRGB(x+1, y+1, pixelColor.getRGB());
				output.setRGB(x, y+1, pixelColor.getRGB());
				x /= 2;
				y /= 2;
			}
		}
		try {
			Calendar calendar = Calendar.getInstance();
			Timestamp currentTimestamp = new java.sql.Timestamp(calendar
					.getTime().getTime());
			File outputfile = new File(
					"H:\\Cs 301 Word Docs\\CS302\\Diamond_Square\\"
							+ currentTimestamp.getDate() + "-"
							+ currentTimestamp.getHours() + "-"
							+ currentTimestamp.getMinutes() + "-"
							+ currentTimestamp.getSeconds() + "" + ".png");
			ImageIO.write(output, "png", outputfile);
		} catch (Exception e) {
			System.out.println("Error writing file");
		}
	}

	static Color ComputeColor(float c) {
		float Red = 0;
		float Green = 0;
		float Blue = 0;

		if (c < 0.5f) {
			Red = c * 2;
		} else {
			Red = (1.0f - c) * 2;
		}

		if (c >= 0.3f && c < 0.8f) {
			Green = (c - 0.3f) * 2;
		} else if (c < 0.3f) {
			Green = (0.3f - c) * 2;
		} else {
			Green = (1.3f - c) * 2;
		}

		if (c >= 0.5f) {
			Blue = (c - 0.5f) * 2;
		} else {
			Blue = (0.5f - c) * 2;
		}

		return new Color(Red, Green, Blue);
	}

	public static BufferedImage getImage(double[][] data) {
		BufferedImage output = new BufferedImage(data.length * 3, data.length * 3,
				BufferedImage.TYPE_INT_ARGB);
		board = data;
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				// System.out.println("called");
				// System.out.println(board[x][y]);
				board[x][y] = board[x][y];
				if (board[x][y] < 0) {
					board[x][y] = 0;
				}
				if (board[x][y] > 254) {
					board[x][y] = 254;
				}

				int xSquared = ((int) board[x][y] * (int) board[x][y]) / 255;
				int xCubed = ((int) board[x][y] * (int) board[x][y] * (int) board[x][y])
						/ (255 * 255);
				int xIn = (int) board[x][y];
				int sinX = Math.abs((int) (Math.sin(board[x][y]) * 255));
				int tanX = Math.abs((int) (Math.sin(board[x][y]) * 255));
				if (tanX > 255) {
					tanX = 255;
				}

				// effect 1
				int red = xCubed;
				int green = xIn;
				int blue = xIn;// sinX; //255 - xSquared;//(int) board[x][y];
				if (blue < 0) {
					blue = 0;
				}
				Color pixelColor = new Color(red, green, blue);// ComputeColor((float)
				//effect 2
				if(DiamondSquare.cntrlPanel.s2.isSelected()) {
					red = xCubed;
					green = xIn;
					blue = sinX; // 255 - xSquared;//(int) board[x][y];
					if (blue < 0) {
						blue = 0;
					}
					pixelColor = new Color(red, green, blue);// 
				} else if (DiamondSquare.cntrlPanel.s3.isSelected()) {
					pixelColor = ComputeColor((float)(board[x][y]/255));
				}	
				x = x * 3;
				y = y * 3;
				output.setRGB(x, y, pixelColor.getRGB());
				output.setRGB(x, y+1, pixelColor.getRGB());
				output.setRGB(x, y+2, pixelColor.getRGB());
				output.setRGB(x+1, y, pixelColor.getRGB());
				output.setRGB(x+1, y+1, pixelColor.getRGB());
				output.setRGB(x+1, y+2, pixelColor.getRGB());
				output.setRGB(x+2, y, pixelColor.getRGB());
				output.setRGB(x+2, y+1, pixelColor.getRGB());
				output.setRGB(x+2, y+2, pixelColor.getRGB());
				x /= 3;
				y /= 3;									
			}
		}
		try {
			Calendar calendar = Calendar.getInstance();
			Timestamp currentTimestamp = new java.sql.Timestamp(calendar
					.getTime().getTime());
			File outputfile = new File(
					"H:\\Cs 301 Word Docs\\CS302\\Diamond_Square\\"
							+ currentTimestamp.getDate() + "-"
							+ currentTimestamp.getHours() + "-"
							+ currentTimestamp.getMinutes() + "-"
							+ currentTimestamp.getSeconds() + "" + ".png");
			ImageIO.write(output, "png", outputfile);
		} catch (Exception e) {
			System.out.println("Error writing file");
		}
		return output;
	}

	public void paintScreen(double[][] data) {
		getImage(data);

	}
}