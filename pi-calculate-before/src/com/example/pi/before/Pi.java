package com.example.pi.before;

public class Pi {

	public static Result calcPi(int iterations) {
		double x, y;
		int numHits = 0, numMisses = 0;
		
		for (int i = 0; i < iterations; i++) {
			x = Math.random();
			y = Math.random();
			
			if (Math.sqrt(x*x + y*y) <= 1) {
				numHits++;
			} else {
				numMisses++;
			}
		}
		
		double pi = 4 * (double)numHits / (double)iterations;
		return new Result(numHits, numMisses, pi, iterations);
	}
}
