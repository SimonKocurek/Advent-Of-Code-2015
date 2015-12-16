package AdventOfCode;

import java.util.Scanner;

/**
 * Created by Kocurek on 01-Dec-15.
 */

public class Day01_Not_Quite_Lisp {
	public static void run() {
		Scanner input = new Scanner(System.in);
		String parenthesis = input.next();
		int res = 0;

		for (int i = 0; i < parenthesis.length(); i++) {
			if (parenthesis.charAt(i) == '(') {
				res++;
			} else {
				res--;
			}
		}

		System.out.println(res);

		input.close();
	}

	public static int next() {
		Scanner input = new Scanner(System.in);
		String parenthesis = input.next();
		int res = 0;

		for (int i = 0; i < parenthesis.length(); i++) {
			if (parenthesis.charAt(i) == '(') {
				res++;
			} else {
				res--;
			}

			if (res == -1) {
				return i + 1;
			}
		}

		input.close();
		return 0;
	}

	public static int len() {
		Scanner input = new Scanner(System.in);
		String parenthesis = input.next();

		return parenthesis.length();
	}
}
