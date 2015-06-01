package com.mcmanuellp.primecalc;

import com.mcmanuellp.util.SleepUtil;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class PrimeCalc
{
	public static void main(String[] args) throws InterruptedException
	{
		CopyOnWriteArrayList<Integer> primeNumbers = new CopyOnWriteArrayList<>();
		//starting threads to calculate every possible divisor
		for(int i = 2; i < 10000; i++)
		{
			ThreadCalc t = new ThreadCalc(i, primeNumbers);
			t.start();
		}
		//waiting for threads to finish
		while(Thread.activeCount() > 2)
		{
			SleepUtil.sleepM(1);
		}
		System.out.println("threaded: " + primeNumbers.size());
		Collections.sort(primeNumbers);
		primeNumbers.stream().filter(i -> i > 9900).forEach(System.out::println);
	}
}
