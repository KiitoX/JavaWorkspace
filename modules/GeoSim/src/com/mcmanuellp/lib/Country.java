package com.mcmanuellp.lib;

public class Country
{
	String     name;
	Government government;
	Religion   religion;

	public static Country empty = new Country("dummy", Government.none, Religion.open);

	private Country(String name, Government government, Religion religion)
	{
		this.name = name;
		this.government = government;
		this.religion = religion;
	}
}
