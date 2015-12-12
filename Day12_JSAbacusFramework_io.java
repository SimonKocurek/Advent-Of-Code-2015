package AdventOfCode;

import java.util.Scanner;

import static org.mentaregex.Regex.match;

public class Day12_JSAbacusFramework_io {
	private static void ignore(String megaString, String word, int pos){
		int end = megaString.length() - word.length() + 1;

		for (int i = pos; pos < end; ++i){
			if (megaString.charAt(i) == '{') {
				ignore( megaString, word, i);
			}

			if (megaString.charAt(i) == '}') {

			}
		}
	}

	public static void json() {
		Scanner input = new Scanner(System.in);
		String json = input.nextLine();
		int sum = 0;

		ignore(json, "red", 0);

		String[] numbers = match(json, "/(-?\\d+)/g");

		for (String number : numbers) {
			sum += Integer.parseInt( number);
		}

		System.out.print(sum);
	}
}
