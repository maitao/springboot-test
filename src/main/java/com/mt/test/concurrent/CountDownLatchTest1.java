package com.mt.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchTest1 implements Runnable {
	final AtomicInteger number = new AtomicInteger();
	volatile boolean bol = false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(number.getAndIncrement());
		synchronized (this) {
			try {
				if (!bol) {
					System.out.println(bol);
					bol = true;
					Thread.sleep(10000);
				}
			} catch (InterruptedException e) {

			}
			System.out.println("并发数量为" + number.intValue());
		}
	}

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		CountDownLatchTest1 test = new CountDownLatchTest1();
		for (int i = 0; i < 10; i++) {
			pool.execute(test);
		}
	}

}
