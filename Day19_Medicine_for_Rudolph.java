package AdventOfCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Molecule {
	ArrayList<String> changeFrom = new ArrayList<>();
	ArrayList<String> changeTo = new ArrayList<>();
	String subject;

	public void load() {
		Scanner input = new Scanner(System.in);

		String line;
		while (!(line = input.nextLine()).equals("")) {
			String[] words = line.split(" ");
			this.changeFrom.add( words[0]);
			this.changeTo.add( words[2]);
		}
		this.subject = input.nextLine();
	}

	public HashMap<String, Boolean> replacements( String molecule) {
		HashMap<String, Boolean> created = new HashMap<>();
		for (int i = 0; i < molecule.length(); i ++) {
			for (int x = 0, changeLength = changeFrom.size(); x < changeLength; x++) {
				String transformation = changeFrom.get(x);
				int fromLength = transformation.length();

				for (int n = 0; n < fromLength; n++) {
					if (molecule.charAt(i + n) != transformation.charAt(n)) {
						break;
					} else if (n == fromLength - 1) {
						created.put(molecule.substring(0, i) + changeTo.get(x) + molecule.substring(i + fromLength, molecule.length()), true);
					}
				}
			}
		}

		return created;
	}

	public HashMap<String, Boolean> reconstructs( String molecule) {
		HashMap<String, Boolean> created = new HashMap<>();
		for (int i = 0; i < molecule.length(); i ++) {
			for (int x = 0, changeLength = changeTo.size(); x < changeLength; x++) {
				String transformation = changeTo.get(x);
				int toLength = transformation.length();

				for (int n = 0; n < toLength; n++) {
					try {
						if (molecule.charAt(i + n) != transformation.charAt(n)) {
							break;
						} else if (n == toLength - 1) {
							created.put(molecule.substring(0, i) + changeFrom.get(x) + molecule.substring(i + toLength, molecule.length()), true);
						}
					} catch (StringIndexOutOfBoundsException e) {
						break;
					}
				}
			}
		}

		return created;
	}
}

public class Day19_Medicine_for_Rudolph {
	public static void molecules() {
		Molecule molecule = new Molecule();
		molecule.load();

		int result = molecule.replacements( molecule.subject).size();

		/*
		HashMap<String, Boolean> nextCreated = new HashMap<>();
		for (String iteration : created.keySet()) {
			for (int i = 0; i < iteration.length(); i ++) {
				for (int x = 0, changeLength = molecule.changeFrom.size(); x < changeLength; x++) {
					String transformation = molecule.changeFrom.get(x);
					int fromLength = transformation.length();

					for (int n = 0; n < fromLength; n++) {
						if (iteration.charAt(i + n) != transformation.charAt(n)) {
							break;
						} else if (n == fromLength - 1) {
							nextCreated.put(iteration.substring(0, i) + molecule.changeTo.get(x) + iteration.substring(i + fromLength, iteration.length()), true);
						}
					}
				}
			}
		}
		*/

		System.out.println( result);
	}

	public static void reconstruct() {
		Molecule molecule = new Molecule();
		molecule.load();
		int[] min = {Integer.MAX_VALUE};

		recursive( molecule, 0, molecule.subject, min);

		System.out.println(min[0]);
	}

	private static void recursive(Molecule molecule, int steps, String _molecule, int[] min) {
		steps ++;
		for (String reconstructed : molecule.reconstructs(_molecule).keySet()) {
			if (reconstructed.equals("e")) {
				if (steps < min[0]) {
					min[0] = steps;
				}
			} else {
				recursive(molecule, steps, reconstructed, min);
			}
		}
	}
}
