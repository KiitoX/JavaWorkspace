package com.mcmanuellp.website;

import com.mcmanuellp.object.HTMLObject;

import java.util.ArrayList;
import java.util.List;

interface MaterialWebsite
{
	List<HTMLObject> StatusBar();

	List<HTMLObject> Content();

	List<HTMLObject> Footer();

	default List<String> getWritable()
	{
		ArrayList<String> writeble = new ArrayList<>();
		for(HTMLObject o : StatusBar())
		{
			writeble.addAll(o.getWritable());
		}
		for(HTMLObject o : Content())
		{
			writeble.addAll(o.getWritable());
		}
		for(HTMLObject o : Footer())
		{
			writeble.addAll(o.getWritable());
		}
		return writeble;
	}
}