package com.mcmanuellp.object;

import java.util.ArrayList;
import java.util.List;

public class Body implements HTMLObject
{
	StatusBar  statusBar;
	HTMLObject content;
	Footer     footer;

	public Body(StatusBar statusBar, HTMLObject content, Footer footer)
	{
		this.statusBar = statusBar;
		this.content = content;
		this.footer = footer;
	}

	public List<String> getWritable()
	{
		ArrayList<String> writable = new ArrayList<>();
		writable.addAll(statusBar.getWritable());
		writable.add("<div class=\"container shadow-z-1 width\">");
		writable.add("<div class=\"module-contents\">");
		writable.addAll(content.getWritable());
		writable.add("</div>");
		writable.add("</div>");
		writable.addAll(footer.getWritable());
		return writable;
	}
}
