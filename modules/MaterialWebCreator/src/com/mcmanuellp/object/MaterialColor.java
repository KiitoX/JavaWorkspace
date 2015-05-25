package com.mcmanuellp.object;

import java.util.ArrayList;
import java.util.List;

public class MaterialColor implements HTMLObject
{
	public static final MaterialColor lightGreen900 = new MaterialColor("light-green-900");
	String name;

	MaterialColor(String name)
	{
		this.name = name;
	}

	public List<String> getWritable()
	{
		ArrayList<String> writable = new ArrayList<>();
		writable.add(name);
		return writable;
	}
}
