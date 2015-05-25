package com.mcmanuellp.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LineUtils
{
	/**
	 * Return a list of strings for the input fileName -- used to parse configuration data
	 * from in-jar resource files.
	 *
	 * @param path relative path to file to be read
	 */
	public static List<String> getLines(String path, Class sourceLocation)
	{
		return getLines(sourceLocation.getClass().getResourceAsStream(path));
	}

	/**
	 * Return a list of strings for the input fileName -- used to parse configuration data
	 * from in-jar resource files.
	 *
	 * @param path path to file to be read
	 */
	public static List<String> getLines(String path) throws IOException
	{
		return getLines(new File(path));
	}

	/**
	 * Return a list of strings for the input fileName -- used to parse configuration data
	 * from in-jar resource files.
	 *
	 * @param file file to be read
	 */
	public static List<String> getLines(File file) throws IOException
	{
		return getLines(new FileInputStream(file));

	}

	/**
	 * Return a list of strings for the input fileName -- used to parse configuration data
	 * from in-jar resource files.
	 *
	 * @param inputStream inputStream to be read
	 */
	public static List<String> getLines(InputStream inputStream)
	{
		ArrayList<String> lines = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try
		{
			while((line = reader.readLine()) != null)
			{
				lines.add(line);
			}
		}
		catch(IOException e)
		{
			ExceptionUtils.handle(e, true, false);
		}
		try
		{
			inputStream.close();
		}
		catch(IOException e)
		{
			ExceptionUtils.handle(e, true, false);
		}
		return lines;
	}

	public static List<String> getLinesWithoutComments(List<String> lines)
	{
		List<String> filtered = new ArrayList<>();
		for(String line : lines)
		{
			if(line.startsWith("#")) continue;
			filtered.add(line);
		}
		return filtered;
	}

	public static List<String> splitLine(String line)
	{
		return splitLine(line, ",");
	}

	public static List<String> splitLine(String line, String regex)
	{
		return Arrays.asList(line.split(regex));
	}
}
