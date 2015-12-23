package AdventOfCode;

import java.util.Scanner;

public class Day22_Wizard_Simulator_20XX {
	private int bossDamage;
	private int leastUsedMana = Integer.MAX_VALUE;

	private void playerTurn( int mana, int playerHealth, int bossHealth, int usedMana,
	                         int activeShield, boolean addedArmor, int activePoison, int activeRecharge) {
		playerHealth -= 1;

		// Overtime effects
		if (activeShield > 0) {
			activeShield --;
		}

		if (activePoison > 0) {
			bossHealth -= 3;
			activePoison --;
		}

		if (activeRecharge > 0) {
			mana += 101;
			activeRecharge --;
		}
		//

		if (playerHealth > 0 && bossHealth > 0 && mana >= 53) {

			int spellCount = 5;
			for(int i = spellCount; i > 0; i --) {
				switch (i) {
					case 1:
						if (mana >= 53)
							bossTurn( mana - 53, playerHealth, bossHealth - 4, usedMana + 53,
								activeShield, addedArmor, activePoison, activeRecharge);
						break;
					case 2:
						if (mana >= 73)
							bossTurn( mana - 73, playerHealth + 2, bossHealth - 2, usedMana + 73,
								activeShield, addedArmor, activePoison, activeRecharge);
						break;
					case 3:
						if (mana >= 113 && activeShield == 0)
							bossTurn( mana - 113, playerHealth, bossHealth, usedMana + 113,
									6, addedArmor, activePoison, activeRecharge);
						break;
					case 4:
						if (mana >= 173 && activePoison == 0)
							bossTurn( mana - 173, playerHealth, bossHealth, usedMana + 173,
									activeShield, addedArmor, 6, activeRecharge);
						break;
					case 5:
						if (mana >= 229 && activeRecharge == 0)
							bossTurn( mana - 229, playerHealth, bossHealth, usedMana + 229,
									activeShield, addedArmor, activePoison, 5);
						break;
				}
			}
		} else if (bossHealth <= 0) {
			if (Integer.min(leastUsedMana, usedMana) != leastUsedMana) {
				System.out.println( usedMana);
			}
			leastUsedMana = Integer.min(leastUsedMana, usedMana);
		}
	}

	private void bossTurn( int mana, int playerHealth, int bossHealth, int usedMana,
	                       int activeShield, boolean addedArmor, int activePoison, int activeRecharge) {
		// Overtime effects
		if (activeShield > 0) {
			addedArmor = true;
			activeShield --;
		} else {
			addedArmor = false;
		}

		if (activePoison > 0) {
			bossHealth -= 3;
			activePoison --;
		}

		if (activeRecharge > 0) {
			mana += 101;
			activeRecharge --;
		}
		//

		if (bossHealth > 0) {
			int attack;

			if (addedArmor) {
				attack = bossDamage - 7;
			} else {
				attack = bossDamage;
			}

			if (attack > 0)
				playerHealth -= attack;
			else
				playerHealth -= 1;

			playerTurn( mana, playerHealth, bossHealth, usedMana,
					activeShield, addedArmor, activePoison, activeRecharge);

		} else {
			if (Integer.min(leastUsedMana, usedMana) != leastUsedMana) {
				System.out.println( usedMana);
			}
			leastUsedMana = Integer.min(leastUsedMana, usedMana);
		}
	}

	public void leastMana() {
		Scanner input = new Scanner(System.in);

		int startingMana = 500;
		int playerHealth = 50;
		int bossHealth = Integer.parseInt( input.nextLine().split(" ")[2]);
		bossDamage = Integer.parseInt( input.nextLine().split(" ")[1]);

		playerTurn( startingMana, playerHealth, bossHealth, 0,
				0, false, 0, 0);

		System.out.println(leastUsedMana);
	}
}
