package br.com.aas.util;

public class Util {
	
	public static int roundUp(double value) {
		int value_round = (int) Math.round(value);
		if((value - value_round) > 0) {
			value_round = value_round + 1;
		}
		return value_round;
	}

}
