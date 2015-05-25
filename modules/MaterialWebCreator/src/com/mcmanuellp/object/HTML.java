package com.mcmanuellp.object;

import java.util.ArrayList;
import java.util.List;

public class HTML implements HTMLObject
{
	String title;
	Body body;

	public HTML(String title, Body body)
	{
		this.title = title;
		this.body = body;
	}

	public List<String> getWritable()
	{
		ArrayList<String> writable = new ArrayList<>();
		writable.add("<!DOCTYPE html PUBLIC \"//W3C//DTD XHTML 1.0 Transitional//EN\"");
		writable.add("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		writable.add("<html lang=\"en\">");
		writable.add("<head>");
		writable.add("<title>" + title + "</title>");
		writable.add("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		writable.add("<link rel=\"stylesheet\" href=\"/css/bootstrap.min.css\">");
		writable.add("<link rel=\"stylesheet\" href=\"/css/material-wfont.min.css\">");
		writable.add("<link rel=\"stylesheet\" href=\"/css/ripples.min.css\">");
		writable.add("<link rel=\"stylesheet\" href=\"/css/mcmanuellp.css\">");
		writable.add("<style type=\"text/css\">");
		writable.add("</style>");
		writable.add("</head>");
		writable.add("<body>");
		writable.addAll(body.getWritable());
		writable.add("<script src=\"/js/jquery-1.11.2.min.js\"></script>");
		writable.add("<script src=\"/js/bootstrap.min.js\"></script>");
		writable.add("<script src=\"/js/material.min.js\"></script>");
		writable.add("<script src=\"/js/ripples.min.js\"></script>");
		writable.add("<script>");
		writable.add("$.material.init();");
		writable.add("</script>");
		writable.add("</body>");
		writable.add("</html>");
		return writable;
	}
}
