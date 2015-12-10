package AdventOfCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class City {
	private String cityName;
	public int ID;
	public HashMap<String, Integer> distance = new HashMap<>();
	boolean visited = false;

	public City( String name) {
		cityName = name;
	}

	public String getName() {
		return cityName;
	}

	public void setDistance(String distanceTo, Integer setDistanceValue) {
		distance.put( distanceTo, setDistanceValue);
	}

	public int getDistance(String distanceTo) {
		return distance.get(distanceTo);
	}
}

class Travel {
	public ArrayList<City> world = new ArrayList<>();

	public void load() {
		Scanner input = new Scanner(System.in);
		ArrayList<String[]> line = new ArrayList<>();
		String inputLine;

		//loading input
		while (!((inputLine = input.nextLine()).equals("exit"))) {
			line.add( inputLine.split(" "));
		}

		//loading cities
		int idCity = 0;
		world.add( new City( line.get(0)[0]));

		for (String[] loadedLine : line) {
			if (!world.get(idCity).getName().equals(loadedLine[0])) {
				world.add(new City(loadedLine[0]));
				idCity++;
			}
		}

		//loading distances
		idCity = 0;
		for (String[] loadedLine : line) {
			if ()
			world.get( getCityID( loadedLine[0])).setDistance( loadedLine[2], Integer.parseInt( loadedLine[4]));
			world.get( getCityID( loadedLine[2])).setDistance( loadedLine[0], Integer.parseInt( loadedLine[4]));
		}
	}


	public void clearVisited() {
		for (City city : world) {
			city.visited = false;
		}
	}

	public boolean allVisited() {
		for (City city : world) {
			if (!city.visited) {
				return false;
			}
		}
		return true;
	}

	public int getCityID(String cityName) {
		int id = 0;

		for (City city : world) {
			if (city.getName().equals( cityName)) {
				return id;
			}
			id++;
		}
		return Integer.MAX_VALUE;
	}

	public ArrayList<City> getDestinations (City currentCity) {
		ArrayList<City> destinations = new ArrayList<>();

		for (String cityName : currentCity.distance.keySet()) {
			for (City cityObject : world) {
				if (cityObject.getName().equals( cityName)) {
					destinations.add( cityObject);
				}
			}
		}

		return destinations;
	}
}

public class Day9_All_in_a_Single_Night {
	private Travel travel = new Travel();
	private ArrayList<Integer> travelled = new ArrayList<>();
	private int result = Integer.MAX_VALUE;

	private void search(City currentCity) {
		ArrayList<City> destinations = travel.getDestinations(currentCity);
		for (int i = 0, destinationsSize = destinations.size(); i < destinationsSize; i++) {
			City cityForTravel = destinations.get(i);

			if (!cityForTravel.visited) {
				cityForTravel.visited = true;

				int lastTravelled = travelled.get(travelled.size() - 1);
				int gonnaTravell = currentCity.getDistance(cityForTravel.getName());
				travelled.add(lastTravelled + gonnaTravell);

				if (travel.allVisited()) {
					// endpoint 1 - finish
					travel.clearVisited();
					result = Math.min(result, travelled.get(travelled.size() - 1));

					travelled.remove(travelled.size() - 1);
				} else {
					search(cityForTravel);
				}
			}

			// endpoint 2 - dead end
			if ( i+1 == destinationsSize) {
				travelled.remove(travelled.size() - 1);
			}
		}
	}

	public void shortestRoute() {
		travel.load();
		for (City currentCity : travel.world) {
			travelled.add(0);
			search(currentCity);
		}

		System.out.print(result);
	}
}
