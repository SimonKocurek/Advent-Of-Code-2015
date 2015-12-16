package AdventOfCode;

import java.util.Scanner;

class Lights {
	private static boolean[][] light = new boolean[1000][1000];

	public static void toggleOn(int[] x, int[] y) {
		for (int i = y[0]; i <= y[1]; i++) {
			for (int n = x[0]; n <= x[1]; n++) {
				light[i][n] = true;
			}
		}
	}

	public static void toggleOff(int[] x, int[] y) {
		for (int i = y[0]; i <= y[1]; i++) {
			for (int n = x[0]; n <= x[1]; n++) {
				light[i][n] = false;
			}
		}
	}

	public static void toggle(int[] x, int[] y) {
		for (int i = y[0]; i <= y[1]; i++) {
			for (int n = x[0]; n <= x[1]; n++) {
				light[i][n] = !light[i][n];
			}
		}
	}

	public static int litCount() {
		int lit = 0;

		for (int i = 0; i < 1000; i++) {
			for (int n = 0; n < 1000; n++) {
				if (light[i][n]) {
					lit++;
				}
			}
		}

		return lit;
	}
}

class BrightLights {
	private static int[][] light = new int[1000][1000];

	public static void toggleOn(int[] x, int[] y) {
		for (int i = y[0]; i <= y[1]; i++) {
			for (int n = x[0]; n <= x[1]; n++) {
				light[i][n]++;
			}
		}
	}

	public static void toggleOff(int[] x, int[] y) {
		for (int i = y[0]; i <= y[1]; i++) {
			for (int n = x[0]; n <= x[1]; n++) {
				if (light[i][n] > 0) {
					light[i][n]--;
				}
			}
		}
	}

	public static void toggle(int[] x, int[] y) {
		for (int i = y[0]; i <= y[1]; i++) {
			for (int n = x[0]; n <= x[1]; n++) {
				light[i][n] += 2;
			}
		}
	}

	public static long brightness() {
		long brightness = 0;

		for (int i = 0; i < 1000; i++) {
			for (int n = 0; n < 1000; n++) {
				brightness += light[i][n];
			}
		}

		return brightness;
	}
}

public class Day06_Probably_a_Fire_Hazard {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		BrightLights lights = new BrightLights();
		String line;

		while (!(line = input.nextLine()).equals("exit")) {
			String[] words = line.split("\\s+");
			int[] x = new int[2];
			int[] y = new int[2];

			if (words[0].equals( "toggle")) {
				x[0] = Integer.valueOf(words[1].split(",")[0]);
				x[1] = Integer.valueOf(words[3].split(",")[0]);
				y[0] = Integer.valueOf(words[1].split(",")[1]);
				y[1] = Integer.valueOf(words[3].split(",")[1]);
			} else {
				x[0] = Integer.valueOf(words[2].split(",")[0]);
				x[1] = Integer.valueOf(words[4].split(",")[0]);
				y[0] = Integer.valueOf(words[2].split(",")[1]);
				y[1] = Integer.valueOf(words[4].split(",")[1]);
			}

			switch (words[1]) {
				case "off": lights.toggleOff(x, y); break;
				case "on": lights.toggleOn(x, y); break;
				default: lights.toggle(x, y); break;
			}
		}

		input.close();
		System.out.println(lights.brightness());
	}

	public static void main2(String[] args) {
		Scanner input = new Scanner(System.in);
		Lights lights = new Lights();
		String line;

		while (!(line = input.nextLine()).equals("exit")) {
			String[] words = line.split("\\s+");
			int[] x = new int[2];
			int[] y = new int[2];

			if (words[0].equals( "toggle")) {
				x[0] = Integer.valueOf(words[1].split(",")[0]);
				x[1] = Integer.valueOf(words[3].split(",")[0]);
				y[0] = Integer.valueOf(words[1].split(",")[1]);
				y[1] = Integer.valueOf(words[3].split(",")[1]);
			} else {
				x[0] = Integer.valueOf(words[2].split(",")[0]);
				x[1] = Integer.valueOf(words[4].split(",")[0]);
				y[0] = Integer.valueOf(words[2].split(",")[1]);
				y[1] = Integer.valueOf(words[4].split(",")[1]);
			}

			switch (words[1]) {
				case "off": lights.toggleOff(x, y); break;
				case "on": lights.toggleOn(x, y); break;
				default: lights.toggle(x, y); break;
			}
		}

		input.close();
		System.out.println(lights.litCount());
	}
}
