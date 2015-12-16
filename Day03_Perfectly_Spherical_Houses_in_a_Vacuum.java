package AdventOfCode;

import java.util.ArrayList;
import java.util.Scanner;

class House {
	private ArrayList<Integer> position_x = new ArrayList<>(0);
	private ArrayList<Integer> position_y = new ArrayList<>(0);
	public ArrayList<Boolean> visited = new ArrayList<>(0);

	public void Add(int X, int Y) {
		position_x.add(X);
		position_y.add(Y);
		visited.add(false);
	}

	public Boolean Exists(int X, int Y) {
		//get id
		for (int i = 0; i < position_x.size(); i++) {
			if (position_x.get(i) == X && position_y.get(i) == Y) {
				visited.set(i, true);
				return true;
			}
		}
		return false;
	}
}

public class Day03_Perfectly_Spherical_Houses_in_a_Vacuum {
	public static void Count_Duplicates() {
		Scanner input = new Scanner(System.in);
		String instructions = input.next();

		int x = 0;
		int y = 0;

		House house = new House();
		house.Add(x, y);

		for (char instruction : instructions.toCharArray()) {
			switch (instruction) {
				case '>': x++; break;
				case '<': x--; break;
				case '^': y++; break;
				case 'v': y--; break;
			}

			if (!house.Exists(x, y)) {
				house.Add(x, y);
			}
		}

		//int duplicates = 0;
		//for (boolean visit : house.visited) {
		//	if (visit) {
		//		duplicates++;
		//	}
		//}

		input.close();
		System.out.println(house.visited.size());
	}

	public static void TwoSantas() {
		Scanner input = new Scanner(System.in);
		String instructions = input.next();

		int[] x = {0,0};
		int[] y = {0,0};
		int santa = 1;

		House house = new House();
		house.Add(x[0], y[0]);

		for (char instruction : instructions.toCharArray()) {
			if (santa == 1) {
				santa = 0;
			} else {
				santa = 1;
			}

			switch (instruction) {
				case '>': x[santa]++; break;
				case '<': x[santa]--; break;
				case '^': y[santa]++; break;
				case 'v': y[santa]--; break;
			}

			if (!house.Exists(x[santa], y[santa])) {
				house.Add(x[santa], y[santa]);
			}
		}

		input.close();
		System.out.println(house.visited.size());
	}
}
