package com.mcmanuellp.textad_take1.screens;

import com.mcmanuellp.textad_take1.TextAdventure;
import com.mcmanuellp.textad_take1.api.IOption;
import com.mcmanuellp.textad_take1.api.IScreen;

public class OptionsScreen implements IScreen
{
	public              boolean onScreen = false;
	public static final char[]  options  = new char[] {'d', 'm'};

	public OptionsScreen()
	{
		onScreen = true;
	}

	public void apply()
	{
		onScreen = true;

		System.out.println("Options Screen\n______________");
		System.out.print("press key 'd' to change debug option\npress key 'm' to get to main menu\nInput key: ");

		while(onScreen)
		{
			switch(ScreenHelper.waitForInput(options, TextAdventure.scanner))
			{
				case 'd':
					onScreen = false;
					setBooleanConfig(TextAdventure.options.debugConfig);
					break;
				case 'm':
					System.out.println();
					onScreen = false;
					TextAdventure.running.setCurrentScreen(TextAdventure.mainScreen);
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

	boolean setBooleanConfig(IOption option)
	{
		char[] choices = new char[] {'y', 'n', 'g'};
		System.out.println("Edit Value");
		switch(ScreenHelper.waitForInput(choices, TextAdventure.scanner))
		{
			case 'y':
				option.setOptionValue(true);
				break;
			case 'n':
				option.setOptionValue(false);
				break;
			case 'g':
				System.out.println(option.getOptionValue());
				break;
			default:
				return false;
		}
		onScreen = true;
		return true;
	}
}
