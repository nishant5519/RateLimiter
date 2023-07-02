package com.algorithms.ratelimit;

public class TokenBucket {
	
	private int bucketCapacity;
	private int refillRate;
	private int currentCapacity;
	private long lastRefilledTime;

	
	public TokenBucket(int capacity , int refillRate) {
		this.bucketCapacity = capacity;
		this.refillRate = refillRate;
		this.currentCapacity = capacity;
		this.lastRefilledTime = System.currentTimeMillis();
		
	}
	
	public boolean isAllowed() {
		refill();
		if(currentCapacity > 0) { //tokens available
			currentCapacity--;
			return true;
		}
		return false;
	}
	
	private void refill() {
		long currentTime = System.currentTimeMillis();
		int refillTokens = (int)(currentTime - lastRefilledTime)*refillRate;
		currentCapacity = Math.min(bucketCapacity, refillTokens + currentCapacity);
		lastRefilledTime = System.currentTimeMillis();
	}
}
