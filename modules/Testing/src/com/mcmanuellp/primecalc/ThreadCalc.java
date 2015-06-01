package com.mcmanuellp.primecalc;

import com.mcmanuellp.util.ExceptionUtils;

import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadCalc implements Runnable
{
	int number;
	boolean prime = true;
	boolean done  = false;

	Thread                        thread;
	String                        name;
	CopyOnWriteArrayList<Integer> arrayList;

	public ThreadCalc(int number, CopyOnWriteArrayList<Integer> arrayList)
	{
		this.number = number;
		this.name = "number" + number;
		this.arrayList = arrayList;
	}

	public void run()
	{
		try
		{
			for(int i = 2; i < number - 1; i++)
			{
				if(number % i == 0)
				{
					prime = false;
				}
			}
			done = true;
			if(prime) arrayList.add(number);
		}
		catch(Exception e)
		{
			ExceptionUtils.handle(e, true, false);
		}
	}

	public void start()
	{
		if(thread == null)
		{
			thread = new Thread(this, name);
			thread.start();
		}
	}
}
