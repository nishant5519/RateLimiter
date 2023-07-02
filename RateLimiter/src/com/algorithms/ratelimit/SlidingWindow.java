package com.algorithms.ratelimit;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindow {
	
	Queue<Long> slidingWindow;
	int timeWindowInSeconds;
	int bucketCapacity;
	
	public SlidingWindow(int timeWindowInSeconds, int bucketCapacity) {
		this.timeWindowInSeconds = timeWindowInSeconds;
        this.bucketCapacity = bucketCapacity;
        slidingWindow = new ConcurrentLinkedQueue<>();
	}
	
	public boolean isAllowed() {
		long currentTime = System.currentTimeMillis();
		updateQueue(currentTime);
		if(slidingWindow.size() < bucketCapacity) {
			slidingWindow.offer(currentTime);
			return true;
		}
		return false;
	}
	
	private void updateQueue(long currentTime) {
		if(slidingWindow.isEmpty())
			return;
		
        long calculatedTime = (currentTime - slidingWindow.peek())/1000;
        while(calculatedTime >= timeWindowInSeconds) {
        	slidingWindow.poll();
        	if(slidingWindow.isEmpty())
        		return;
        	calculatedTime = (currentTime - slidingWindow.peek())/1000;
        	
        }
        
		
	}

}
