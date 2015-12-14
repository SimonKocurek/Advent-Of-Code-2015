package AdventOfCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Person {
	String name;
	HashMap<String, Integer> likes = new HashMap<>();

	public Person(String _name) {
		this.name = _name;
	}
}

class SittingPlan {
	private ArrayList<Person> people = new ArrayList<>();
	private HashMap<String, Boolean> sitting = new HashMap<>();

	private ArrayList<String> lastPerson = new ArrayList<>();
	private boolean first = true;
	private int happiness = 0;
	public int min = Integer.MIN_VALUE;

	public void load() {
		Scanner input = new Scanner(System.in);
		String line;

		while (!(line = input.nextLine()).equals("exit")) {
			String[] words = line.split(" ");

			String neighbour = words[10].substring(0, words[10].length() - 1);

			int happiness = Integer.parseInt( words[3]);
			if (words[2].equals("lose")) {
				happiness = -happiness;
			}

			if (first) {
				people.add( new Person(words[0]));
				sitting.put( words[0], false);
				first = false;
			}

			Person lastPerson = people.get( people.size() - 1);

			if (!lastPerson.name.equals( words[0])) {
				people.add( new Person(words[0]));
				sitting.put( words[0], false);
			}
			people.get( people.size() - 1).likes.put(neighbour, happiness);
		}

		first = true;
	}

	public void find() {
		for (Map.Entry<String, Boolean> person : sitting.entrySet()) {
			// if not sitting
			if (!person.getValue()) {
				if (first) {
					lastPerson.add( person.getKey());
					sitting.put( person.getKey(), true);
					first = false;
				} else {
					sitting.put( person.getKey(), true);
					happiness += likes( person.getKey(), lastPerson.get( lastPerson.size() - 1));
					lastPerson.add( person.getKey());

					find();

					lastPerson.remove( lastPerson.size() - 1);
					happiness -= likes( person.getKey(), lastPerson.get( lastPerson.size() - 1));
					sitting.put( person.getKey(), false);
				}
			} else if (allSitting()) {
				happiness += likes( lastPerson.get(0), lastPerson.get( people.size() - 1));

				if (min < happiness) {
					min = happiness;
				}

				happiness -= likes( lastPerson.get(0), lastPerson.get( people.size() - 1));
			}
		}
	}

	private int likes( String person, String neighbour) {
		int firstLikes = 0;
		int secondLikes = 0;
		for (Person possible : people) {
			if (possible.name.equals( neighbour)) {
				if (possible.likes.containsKey( person)) {
					firstLikes = possible.likes.get( person);
				}
			} else if (possible.name.equals( person)) {
				if (possible.likes.containsKey( neighbour))
				secondLikes = possible.likes.get( neighbour);
			}
		}
		return firstLikes + secondLikes;
	}

	private boolean allSitting() {
		for (Boolean sat : sitting.values()) {
			if (!sat) {
				return false;
			}
		}
		return true;
	}
}

public class Day13_Knights_of_the_Dinner_Table {
	public static void bestOption() {
		SittingPlan sittingPlan = new SittingPlan();

		sittingPlan.load();
		sittingPlan.find();

		System.out.println(sittingPlan.min);
	}
}
