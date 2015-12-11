package AdventOfCode;

import java.util.Scanner;

public class Day10_Elves_Look_Elves_Say {
	public static void lookAndSay() {
		Scanner input = new Scanner(System.in);
		String chain = input.next();

		StringBuilder newChain = new StringBuilder();
		for (int i = 0; i < 50; i++) {
			newChain.setLength(0);
			char charNumber = chain.charAt(0);
			int repeated = 0;

			char[] charArray = chain.toCharArray();
			for (int x = 0, stringLength = charArray.length; x <= stringLength; x++) {
				try {
					char letter = charArray[x];

					if (letter != charNumber) {
						newChain.append(String.valueOf(repeated));
						newChain.append( charNumber);

						charNumber = letter;
						repeated = 0;
					}

				} catch (ArrayIndexOutOfBoundsException e) {
					newChain.append(String.valueOf(repeated));
					newChain.append( charNumber);
				}

				repeated++;
			}

			chain = newChain.toString();
		}

		System.out.println(chain.length());
	}
}
