package com.avancial.socle.utils;

import java.lang.reflect.Array;
import java.math.BigInteger;

public class Convertisseur {
	public static String hexToBin(String s) {
		return new BigInteger(s, 16).toString(2);
	}

	public static String asciiToHex(String s) {
		char[] chars = s.toCharArray();
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString();
	}

	public static String asciiToBin(String s) {
		return hexToBin(asciiToHex(s));
	}

	public static String asciiToBinary(String asciiString) {

		byte[] bytes = asciiString.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			// binary.append(' ');
		}
		return binary.toString();
	}

	public static String test2(Object object) {
		return hexToBin(byteToBinary(object));
	}

	/**
	 * Convertit un tableau de bytes correspondant au régime en séquence binaire.
	 * @param x
	 * @return
	 */
	public static String byteToBinary(Object x) {
		StringBuffer sb = new StringBuffer();
		int length = Array.getLength(x);

		for (int i = 0; i < length; i++) {
			byte b = (byte) Array.get(x, i);
			sb.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
		}
		
		return sb.toString();
	}
}
