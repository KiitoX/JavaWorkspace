package com.mcmanuellp.lib;

public class Religion
{
	//TODO add more
	public static final Religion open = new Religion("Open (only used for liberal countries)");

	public static final Religion christian = new Religion("Christian");

	public static final Religion catholic   = new Religion("Catholic Church");
	public static final Religion angelic    = new Religion("Angelic Church");
	public static final Religion protestant = new Religion("Protestant Church");
	public static final Religion orthodox   = new Religion("Orthodox Church");
	public static final Religion mormons    = new Religion("Mormon Church");

	public static final Religion jewish = new Religion("Jewish");

	public static final Religion islamic = new Religion("Islamic");

	public static final Religion sunnites = new Religion("Sunnite");
	public static final Religion shiites  = new Religion("Shiite");
	public static final Religion kurdish  = new Religion("Kurdish");

	public static final Religion buddhism = new Religion("Buddhism");

	public static final Religion hinduism = new Religion("Hinduism");

	public static final Religion scientology = new Religion("Scientology");
	public static final Religion jediknights = new Religion("Jedi Knights");
	public static final Religion illuminati  = new Religion("Illuminati");

	public final String name;

	private Religion(String name)//TODO add more differences to each religion
	{
		this.name = name;
	}
}
