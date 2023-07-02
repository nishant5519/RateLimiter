package com.algorithms.ratelimit;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LeakyBucket {
	 private BlockingQueue<Integer> queue;

	    public LeakyBucket(int capacity) {
	        this.queue = new LinkedBlockingQueue<>(capacity);
	    }

	    public boolean isAllowed() {
	        if(queue.remainingCapacity() > 0){
	            queue.add(1);
	            return true;
	        }
	        return false;
	    }
}
