package AdventOfCode;

import java.security.MessageDigest;
import java.util.Scanner;

public class Day4_The_Ideal_Stocking_Stuffer {
	public static void MD5() throws Exception {
		StringBuilder hexString = new StringBuilder();
		Scanner input = new Scanner(System.in);
		String key = input.next();
		int number = 0;

		MessageDigest md = MessageDigest.getInstance("MD5");

		do {
			hexString.setLength(0);
			number++;
			String combined = key + String.valueOf(number);
			md.update(combined.getBytes());
			byte[] hash = md.digest();

			for (byte aHash : hash) {
				if ((0xff & aHash) < 0x10) {
					hexString.append("0").append(Integer.toHexString((0xFF & aHash)));
				} else {
					hexString.append(Integer.toHexString(0xFF & aHash));
				}
			}

		} while (!hexString.toString().startsWith("00000"));

		System.out.println(number);
		input.close();
	}

	public static void MD5Advanced() throws Exception {
		StringBuilder hexString = new StringBuilder();
		Scanner input = new Scanner(System.in);
		String key = input.next();
		int number = 0;

		MessageDigest md = MessageDigest.getInstance("MD5");

		do {
			hexString.setLength(0);
			number++;
			String combined = key + String.valueOf(number);
			md.update(combined.getBytes());
			byte[] hash = md.digest();

			for (byte aHash : hash) {
				if ((0xff & aHash) < 0x10) {
					hexString.append("0").append(Integer.toHexString((0xFF & aHash)));
				} else {
					hexString.append(Integer.toHexString(0xFF & aHash));
				}
			}

		} while (!hexString.toString().startsWith("000000"));

		System.out.println(number);
		input.close();
	}


}

