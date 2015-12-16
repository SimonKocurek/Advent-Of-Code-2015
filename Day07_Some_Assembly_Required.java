package AdventOfCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Signal {
	public HashMap<String, Integer> variable = new HashMap<>();

	public boolean hasValue (String word) {
		try {
			int number = Integer.parseInt(word);
		} catch (NumberFormatException nfe) {
			if (!variable.containsKey(word)) {
				return false;
			}
		}
		return true;
	}

	public int getValue(String word) {
		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException nfe) {
			return variable.get(word);
		}
	}

	public void checkBorders( String word) {
		int value = variable.get(word);
		if (value > 65535) {
			variable.put(word, value - 65535 - 1);
		}
		if (value < 0) {
			variable.put(word, 65535 + value + 1);
		}
	}
}


public class Day07_Some_Assembly_Required {
	public static void signals() {
		Scanner input = new Scanner(System.in);
		Signal signal = new Signal();
		ArrayList<String[]> word = new ArrayList<>();
		String line;

		//result of part 1
		signal.variable.put("b", 46065);

		while (!(line = input.nextLine()).equals("exit")) {
			String[] words = line.split(" ");
			if (!(words[2].equals("b"))) {
				word.add( words);
			}
		}

		while (word.size() > 0) {
			for (int i = 0; i < word.size(); i++) {

				if (signal.hasValue( word.get(i)[0])) {
					int firstValue = signal.getValue( word.get(i)[0]);

					switch (word.get(i)[1]) {
						case "->":
							signal.variable.put( word.get(i)[2], firstValue);

							word.remove(i);
							i--;
							break;

						case "AND":
							if (signal.hasValue( word.get(i)[2])) {
								int secondValue = signal.getValue( word.get(i)[2]);
								signal.variable.put( word.get(i)[4], firstValue & secondValue);
								signal.checkBorders( word.get(i)[4]);

								word.remove(i);
								i--;
							} break;

						case "OR" :
							if (signal.hasValue( word.get(i)[2])) {
								int secondValue = signal.getValue( word.get(i)[2]);
								signal.variable.put( word.get(i)[4], firstValue | secondValue);
								signal.checkBorders( word.get(i)[4]);

								word.remove(i);
								i--;
							} break;

						case "LSHIFT" :
							if (signal.hasValue( word.get(i)[2])) {
								int secondValue = signal.getValue( word.get(i)[2]);
								signal.variable.put( word.get(i)[4], firstValue << secondValue);
								signal.checkBorders( word.get(i)[4]);

								word.remove(i);
								i--;
							} break;

						case "RSHIFT" :
							if (signal.hasValue( word.get(i)[2])) {
								int secondValue = signal.getValue( word.get(i)[2]);
								signal.variable.put( word.get(i)[4], firstValue >> secondValue);
								signal.checkBorders( word.get(i)[4]);

								word.remove(i);
								i--;
							} break;
					}
				} else if (word.get(i)[0].equals( "NOT")) {
					if (signal.hasValue( word.get(i)[1])) {
						int firstValue = signal.getValue( word.get(i)[1]);
						signal.variable.put( word.get(i)[3], ~ firstValue);
						signal.checkBorders( word.get(i)[3]);

						word.remove(i);
						i--;
					}
				}
			}
		}

		System.out.println( signal.variable.get("a"));
	}
}








