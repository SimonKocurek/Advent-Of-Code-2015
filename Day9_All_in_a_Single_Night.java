package AdventOfCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Tree {
	private Travel travel;
	ArrayList<Integer> travelled = new ArrayList<>();
	int shortest = Integer.MIN_VALUE;

	public void setTravel(Travel travelInfo) {
		travel = travelInfo;
	}

	private boolean allVisited() {
		return !travel.visited.containsValue(false);
	}

	public void visit(String city1, String city2) {
		travel.visited.put( city2, true);

		for (int i = 0; i < travel.city1.size(); i++) {
			if (travel.city1.get(i).equals( city1) && travel.city2.get(i).equals( city2)) {
				travelled.add( travel.distance.get(i));
				break;
			}

			if (travel.city2.get(i).equals( city1) && travel.city1.get(i).equals( city2)) {
				travelled.add( travel.distance.get(i));
				break;
			}
		}
	}

	public void unVisit(String city) {
		travel.visited.put( city, false);
		travelled.remove( travelled.size() - 1);
	}

	public void search(String root) {
		ArrayList<String> reachable = new ArrayList<>( travel.reachable( root));
		int branches = reachable.size();

		if (branches == 1) {
			travel.visited.put( reachable.get(0), true);
			if (allVisited()) {
				visit(root, reachable.get(0));
				shortest = Math.max(shortest, Travel.sum(travelled));
				unVisit( reachable.get(0));
			} else {
				travel.visited.put( reachable.get(0), false);
			}
		} else {
			for (String reachableCity : reachable) {
				visit(root, reachableCity);
				search(reachableCity);
				unVisit(reachableCity);
			}
		}
	}
}

class Travel {
	ArrayList<Integer> distance = new ArrayList<>();
	ArrayList<String> city1 = new ArrayList<>();
	ArrayList<String> city2 = new ArrayList<>();
	HashMap<String, Boolean> visited = new HashMap<>();

	public void iniciateVisited() {
		for (String cityName : visited.keySet()) {
			visited.put( cityName, false);
		}
	}

	public static int sum(ArrayList<Integer> list) {
		int result = 0;

		for (int value : list) {
			result += value;
		}

		return result;
	}

	public void load() {
		Scanner input = new Scanner(System.in);
		String line;

		while (!(line = input.nextLine()).equals( "exit")) {
			String[] words = line.split(" ");

			city1.add(words[0]);
			city2.add(words[2]);
			distance.add( Integer.parseInt( words[4]));
			visited.put(words[0], false);
			visited.put(words[2], false);
		}
	}

	public ArrayList<String> reachable(String currentCity) {
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < city1.size(); i++) {
			String pair1 = city1.get(i);
			String pair2 = city2.get(i);

			if (visited.get( pair1) && visited.get( pair2)) {
				continue;
			}

			if (pair1.equals( currentCity)) {
				list.add( pair2);
			} else if (pair2.equals( currentCity)) {
				list.add( pair1);
			}
		}

		return list;
	}
}

public class Day9_All_in_a_Single_Night {
	public static void shortestRoute() {
		Travel travel = new Travel();
		travel.load();

		Tree tree = new Tree();
		tree.setTravel( travel);

		for (String cityName : travel.visited.keySet()) {
			travel.visited.put( cityName, true);
			tree.search( cityName);
			travel.iniciateVisited();
			tree.travelled.clear();
		}

		System.out.println( tree.shortest);
	}
}
