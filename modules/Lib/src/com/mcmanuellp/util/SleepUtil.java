package com.mcmanuellp.util;

public class SleepUtil
{
	public static void sleepS(long seconds) throws InterruptedException
	{
		Thread.sleep(seconds * 1000);
	}

	public static void sleepCountdownS(long seconds) throws InterruptedException
	{
		for(int i = 0; i < seconds; i++)
		{
			System.out.println(seconds - i + "!");
			sleepS(1);
		}
	}

	public static void sleepCountdown10S(long seconds) throws InterruptedException
	{
		for(int i = 0; i < seconds - 9; i += 10)
		{
			System.out.println(seconds - i + "!");
			sleepS(10);
		}
	}

	public static void sleepM(long millis) throws InterruptedException
	{
		Thread.sleep(millis);
	}

	public static void sleepCountdownM(long millis) throws InterruptedException
	{
		for(int i = 0; i < millis; i++)
		{
			System.out.println(millis - i + "!");
			sleepM(1);
		}
	}

	public static void sleepCountdown10M(long millis) throws InterruptedException
	{
		for(int i = 0; i < millis - 9; i += 10)
		{
			System.out.println(millis - i + "!");
			sleepM(10);
		}
	}

	public static void sleepCountdownEveryXM(long millis, long interval) throws InterruptedException
	{
		if(millis % interval != 0) System.out.println("INF: millis 'should' be a multiple of interval, it won't hurt but 'might' result in unexpected behaviour");
		for(int i = 0; i < millis - (interval - 1); i += 10)
		{
			System.out.println(millis - i + "!");
			sleepM(interval);
		}
	}
}
