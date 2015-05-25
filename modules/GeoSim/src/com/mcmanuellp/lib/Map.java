package com.mcmanuellp.lib;

import java.util.HashMap;

public class Map
{
	public static final Map get = new Map();

	public final HashMap<String, Region> regions;

	private Map()
	{
		regions = Region.get();
	}
}
