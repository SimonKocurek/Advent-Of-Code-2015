package AdventOfCode;

import java.util.ArrayList;
import java.util.Scanner;

class Reindeer {
	String name;
	private int speed;
	private int endurance;
	private int rest;

	int travelled = 0;
	int points = 0;
	private int endured = 0;
	private boolean resting = false;
	private int rested = 0;

	public Reindeer(String _name, int _speed, int _endurance, int _rest) {
		this.name = _name;
		this.speed = _speed;
		this.endurance = _endurance;
		this.rest = _rest;
	}

	public void move(){
		if (resting) {
			if (rested + 1 == rest) {
				resting = false;
				rested = 0;
			} else {
				rested ++;
			}

		} else {
			travelled += speed;
			if (endured + 1 == endurance) {
				resting = true;
				endured = 0;
			} else {
				endured ++;
			}
		}
	}
}

public class Day14_Reindeer_Olympics {
	public static void race() {
		Scanner input = new Scanner(System.in);
		String line;
		ArrayList<Reindeer> reindeers = new ArrayList<>();

		while (!(line = input.nextLine()).equals("exit")) {
			String[] words = line.split(" ");

			int speed = Integer.parseInt( words[3]);
			int endurance = Integer.parseInt( words[6]);
			int rest = Integer.parseInt( words[13]);

			reindeers.add( new Reindeer( words[0], speed, endurance, rest));
		}


		int winnerDistance = 0;
		for (int i = 0; i < 2503; i ++) {
			for (Reindeer reindeer : reindeers) {
				reindeer.move();
			}

			for (Reindeer reindeer : reindeers) {
				if (reindeer.travelled > winnerDistance) {
					winnerDistance = reindeer.travelled;
				}
			}

			for (Reindeer reindeer : reindeers) {
				if (reindeer.travelled == winnerDistance) {
					reindeer.points ++;
				}
			}
		}

		int winnerPoints = 0;
		for (Reindeer reindeer : reindeers) {
			if (reindeer.points > winnerPoints) {
				winnerPoints = reindeer.points;
			}
		}

		System.out.println(winnerDistance);
		System.out.print(winnerPoints);
	}
}
