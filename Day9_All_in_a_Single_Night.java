package AdventOfCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class City {
	private String cityName;
	private HashMap<String, Integer> distance;

	public City( String name) {
		cityName = name;
	}

	public String getName() {
		return cityName;
	}
}

class Travel {
	public static boolean loaded( String currentCity, ArrayList<City> world) {
		for (City city : world) {
			if (city.getName().equals( currentCity)) {
				return true;
			}
		}
		return false;
	}

	public static void load() {
		Scanner input = new Scanner(System.in);
		ArrayList<City> world = new ArrayList<>();
		String line;

		while (!((line = input.next()).equals("exit"))) {
			String[] words = line.split(" ");

			if (!loaded( words[0], world)) {
				world.add( new City( words[0], ));
			}
		}
	}

	public static boolean allVisited() {
		return true;
	}
}

public class Day9_All_in_a_Single_Night {
	public static void shortestRoute() {
		int result;

		System.out.print(result);
	}
}
