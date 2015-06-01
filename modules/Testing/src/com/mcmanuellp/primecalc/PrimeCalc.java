package com.mcmanuellp.primecalc;

import com.mcmanuellp.util.SleepUtil;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class PrimeCalc
{
	public static void main(String[] args) throws InterruptedException
	{
		int maxThreadsRunning = 0;
		CopyOnWriteArrayList<Integer> primeNumbers = new CopyOnWriteArrayList<>();
		//starting threads to calculate every possible divisor
		long start = System.nanoTime();
		for(int i = 2; i < 10000; i++)
		{
			int threadsRunning = Thread.activeCount();
			if(threadsRunning > maxThreadsRunning) maxThreadsRunning = threadsRunning;
			ThreadCalc t = new ThreadCalc(i, primeNumbers);
			t.start();
		}
		//waiting for threads to finish
		while(Thread.activeCount() > 2)
		{
			SleepUtil.sleepM(1);
		}
		long end = System.nanoTime();
		long time = end - start;
		float timeS = (float)time / (float)(Math.pow(10, 9));
		System.out.printf("Max amount of Threads ran at the same time: %d\n", maxThreadsRunning);
		System.out.printf("Time used to process: %.3fs (%dns)\n", timeS, time);
		System.out.printf("Found: %d prime numbers\n", primeNumbers.size());
		Collections.sort(primeNumbers);
		primeNumbers.stream().filter(i -> i > 9900).forEach(System.out::println);
	}
}
