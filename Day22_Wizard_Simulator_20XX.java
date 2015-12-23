package AdventOfCode;

import java.util.Scanner;

class SpellBook {
	int spellCount = 5;

	public void magicMissile(int[] mana, int[] bossHealth, boolean cast) {
		if (cast) {
			mana[0] -= 53;
			bossHealth[0] -= 4;
		}
	}

	public void drain(int[] mana, int[] bossHealth, int[] playerHealth, boolean cast) {
		if (cast) {
			mana[0] -= 73;
			bossHealth[0] -= 2;
			playerHealth[0] += 2;
		}
	}

	private int activeShield = 0;
	private boolean addedArmor = false;
	public void shield(int[] mana, GameHero player, boolean cast) {
		if (activeShield > 0) {
			if (!addedArmor) {
				player.armor += 7;
				addedArmor = true;
			}
			activeShield --;
		} else {
			player.armor -= 7;
			addedArmor = false;
		}

		if (cast) {
			mana[0] -= 113;
			activeShield = 6;
		}
	}

	private int activePoison = 0;
	public void poison(int[] mana, GameHero boss, boolean cast) {
		if (activePoison > 0) {
			boss.health -= 3;
			activePoison --;
		}

		if (cast) {
			mana[0] -= 173;
			activePoison = 6;
		}
	}

	private int activeRecharge = 0;
	public void recharge(int[] mana, boolean cast) {
		if (activeRecharge > 0) {
			mana[0] += 101;
		}

		if (cast) {
			mana[0] -= 229;
			activeRecharge = 5;
		}
	}
}

class GameHero {
	int health = 50;
	int armor = 0;
	int damage = 0;
}

public class Day22_Wizard_Simulator_20XX {
	private GameHero player = new GameHero();
	private GameHero boss = new GameHero();
	private SpellBook spellBook = new SpellBook();

	private boolean playerTurn;
	private int leastUsedMana = Integer.MAX_VALUE;

	private void playerTurn( int mana, int playerHealth, int bossHealth, int usedMana) {
		int[] manaChange = {0};
		int[] bossHealthChange = {0};
		int[] playerHealthChange = {0};

		if (playerHealth > 0 && bossHealth > 0) {
			playerTurn = true;

			for(int i = 0; i < spellBook.spellCount; i ++) {
				switch (i) {
					case 0:
						manaChange[0] = 0;
						bossHealthChange[0] = 0;

						spellBook.magicMissile(manaChange, bossHealthChange, playerTurn);

						bossHealth += bossHealthChange[0];
						usedMana -= manaChange[0];

						bossTurn( mana + manaChange[0]);
						break;
					case 1:
						manaChange[0] = 0;
						bossHealthChange[0] = 0;
						playerHealthChange[0] = 0;

						spellBook.drain(manaChange, bossHealthChange, playerHealthChange, playerTurn);

						usedMana += manaChange[0];
						bossHealth += bossHealthChange[0];
						playerHealth += playerHealthChange[0];

						bossTurn( mana - manaChange[0]);
						break;
					case 2:
						spellBook.shield(manaChange, player, playerTurn);
						usedMana += 113;
						bossTurn();
						usedMana -= 113;
						break;
					case 3:
						spellBook.poison(manaChange, boss, playerTurn);
						usedMana += 173;
						bossTurn();
						usedMana -= 173;
						break;
					case 4:
						spellBook.recharge(manaChange, playerTurn);
						usedMana += 229;
						bossTurn();
						usedMana -= 229;
						break;
				}
			}
		} else if (boss.health <= 0) {
			leastUsedMana = Integer.min(leastUsedMana, usedMana);
		}
	}

	private void bossTurn( int mana) {
		if (player.health > 0 && boss.health > 0) {
			playerTurn = false;

			for(int i = 0; i < spellBook.spellCount; i ++) {
				switch (i) {
					case 0: spellBook.magicMissile(manaChange, boss, playerTurn); break;
					case 1: spellBook.drain(manaChange, boss, player, playerTurn); break;
					case 2: spellBook.shield(manaChange, player, playerTurn); break;
					case 3: spellBook.poison(manaChange, boss, playerTurn); break;
					case 4: spellBook.recharge(manaChange, playerTurn); break;
				}
			}

			int attack = boss.damage - player.armor;

			if (attack > 0)
				player.health -= attack;
			else
				player.health -= 1;

		} else if (boss.health <= 0) {
			leastUsedMana = Integer.min(leastUsedMana, usedMana);
		}
	}

	public void leastMana() {
		Scanner input = new Scanner(System.in);

		boss.health = Integer.parseInt( input.nextLine().split(" ")[2]);
		boss.damage = Integer.parseInt( input.nextLine().split(" ")[1]);

		playerTurn( 500, player.health, boss.health, 0);

		System.out.println(leastUsedMana);
	}
}
