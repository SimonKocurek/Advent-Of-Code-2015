package AdventOfCode;

import java.util.ArrayList;
import java.util.Scanner;

import static org.mentaregex.Regex.match;

public class Day12_JSAbacusFramework_io {
	private static String ignore(String megaString, String word){
		StringBuilder newMegastring = new StringBuilder();
		ArrayList<Integer> startBracket = new ArrayList<>();
		int end = megaString.length() - word.length() + 1;
		boolean foundWord = false;

		for (int i = 1; i < end; ++i){
			switch (megaString.charAt(i)) {
				case '{':
					startBracket.add(i);
					break;
				case '}':
					foundWord = false;
					break;
				default:
					if (megaString.substring(i, i + megaString.length()).equals( word)) {
						foundWord = true;
						int lastStart = startBracket.get( startBracket.size() - 1);
						newMegastring.delete( lastStart, newMegastring.length());
					}

					if (!foundWord) {
						newMegastring.append( megaString.charAt(i));
					}
					break;
			}
		}

		return newMegastring.toString();
	}

	public static void json() {
		Scanner input = new Scanner(System.in);
		String json = input.nextLine();
		int sum = 0;

		json = ignore(json, "red");
		String[] numbers = match(json, "/(-?\\d+)/g");

		for (String number : numbers) {
			sum += Integer.parseInt( number);
		}

		System.out.print(sum);
	}
}
