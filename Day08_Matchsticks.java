package AdventOfCode;

import java.util.Scanner;

public class Day08_Matchsticks {
	public static void sizeDifference() {
		Scanner input = new Scanner(System.in);
		String line;

		char[] specialChars = {'\"', '\\'};
		int difference = 0;

		while (!(line = input.next()).equals("exit")) {
			int wordLength = line.length();
			int resultLength = 0;

			for (int i = 1; i < line.length() - 1; i++) {
				resultLength++;

				if (i+3 < line.length() - 1) {
					if (line.substring(i, i+4).matches("\\\\x(\\d|[a-f]){2}") ) {
						i += 3;
						continue;
					}
				}

				if (line.charAt(i) == '\\' && new String( specialChars).contains( String.valueOf(line.charAt(i+1)))) {
					i++;
				}
			}

			difference += wordLength - resultLength;
		}

		System.out.println(difference);
	}

	public static void sizeDifference2() {
		Scanner input = new Scanner(System.in);
		String line;

		int difference = 0;

		while (!(line = input.next()).equals("exit")) {
			int wordLength = line.length();
			int resultLength = 6;

			for (int i = 1; i < line.length() - 1; i++) {
				resultLength++;

				if (line.charAt(i) == '\\' || line.charAt(i) == '\"') {
					resultLength++;
				}
			}

			difference +=  resultLength - wordLength;
		}

		System.out.println(difference);
	}
}
