package com.mcmanuellp.textad_take1;

import com.mcmanuellp.textad_take1.api.IOption;
import com.mcmanuellp.textad_take1.options.OptionBooleanDebug;
import com.mcmanuellp.textad_take1.screens.MainScreen;
import com.mcmanuellp.textad_take1.screens.OptionsScreen;
import com.mcmanuellp.textad_take1.screens.PlayScreen;

import java.util.Scanner;

public class TextAdventure
{
	public static ThreadRunning running;

	public static Scanner scanner;

	public static MainScreen mainScreen;
	public static OptionsScreen optionsScreen;
	public static PlayScreen playScreen;

	public static TextAdventure.Options options;

	public static void main(String[] args)
	{
		scanner = new Scanner(System.in);

		mainScreen = new MainScreen();
		optionsScreen = new OptionsScreen();
		playScreen = new PlayScreen();

		running = new ThreadRunning(mainScreen);
	}

	public static void onExit()
	{
		TextAdventure.scanner.close();
	}

	public static void exit()
	{
		running.exit();
	}

	public class Options
	{
		public final IOption debugConfig = new OptionBooleanDebug(false);
	}
}
