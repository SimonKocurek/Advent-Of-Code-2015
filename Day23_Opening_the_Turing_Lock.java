package AdventOfCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day23_Opening_the_Turing_Lock {
	public static void getB() {
		Scanner input = new Scanner(System.in);
		String inLine;
		ArrayList<String[]> line = new ArrayList<>();

		while (!(inLine = input.nextLine()).equals("")) {
			line.add( inLine.split(" "));
		}

		HashMap<Character, Integer> registers = new HashMap<>();
		registers.put('a', 1);
		registers.put('b', 0);
		int position = -1;

		while (position != line.size() - 1) {
			position ++;
			String[] currentLine = line.get(position);
			char register = currentLine[1].charAt(0);

			char reg = currentLine[1].charAt(0);
			int value;

			switch (currentLine[0]) {
				case "inc":
					value = registers.get( currentLine[1].charAt(0));
					registers.put( reg, value + 1);
					break;

				case "hlf":
					value = registers.get( currentLine[1].charAt(0));
					registers.put( reg, value / 2);
					break;

				case "tpl":
					value = registers.get( currentLine[1].charAt(0));
					registers.put( reg, value * 3);
					break;

				case "jmp":
					position += Integer.parseInt(currentLine[1]) - 1;
					break;

				case "jie":
					value = registers.get( currentLine[1].charAt(0));
					if (value % 2 == 0) {
						position += Integer.parseInt(currentLine[2]) - 1;
					}
					break;

				case "jio":
					value = registers.get( currentLine[1].charAt(0));
					if (value == 1) {
						position += Integer.parseInt(currentLine[2]) - 1;
					}
					break;
			}
		}

		System.out.println( registers.get( 'b'));
	}
}
