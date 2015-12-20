package AdventOfCode;

import java.util.Scanner;

public class Day20_Infinite_Elves_and_Infinite_Houses {
	public static void findHouse() {
		Scanner input = new Scanner(System.in);
		int searched =  input.nextInt();
		int range = 1;

		findIt:
		while (true) {
			int house[] = new int[range];

			fillHouses(range, house);

			for (int i = 0; i < range; i ++) {
				if (house[i] >= searched) {
					System.out.println( i);
					break findIt;
				}
			}
			range *= 2;
		}
	}

	private static void fillHouses(int range, int[] presents) {
		for (int i = 0; i < range; i ++) {
			int difference = i + 1;
			for (int n = difference, visits = 0; n < range; n += difference) {
				visits ++;
				presents[n] += difference * 11;

				if (visits == 50)
					break;
			}
		}
	}
}
