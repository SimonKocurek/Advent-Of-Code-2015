package AdventOfCode;

import java.util.ArrayList;
import java.util.Scanner;

class Ingredient {
	int capacity;
	int durability;
	int flavour;
	int texture;
	int calories;

	int count = 0;

	public Ingredient(int _capacity, int _duraility, int _flavour, int _texture, int _calories) {
		this.capacity = _capacity;
		this.durability = _duraility;
		this.flavour = _flavour;
		this.texture = _texture;
		this.calories = _calories;
	}
}

public class Day15_Science_for_Hungry_People {
	private ArrayList<Ingredient> ingredients = new ArrayList<>();
	private Ingredient firstIngredient;
	private int remainingIngredients = 0;
	private int maxScore = 0;

	private int score() {
		int capacity = 0;
		int durability = 0;
		int flavour = 0;
		int texture = 0;

		for (Ingredient ingredient : ingredients) {
			capacity += ingredient.count * ingredient.capacity;
			durability += ingredient.count * ingredient.durability;
			flavour += ingredient.count * ingredient.flavour;
			texture += ingredient.count * ingredient.texture;
		}

		if (capacity <= 0 || durability <= 0 || flavour <= 0 || texture <= 0) {
			return 0;
		} else {
			return capacity * durability * flavour * texture;
		}
	}

	private boolean hasCalories() {
		int calories = 0;

		for (Ingredient ingredient : ingredients)
			calories+= ingredient.count * ingredient.calories;

		return calories == 500;
	}

	private void recursion(int i) {
		if (remainingIngredients == 0) {
			if (hasCalories()) {
				if (score() > maxScore) {
					maxScore = score();
				}
			}

		} else {
			for (int ingredientsSize = ingredients.size(); i < ingredientsSize; i++) {
				Ingredient ingredient = ingredients.get(i);

				ingredient.count++;
				remainingIngredients--;

				recursion(i);

				remainingIngredients++;
				ingredient.count--;
			}
		}
	}

	private void bestOption() {
		recursion(1);

		while (firstIngredient.count != 0) {
			recursion(1);

			firstIngredient.count --;
			remainingIngredients ++;
		}
	}

	public void bake() {
		Scanner input = new Scanner(System.in);
		String line;

		while (!(line = input.nextLine()).equals("exit")) {
			//Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
			String[] words = line.split(",");

			int capacity = Integer.parseInt( words[0].split(" ")[2]);
			int durability= Integer.parseInt( words[1].split(" ")[2]);
			int flavour= Integer.parseInt( words[2].split(" ")[2]);
			int texture= Integer.parseInt( words[3].split(" ")[2]);
			int calories= Integer.parseInt( words[4].split(" ")[2]);

			ingredients.add(new Ingredient(capacity, durability, flavour, texture, calories));
		}

		firstIngredient = ingredients.get(0);
		firstIngredient.count = 100;
		bestOption();

		System.out.println(maxScore);
	}
}
