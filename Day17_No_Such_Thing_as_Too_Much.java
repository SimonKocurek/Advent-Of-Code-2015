package AdventOfCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Container {
	int size;
	boolean used = false;

	public Container(int _size) {
		this.size = _size;
	}
}

public class Day17_No_Such_Thing_as_Too_Much {
	private ArrayList<Container> containers = new ArrayList<>();
	private int litres = 150;

	private HashMap<Container, Boolean> chain = new HashMap<>();
	private ArrayList<HashMap<Container, Boolean>> results = new ArrayList<>();

	private void fill() {
		Scanner input = new Scanner(System.in);

		while (input.hasNextInt()) {
			containers.add( new Container(input.nextInt()));
		}
	}

	private void find(int i) {
		for (int containersLength = containers.size(); i < containersLength; i++) {
			Container container = containers.get(i);

			if (!container.used) {
				litres -= container.size;
				container.used = true;
				chain.put(container, true);

				if (litres == 0) {
					if (! results.contains(chain)) {
						HashMap<Container, Boolean> copy = new HashMap<>(chain);
						results.add(copy);
					}
				} else if (litres > 0) {
					find( i);
				}

				container.used = false;
				chain.remove(container);
				litres += container.size;
			}
		}
	}

	private int minimum() {
		int min = results.get(0).size();
		for (HashMap<Container, Boolean> result : results) {
			if (result.size() < min) {
				min = result.size();
			}
		}

		int usedMin = 0;
		for (HashMap<Container, Boolean> result : results) {
			if (result.size() == min) {
				usedMin ++;
			}
		}

		return usedMin;
	}

	public void result() {
		fill();
		find(0);
		int possibilities = minimum();

		System.out.println(possibilities);
	}
}
