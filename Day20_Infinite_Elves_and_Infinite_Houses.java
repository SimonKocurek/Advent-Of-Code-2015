package AdventOfCode;

import java.util.Scanner;

public class Day20_Infinite_Elves_and_Infinite_Houses {
	public static void findHouse() {
		Scanner input = new Scanner(System.in);
		int searched =  input.nextInt() / 10;

		findIt:
		while (true) {
			int range = 1000;
			int house[] = new int[range];
			fillHouses(range, house);

			for (int i = 0; i < range; i ++) {
				if (house[i] == searched) {
					System.out.println( i);
					break findIt;
				}
			}
			System.out.println("fail");
		}
	}

	private static void fillHouses(int range, int[] presents) {
		for (int i = 0; i < range; i ++) {
			int difference = i + 1;
			for (int n = difference; n < range; n += difference) {
				presents[difference - 1] = n;
			}
		}
	}
}
