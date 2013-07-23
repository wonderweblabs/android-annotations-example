package com.example.pi.before;

public class Result {
	public int hits;
	public int misses;
	public double pi;
	public int iterations;
	
	public Result(int hits, int misses, double pi, int iterations) {
		this.hits = hits;
		this.misses = misses;
		this.pi = pi;
		this.iterations = iterations;
	}
}
