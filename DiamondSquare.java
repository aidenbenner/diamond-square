package diamond_square;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DiamondSquare {

	public static ControlPanel cntrlPanel = new ControlPanel();

	public static void main(String args[]) throws InterruptedException {
		cntrlPanel.init();
		diamondSquare();
		Screen s = new Screen(data);

		
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.add(s);
		s.setPreferredSize(new Dimension(500,500));
		s.setMaximumSize(new Dimension(500,500));
		s.setMinimumSize(new Dimension(500,500));
		cntrlPanel.setPreferredSize(new Dimension(100,500));
		cntrlPanel.setMaximumSize(new Dimension(100,500));
		cntrlPanel.setMinimumSize(new Dimension(100,500));
		frame.setSize(800, 500);
		frame.add(cntrlPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			s.repaint();
		}
	}

	static int BOARD_SIZE = 129;
	public static double[][] data = new double[BOARD_SIZE][BOARD_SIZE];

	public static double[][] diamondSquare() {
		//initial values for corners 
		final double SEED = 127;

		data = new double[BOARD_SIZE][BOARD_SIZE];
		// seed the data
		data[0][0] = SEED;
		data[0][BOARD_SIZE - 1] = SEED;
		data[BOARD_SIZE - 1][0] = SEED;
		data[BOARD_SIZE - 1][BOARD_SIZE - 1] = SEED;
		
		double h = 127;
		Random r = new Random();
		for (int sideLength = BOARD_SIZE - 1; sideLength >= 2; sideLength /= 2, h /= 2.0) {
			int halfSide = sideLength / 2;
			for (int x = 0; x < BOARD_SIZE - 1; x += sideLength) {
				for (int y = 0; y < BOARD_SIZE - 1; y += sideLength) {
					double avg = data[x][y] + // top left
							data[x + sideLength][y] + // top right
							data[x][y + sideLength] + // lower left
							data[x + sideLength][y + sideLength];// lower right
					avg /= 4.0;

					// center is average plus random offset
					data[x + halfSide][y + halfSide] = avg
							+ (r.nextDouble() * 2 * h) - h;
				}
			}

			for (int x = 0; x < BOARD_SIZE - 1; x += halfSide) {
				for (int y = (x + halfSide) % sideLength; y < BOARD_SIZE - 1; y += sideLength) {
					double avg = data[(x - halfSide + BOARD_SIZE - 1)
							% (BOARD_SIZE - 1)][y]
							+ // left of center
							data[(x + halfSide) % (BOARD_SIZE - 1)][y] +
																	
							data[x][(y + halfSide) % (BOARD_SIZE - 1)] + 
																		
							data[x][(y - halfSide + BOARD_SIZE - 1)
									% (BOARD_SIZE - 1)]; // above center
					avg /= 4.0;
					avg = avg + (r.nextDouble() * 2 * h) - h;
					// update value for center of diamond
					data[x][y] = avg;
					if (x == 0)
						data[BOARD_SIZE - 1][y] = avg;
					if (y == 0)
						data[x][BOARD_SIZE - 1] = avg;
				}
			}
		}
		return data;
	}

	public static double[][] randGen() {
		Random r = new Random();
		int h = 20;
		for (int i = 0; i < data.length; i++) {
			for (int k = 0; k < data.length; k++) {
				data[i][k] += r.nextInt(2 * h) - h;
			}
		}
		return data;
	}


}