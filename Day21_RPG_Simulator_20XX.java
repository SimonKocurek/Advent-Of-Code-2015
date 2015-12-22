package AdventOfCode;

import java.util.ArrayList;
import java.util.Scanner;

class GameCharacter {
	int health = 0;
	int damage = 0;
	int armor = 0;
}

class GameItem {
	String type;
	String name;
	int cost;
	int damage;
	int armor;

	boolean bought = false;

	public GameItem(String _type, String _name, int _cost, int _damage, int _armor) {
		type = _type;
		name = _name;
		cost = _cost;
		damage = _damage;
		armor = _armor;
	}
}

public class Day21_RPG_Simulator_20XX {
	private static boolean wonBattle(GameCharacter player, GameCharacter boss) {
		boolean playerTurn = true;
		int playerHealth = player.health;
		int bossHealth = boss.health;

		while ( true) {
			if (playerTurn) {
				int attack = player.damage - boss.armor;
				playerTurn = false;

				if (attack > 0)
					boss.health -= attack;
				else
					boss.health -= 1;
			} else {
				int attack = boss.damage - player.armor;
				playerTurn = true;

				if (attack > 0)
					player.health -= attack;
				else
					player.health -= 1;
			}

			if (player.health <= 0) {
				player.health = playerHealth;
				boss.health = bossHealth;
				return false;
			}
			if (boss.health <= 0) {
				player.health = playerHealth;
				boss.health = bossHealth;
				return true;
			}
		}
	}

	private static int buy( ArrayList<GameItem> items, GameCharacter player, GameCharacter boss) {
		int highestCost = Integer.MIN_VALUE;
		int goldSpent = 0;

		for (GameItem weapon : items) {
			// 1 Weapon
			if (weapon.type.equals("Weapon")) {
				player.damage += weapon.damage;
				goldSpent += weapon.cost;

				// 1 Armor
				for (GameItem Armor : items) {
					if (Armor.type.equals("Armor")) {
						player.armor += Armor.armor;
						goldSpent += Armor.cost;

						// 2 rings
						for (GameItem Ring : items) {
							if (Ring.type.equals("Ring")) {
								Ring.bought = true;
								player.damage += Ring.damage;
								player.armor += Ring.armor;
								goldSpent += Ring.cost;

								for (GameItem Ring2 : items) {
									if (Ring2.type.equals("Ring") && !Ring2.bought) {
										player.damage += Ring2.damage;
										player.armor += Ring2.armor;
										goldSpent += Ring2.cost;

										if (!wonBattle(player, boss)) {
											highestCost = Integer.max( highestCost, goldSpent);
										}

										player.damage -= Ring2.damage;
										player.armor -= Ring2.armor;
										goldSpent -= Ring2.cost;
									}
								}

								if (!wonBattle(player, boss)) {
									highestCost = Integer.max( highestCost, goldSpent);
								}

								Ring.bought = false;
								player.damage -= Ring.damage;
								player.armor -= Ring.armor;
								goldSpent -= Ring.cost;
							}
						}

						// No ring
						if (!wonBattle(player, boss)) {
							highestCost = Integer.max( highestCost, goldSpent);
						}

						player.armor -= Armor.armor;
						goldSpent -= Armor.cost;
					}
				}

				//// No Armor
				// 2 rings
				for (GameItem Ring : items) {
					if (Ring.type.equals("Ring")) {
						Ring.bought = true;
						player.damage += Ring.damage;
						player.armor += Ring.armor;
						goldSpent += Ring.cost;

						for (GameItem Ring2 : items) {
							if (Ring2.type.equals("Ring") && !Ring2.bought) {
								player.damage += Ring2.damage;
								player.armor += Ring2.armor;
								goldSpent += Ring2.cost;

								if (!wonBattle(player, boss)) {
									highestCost = Integer.max( highestCost, goldSpent);
								}

								player.damage -= Ring2.damage;
								player.armor -= Ring2.armor;
								goldSpent -= Ring2.cost;
							}
						}

						if (!wonBattle(player, boss)) {
							highestCost = Integer.max( highestCost, goldSpent);
						}

						Ring.bought = false;
						player.damage -= Ring.damage;
						player.armor -= Ring.armor;
						goldSpent -= Ring.cost;
					}
				}

				// No ring
				if (!wonBattle(player, boss)) {
					highestCost = Integer.max( highestCost, goldSpent);
				}

				player.damage -= weapon.damage;
				goldSpent -= weapon.cost;
			}
		}

		return highestCost;
	}

	public static void leastSpent() {
		ArrayList<GameItem> items = new ArrayList<>();
		items.add(new GameItem("Weapon", "Dagger", 8, 4, 0));
		items.add(new GameItem("Weapon", "Shortsword", 10, 5, 0));
		items.add(new GameItem("Weapon", "Warhammer", 25, 6, 0));
		items.add(new GameItem("Weapon", "Longsword", 40, 7, 0));
		items.add(new GameItem("Weapon", "Greataxe", 74, 8, 0));

		items.add(new GameItem("Armor", "Leather", 13, 0, 1));
		items.add(new GameItem("Armor", "Chainmail", 31, 0, 2));
		items.add(new GameItem("Armor", "Splintmail", 53, 0, 3));
		items.add(new GameItem("Armor", "Bandedmail", 75, 0, 4));
		items.add(new GameItem("Armor", "Platemail", 102, 0, 5));

		items.add(new GameItem("Ring", "Damage +1", 25, 1, 0));
		items.add(new GameItem("Ring", "Damage +2", 50, 2, 0));
		items.add(new GameItem("Ring", "Damage +3", 100, 3, 0));
		items.add(new GameItem("Ring", "Defense +1", 20, 0, 1));
		items.add(new GameItem("Ring", "Defense +2", 40, 0, 2));
		items.add(new GameItem("Ring", "Defense +3", 80, 0, 3));
		///////////////////SHOP//////////////////////

		int lowestCost = Integer.MAX_VALUE;

		Scanner input = new Scanner(System.in);
		GameCharacter boss = new GameCharacter();
		boss.health = Integer.parseInt( input.nextLine().split(" ")[2]);
		boss.damage = Integer.parseInt( input.nextLine().split(" ")[1]);
		boss.armor = Integer.parseInt( input.nextLine().split(" ")[1]);

		GameCharacter player = new GameCharacter();
		player.health = 100;

		int spent = buy( items, player, boss);

		System.out.println( spent);
	}
}
