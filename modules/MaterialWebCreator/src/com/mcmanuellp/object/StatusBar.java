package com.mcmanuellp.object;

import java.util.ArrayList;
import java.util.List;

public class StatusBar implements HTMLObject
{
	public MaterialColor color;

	public StatusBar(MaterialColor color)
	{
		this.color = color;
	}

	public List<String> getWritable()
	{
		ArrayList<String> writable = new ArrayList<>();
		writable.add("<div class=\"navbar navbar-material-" + color.getWritable() + " navbar-fixed-top shadow-z-2 width\">");
		writable.add("<div class=\"container\">");
		//TODO add content
		writable.add("</div>");
		writable.add("</div>");
		return writable;
	}
}
