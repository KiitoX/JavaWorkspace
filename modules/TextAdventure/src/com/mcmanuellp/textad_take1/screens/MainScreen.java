package com.mcmanuellp.textad_take1.screens;

import com.mcmanuellp.textad_take1.TextAdventure;
import com.mcmanuellp.textad_take1.api.IScreen;

public class MainScreen implements IScreen
{
	public boolean onScreen = false;
	static final char[] possibleInputs = new char[] {'p', 'o', 'q'};

	public MainScreen()
	{
		onScreen = true;
	}

	public void apply()
	{
		onScreen = true;

		System.out.println("Main Screen\n___________");
		System.out.print("press key 'p' to play\npress key 'o' to see the options\npress key 'q' to quit\nInput key: ");

		while(onScreen)
		{
			switch(ScreenHelper.waitForInput(possibleInputs, TextAdventure.scanner))
			{
				case 'p':
					System.out.println();
					onScreen = false;
					TextAdventure.running.setCurrentScreen(TextAdventure.playScreen);
					break;
				case 'o':
					System.out.println();
					onScreen = false;
					TextAdventure.running.setCurrentScreen(TextAdventure.optionsScreen);
					break;
				case 'q':
					System.out.println("\nExiting...");
					onScreen = false;
					TextAdventure.exit();
					break;
				case 'x':
					System.out.print("Invalid input! Retry: ");
					break;
				default:
					System.out.print("Something broke. Retry anyways:");
					break;
			}
		}
	}
}
