package AdventOfCode;

import java.util.Scanner;

class Sue {
	int children = -1;
	int cats = -1;
	int samoyeds = -1;
	int pomeranians = -1;
	int akitas = -1;
	int vizslas = -1;
	int goldfish = -1;
	int trees = -1;
	int cars = -1;
	int perfumes = -1;

	int number;
	public Sue(int _number) {
		this.number = _number;
	}
}

public class Day16_Aunt_Sue {
	public static void assign( String word, String number, Sue sue) {
		if (number.contains(",")) {
			number = number.substring(0, number.length() - 1);
		}
		int count = Integer.parseInt(number);
		switch (word) {
			case "children:": sue.children = count; break;
			case "cats:": sue.cats = count;  break;
			case "samoyeds:": sue.samoyeds = count;  break;
			case "pomeranians:": sue.pomeranians = count;  break;
			case "akitas:": sue.akitas = count;  break;
			case "vizslas:": sue.vizslas = count;  break;
			case "goldfish:": sue.goldfish = count;  break;
			case "trees:": sue.trees = count;  break;
			case "cars:": sue.cars = count;  break;
			case "perfumes:": sue.perfumes = count;  break;
		}
	}

	public static void find() {
		Sue wanted = new Sue(0);

		wanted.children = 3;
		wanted.cats = 7;
		wanted.samoyeds = 2;
		wanted.pomeranians = 3;
		wanted.akitas = 0;
		wanted.vizslas = 0;
		wanted.goldfish = 5;
		wanted.trees = 3;
		wanted.cars = 2;
		wanted.perfumes = 1;

		Sue[] sues = new Sue[500];
		Scanner input = new Scanner(System.in);

		for (int i = 0; i < 500; i ++) {
			String[] words = input.nextLine().split(" ");

			sues[i] = new Sue(i+1);
			for (int n = 2; n < words.length - 1; n += 2) {
				assign(words[n], words[n + 1], sues[i]);
			}
		}

		for (Sue sue : sues) {
			if (sue.children == -1 || sue.children == wanted.children)
			if (sue.cats == -1 || sue.cats > wanted.cats)
			if (sue.samoyeds == -1 || sue.samoyeds == wanted.samoyeds)
			if (sue.pomeranians == -1 || sue.pomeranians < wanted.pomeranians)
			if (sue.akitas == -1 || sue.akitas == wanted.akitas)
			if (sue.vizslas == -1 || sue.vizslas == wanted.vizslas)
			if (sue.goldfish == -1 || sue.goldfish < wanted.goldfish)
			if (sue.trees == -1 || sue.trees > wanted.trees)
			if (sue.cars == -1 || sue.cars == wanted.cars)
			if (sue.perfumes == -1 || sue.perfumes == wanted.perfumes)
				System.out.println(sue.number);
		}
	}
}
