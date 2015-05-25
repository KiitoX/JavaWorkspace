package com.mcmanuellp.lib;

public class Government
{
	//TODO add more
	public static final Government none      = new Government("None");
	public static final Government broken    = new Government("Broken");
	public static final Government anarchy   = new Government("Anarchy");
	public static final Government republic  = new Government("Republic");
	public static final Government democracy = new Government("Democracy");
	public static final Government monarchy  = new Government("Monarchy");

	public final String name;

	private Government(String name)
	{
		this.name = name;
	}
}
