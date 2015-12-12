package AdventOfCode;

import java.util.Scanner;

public class Day11_Corporate_Policy {
	private static void increment( char[] password) {
		int last = password.length - 1;

		while (true) {
			if (password[last] == 'z') {
				password[last] = 'a';
				if (last > 0) {
					last -= 1;
				}
			} else {
				password[ last] += 1;
				break;
			}
		}
	}

	private static boolean incOrder( char[] password) {
		for (int i = 0; i < password.length - 2; i++) {
			if (password[i] == password[i+1] - 1 && password[i] == password[i+2] - 2) {
				return true;
			}
		}
		return false;
	}

	private static boolean forbiddenLetters(char[] password) {
		char[] forbidden = {'i', 'o', 'l'};

		for (char letter : password) {
			for (char forbiddenLetter : forbidden) {
				if (letter == forbiddenLetter) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean twoPairs( char[] password) {

		for (int i = 0; i < password.length - 3; i++) {
			if (password[i] == password[i+1]) {
				for (int n = i+2; n < password.length - 1; n++) {
					if (password[n] == password[n+1]) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static void newPassword() {
		Scanner input = new Scanner(System.in);
		char[] password = input.next().toCharArray();

		while (!incOrder( password) || forbiddenLetters(password) || !twoPairs( password)) {
			increment(password);
		}

		System.out.println( new String( password));
	}
}
