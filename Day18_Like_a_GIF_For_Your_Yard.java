package AdventOfCode;

import java.util.Scanner;

class Light {
	boolean current;
	boolean following;

	public Light(char input) {
		this.current = input == '#';
	}
}

public class Day18_Like_a_GIF_For_Your_Yard {
	Light[][] light = new Light[100][100];

	public int neighbours(int middleX, int middleY) {
		int count = 0;

		for (int x = middleX - 1; x < middleX+2; x ++) {
			for (int y = middleY - 1; y < middleY+2; y ++) {
				try {
					if ( light[y][x].current) {
						count ++;
					}
				} catch (ArrayIndexOutOfBoundsException ignored) {}
			}
		}

		if ( light[middleY][middleX].current)
			return count - 1;
		else
			return count;
	}

	public void gameOfLife() {
		Scanner input = new Scanner(System.in);
		char[] line;

		for (int y = 0; y < 100; y ++) {
			line = input.nextLine().toCharArray();
			for (int x = 0; x < 100; x ++) {
				light[y][x] = new Light( line[x]);
			}
		}

		light[0][0].current = true;
		light[0][99].current = true;
		light[99][0].current = true;
		light[99][99].current = true;
		for (int i = 0; i < 100; i ++) {

			for (int y = 0; y < 100; y ++) {
				for (int x = 0; x < 100; x ++) {
					int neighboursCount = neighbours(x, y);
					Light currentLight = light[y][x];

					if (currentLight.current)
						currentLight.following = neighboursCount == 2 || neighboursCount == 3;
					else
						currentLight.following = neighboursCount == 3;
				}
			}

			for (int y = 0; y < 100; y ++) {
				for (int x = 0; x < 100; x ++) {
					light[y][x].current = light[y][x].following;
				}
			}

			light[0][0].current = true;
			light[0][99].current = true;
			light[99][0].current = true;
			light[99][99].current = true;
		}

		int lit = 0;
		for (int y = 0; y < 100; y ++) {
			for (int x = 0; x < 100; x ++) {
				if ( light[y][x].current)
					lit ++;
			}
		}

		System.out.println(lit);
	}
}
