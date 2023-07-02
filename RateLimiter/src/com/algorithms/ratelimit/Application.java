package com.algorithms.ratelimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

	public static void main(String[] args) {
		
		ExecutorService executors = Executors.newFixedThreadPool(12);
		
		TokenBucket bucket =  new TokenBucket(1, 1);
		
		for(int i=0; i < 12 ;i ++) {
			executors.execute(()-> System.out.println(bucket.isAllowed()));
		}
		executors.shutdown();
		
		System.out.println("======================");
		
		LeakyBucket leakyBucket = new LeakyBucket(2);
			executors = Executors.newFixedThreadPool(12);
		
		for(int i=0; i < 12 ;i ++) {
			executors.execute(()-> System.out.println(leakyBucket.isAllowed()));
		}
		
		executors.shutdown();
		System.out.println("======================");
		
		SlidingWindow slidingWindow = new SlidingWindow(1,5);
		executors = Executors.newFixedThreadPool(12);
		
		for(int i=0; i < 12 ;i ++) {
			executors.execute(()-> System.out.println(slidingWindow.isAllowed()));
		}
		executors.shutdown();
	
	}

}
