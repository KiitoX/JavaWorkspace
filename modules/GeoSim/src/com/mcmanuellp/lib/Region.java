package com.mcmanuellp.lib;

import java.util.HashMap;

public class Region
{
	private static final HashMap<String, Region> regions = new HashMap<>();

	private static final String UNKNOWN = "XaPc345%*b";

	public final String     name;
	public final Properties properties;
	public final String[]   adjacentRegionKeys;
	public final String     key;
	public       Country    owner;
	public       Religion   religion;

	private Region(String name, Properties properties, Country owner, Religion religion, String key, String... adjacentRegionKeys)
	{
		this.name = name;
		this.properties = properties;
		this.owner = owner;//TODO assign country directly?
		this.religion = religion;
		this.key = key;
		this.adjacentRegionKeys = adjacentRegionKeys;
	}

	public static HashMap<String, Region> get()
	{
		//TODO add all regions in some order
		add("Baden", 100000, 10000, (byte)20, (byte)0, false, Country.empty, Religion.catholic, "baden", "wuerttemberg");
		add("Württemberg", 100000, 10000, (byte)20, (byte)0, false, Country.empty, Religion.catholic, "wuerttemberg", "baden");
		add("Florida", 80000, 40000, (byte)60, (byte)-20, false, Country.empty, Religion.christian, "florida", UNKNOWN);

		return regions;
	}

	private static void add(String name, int size, int population, byte temperature, byte humidity, boolean water, Country startCountry, Religion religion, String key, String... adjacentCountries)
	{
		add(new Region(name, new Properties(size, population, temperature, humidity, water), startCountry, religion, key, adjacentCountries));
	}

	private static void add(Region region)
	{
		regions.put(region.name, region);
	}

	public static class Properties
	{
		int  size;
		int  population;
		byte temperature;
		byte humidity;
		boolean water;

		//TODO maybe add some templates &| replace int/byte with custom enum

		public Properties(int size, int population, byte temperature, byte humidity, boolean water)
		{
			this.size = size;
			this.population = population;
			this.temperature = temperature;
			this.humidity = humidity;
			this.water = water;
		}
	}
}
