package AdventOfCode;

import java.util.Scanner;

public class Day2_I_Was_Told_There_Would_Be_No_Math {
	public static void Size() {
		Scanner input = new Scanner(System.in);
		int total = 0;

		while (true) {
			String [] line = input.nextLine().split("x");
			int x = Integer.valueOf(line[0]);
			int y = Integer.valueOf(line[1]);
			int z = Integer.valueOf(line[2]);

			if (x > y) {
				int swap = x;
				x = y;
				y = swap;
			}
			if (x > z) {
				int swap = x;
				x = z;
				z = swap;
			}
			if (y > z) {
				int swap = y;
				y = z;
				z = swap;
			}

			total += 2 * (x*y + x*z + z*y) + x*y;

			System.out.println(total);
		}
	}

	public static void ribbon() {
		Scanner input = new Scanner(System.in);
		int total = 0;

		while (true) {
			String [] line = input.nextLine().split("x");
			int x = Integer.valueOf(line[0]);
			int y = Integer.valueOf(line[1]);
			int z = Integer.valueOf(line[2]);

			if (x > y) {
				int swap = x;
				x = y;
				y = swap;
			}
			if (x > z) {
				int swap = x;
				x = z;
				z = swap;
			}
			if (y > z) {
				int swap = y;
				y = z;
				z = swap;
			}

			total += 2 * (x+y) + x*y*z;

			System.out.println(total);
		}
	}
}
