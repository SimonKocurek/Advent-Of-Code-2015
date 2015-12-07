package AdventOfCode;

import java.util.HashMap;
import java.util.Scanner;

class Signal {

	public void assign(char var, int assigned) {
		variable.put(var, assigned);
	}

	public void and(char var, int x, int y) {
		variable.put(var, x & y);
	}

	public void or(char var, int x, int y) {
		variable.put(var, x | y);
	}

	public void lShift(char var, char x, int shift) {
		variable.put(var, variable.get(x) << shift);
	}

	public void rShift(char var, char x, int shift) {
		variable.put(var, variable.get(x) >> shift);
	}

	public void not(char var, int x) {
		variable.put(var, ~ x);
	}
}


public class Day7_Some_Assembly_Required {
	public static void signals() {
		Scanner input = new Scanner(System.in);
		HashMap<Character, Integer> variable = new HashMap<>();
		String line;

		while (!(line = input.next()).equals("exit")) {
			String[] word = line.split(" ");
			char var = word[word.length - 1].charAt(0);
		}
	}
}
