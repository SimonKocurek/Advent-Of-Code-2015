package AdventOfCode;

import java.util.*;

class Word {
	protected static String line;

	public static void setLine(String newLine) {
		line = newLine;
	}

	public static boolean hasVowels() {
		final char[] vowels = {'a','e','i','o','u'};
		int vowelCount = 0;

		for (char vowel : line.toCharArray()) {
			for (char comparedTo : vowels) {
				if (vowel == comparedTo) {
					vowelCount++;
					break;
				}
			}

			if (vowelCount == 3) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasDouble() {
		for (int i = 0; i < line.length() - 1; i++) {
			if (line.charAt(i) == line.charAt(i + 1)) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasCombo() {
		final HashMap<Character, Character> combos = new HashMap<Character, Character>(){{
			put('a','b');
			put('c','d');
			put('p','q');
			put('x','y');
		}};

		for (int i = 0; i < (line.length() - 1); i++) {
			for (Map.Entry<Character, Character> set : combos.entrySet()) {
				if (line.charAt(i) == set.getKey() && line.charAt(i + 1) == set.getValue()) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean hasTwoPairs() {
		for (int i = 0; i < line.length() - 3; i++) {
			if (line.substring(i+2).contains( line.substring(i, i+2))) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasTriplet() {
		for (int i = 0; i < line.length() - 2; i++) {
			if (line.charAt(i) == line.charAt(i + 2)) {
				return true;
			}
		}

		return false;
	}
}

public class Day05_Doesn_t_He_Have_Intern_Elves_For_This {
	public static void niceString() {
		Scanner input = new Scanner(System.in);
		String line;
		Word word = new Word();
		int niceCount = 0;

		while (!(line = input.next()).equals("exit")) {
			Word.setLine(line);

			if (!word.hasCombo()) {
				if (word.hasDouble() && word.hasVowels()) {
					niceCount++;
				}
			}
		}

		input.close();
		System.out.println(niceCount);
	}

	public static void nicerString() {
		Scanner input = new Scanner(System.in);
		String line;
		Word word = new Word();
		int niceCount = 0;

		while (!(line = input.next()).equals("exit")) {
			Word.setLine(line);

			if (word.hasTwoPairs() && word.hasTriplet()) {
				niceCount++;
			}
		}

		input.close();
		System.out.println(niceCount);
	}
}
