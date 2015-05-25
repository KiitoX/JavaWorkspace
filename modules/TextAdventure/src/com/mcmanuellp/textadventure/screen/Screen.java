package com.mcmanuellp.textadventure.screen;

public class Screen
{
	String name;
	String[] drawContent;

	public Screen(String name)
	{
		this.name = name;
		this.setDrawContent(new String[]{"ScreenName: " + getName()});
	}

	public void apply()
	{
		draw();
		action();
	}

	public void draw()
	{
		for(String s : getDrawContent())
		{
			System.out.println(s);
		}
	}

	public void action()
	{
		System.out.println("No action defined, this may be a bug!");
	}

	public void setDrawContent(String[] content)
	{
		drawContent = content;
	}

	public String[] getDrawContent()
	{
		return drawContent;
	}

	public String getName()
	{
		return name;
	}

	public static class s
	{
		public static Screen main_menu = new ScreenMainMenu();
		public static Screen options   = new ScreenOptions();
		public static Screen play      = new ScreenPlay();
	}
}
