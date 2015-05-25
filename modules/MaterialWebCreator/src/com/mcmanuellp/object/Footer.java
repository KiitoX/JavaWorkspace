package com.mcmanuellp.object;

import java.util.ArrayList;
import java.util.List;

public class Footer implements HTMLObject
{
	String text;

	public Footer(String text)
	{
		this.text = text;
	}

	public List<String> getWritable()
	{
		ArrayList<String> writable = new ArrayList<>();
		writable.add("<footer class=\"footer shadow-z-1 width\">");
		writable.add("<div class=\"container text-muted\">");
		writable.add(text);
		writable.add("</div>");
		writable.add("</footer>");
		return writable;
	}
}
