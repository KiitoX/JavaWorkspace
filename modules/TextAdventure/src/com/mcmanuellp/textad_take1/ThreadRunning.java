package com.mcmanuellp.textad_take1;

import com.mcmanuellp.textad_take1.api.IScreen;

public class ThreadRunning extends Thread
{
	IScreen currentScreen;
	boolean active;

	public ThreadRunning(IScreen currentScreen)
	{
		this.currentScreen = currentScreen;
		this.active = true;
		this.setName("TextAdventure running");
		this.start();
	}

	@Override
	public void run()
	{
		while(this.active)
		{
			if(getCurrentScreen() != null)
			{
				currentScreen.apply();
			}
		}
		TextAdventure.onExit();
	}

	public void exit()
	{
		this.active = false;
	}

	public IScreen getCurrentScreen()
	{
		return this.currentScreen;
	}

	public void setCurrentScreen(IScreen screen)
	{
		this.currentScreen = screen;
	}
}
