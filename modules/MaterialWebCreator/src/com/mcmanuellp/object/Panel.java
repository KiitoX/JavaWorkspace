package com.mcmanuellp.object;

import java.util.ArrayList;
import java.util.List;

public class Panel implements HTMLObject
{
	public Panel()
	{

	}

	public List<String> getWritable()
	{
		return new ArrayList<>();
	}
}
