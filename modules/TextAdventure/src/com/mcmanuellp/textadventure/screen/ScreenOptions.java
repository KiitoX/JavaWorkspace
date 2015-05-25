package com.mcmanuellp.textadventure.screen;

import com.mcmanuellp.textadventure.TextAdventure;

public class ScreenOptions extends Screen
{
	public ScreenOptions()
	{
		super("options");
		this.setDrawContent(new String[]{"---:---:--Options--:---:---",
		                                 " -press 'd' to edit debug- ",
										 " -press 't' to edit testV- ",
		                                 "  -press 'x' to get back-  "});
	}

	@Override
	public void apply()
	{
		applyC(0);
	}

	public void applyC(int mode)
	{
		switch(mode)
		{
			case 0:
				drawC(0);
				actionC(0);
				break;
			case 1:
				drawC(1);
				actionC(1);
				break;
			case 2:
				drawC(2);
				actionC(2);
				break;
		}
	}

	public void drawC(int mode)
	{
		switch(mode)
		{
			case 0:
				for(String s : getDrawContent())
				{
					System.out.println(s);
				}
				break;
			case 1:
				System.out.println("debug is currently set to: " + TextAdventure.O.debug);
				System.out.println("set option debug to: 1/0 (true/false), 'x' to cancel");
				break;
			case 2:
				System.out.println("test is currently set to: " + TextAdventure.O.test);
				System.out.println("set option test to: 1/0 (true/false), 'x' to cancel");
				break;
		}

	}

	public void actionC(int mode)
	{
		switch(mode)
		{
			case 0:
				char action = TextAdventure.s.getC(new char[]{'d', 't', 'x'});
				switch(action)
				{
					case 'd':
						this.applyC(1);
						break;
					case 't':
						this.applyC(2);
						break;
					case 'x':
						TextAdventure.setCurrentScreen(s.main_menu);
						break;
				}
				break;
			case 1:
				char var1 = TextAdventure.s.getC(new char[]{'0', '1', 'x'});
				switch(var1)
				{
					case '0':
						TextAdventure.O.debug = '0';
						break;
					case '1':
						TextAdventure.O.debug = '1';
						break;
					case 'x':
						break;
				}
				this.applyC(0);
				break;
			case 2:
				TextAdventure.O.test = TextAdventure.O.setToEither(new char[]{'0', '1', 'x'}, TextAdventure.O.test);
				this.applyC(0);
				break;
		}
	}
}
