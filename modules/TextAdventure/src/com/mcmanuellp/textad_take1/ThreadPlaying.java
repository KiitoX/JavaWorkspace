package com.mcmanuellp.textad_take1;

public class ThreadPlaying extends Thread
{
	boolean active;

	public ThreadPlaying()
	{
		this.active = true;
		this.setName("TextAdventure playing");
		this.start();
	}

	@Override
	public void run()
	{
		while(this.active)
		{
			System.out.println("HAVE FUN!");//TODO actually implement playing (lawl?)
		}
	}

	public void exit()
	{
		this.active = false;
	}
}
