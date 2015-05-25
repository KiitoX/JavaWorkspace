package com.mcmanuellp.textad_take1.screens;

import com.mcmanuellp.textad_take1.ThreadPlaying;
import com.mcmanuellp.textad_take1.api.IScreen;

public class PlayScreen implements IScreen
{
	public boolean onScreen = false;
	public static ThreadPlaying playing;

	public PlayScreen()
	{
		onScreen = true;
	}

	public void apply()
	{
		onScreen = true;

		System.out.println("Loading...\n");

		while(onScreen)
		{
			playing = new ThreadPlaying();
		}
	}
}
