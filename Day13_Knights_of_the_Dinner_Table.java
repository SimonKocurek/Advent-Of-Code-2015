package AdventOfCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Person {
	String name;
	HashMap<String, Integer> likes = new HashMap<>();

	public Person(String _name) {
		this.name = _name;
	}

}

public class Day13_Knights_of_the_Dinner_Table {
	public static void bestOption() {
		Scanner input = new Scanner(System.in);
		String line;
		ArrayList<Person> people = new ArrayList<>();

		while (!(line = input.nextLine()).equals("exit")) {
			String[] words = line.split(" ");

			people.add( new Person(words[0]));

		}
	}
}
