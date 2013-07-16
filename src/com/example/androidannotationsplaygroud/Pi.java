package com.example.androidannotationsplaygroud;

public class Pi {

	public static double calcPi(int iterations) {
		double x, y;
		int numHits = 0;
		
		for (int i = 0; i < iterations; i++) {
			x = Math.random();
			y = Math.random();
			
			if (Math.sqrt(x*x + y*y) <= 1) {
				numHits++;
			}
		}
		
		double pi = 4 * (double)numHits / (double)iterations;
		return pi;
	}
}
