package com.mcmanuellp.textadventure.screen;

import com.mcmanuellp.textadventure.TextAdventure;

public class ScreenMainMenu extends Screen
{
	public ScreenMainMenu()
	{
		super("main_menu");
		this.setDrawContent(new String[]{"---:---Main Menu---:---",
										 "  -press 'p' to play-  ",
										 "-press 'o' for options-",
										 "  -press 'x' to exit-  "});
	}

	@Override
	public void action()
	{
		char action = TextAdventure.s.getC(new char[]{'p', 'o', 'x'});
		switch(action)
		{
			case 'p':
				TextAdventure.setCurrentScreen(s.play);
				break;
			case 'o':
				TextAdventure.setCurrentScreen(s.options);
				break;
			case 'x':
				break;
		}
	}
}
