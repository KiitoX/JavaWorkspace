package com.mcmanuellp.util;

public class ParseUtils
{
	public static boolean safeParseBoolean(String bool)
	{
		return Boolean.parseBoolean(bool.trim());
	}

	public static boolean safeParseBooleanExtensive(String bool)
	{
		boolean flag = false;
		if(bool != null)
		{
			if(bool.equalsIgnoreCase("1")) flag = true;
			if(bool.equalsIgnoreCase("0")) flag = false;
			if(bool.equalsIgnoreCase("true")) flag = true;
			if(bool.equalsIgnoreCase("false")) flag = false;
			if(bool.equalsIgnoreCase("t")) flag = true;
			if(bool.equalsIgnoreCase("f")) flag = false;
			if(bool.equalsIgnoreCase("yes")) flag = true;
			if(bool.equalsIgnoreCase("no")) flag = false;
			if(bool.equalsIgnoreCase("y")) flag = true;
			if(bool.equalsIgnoreCase("n")) flag = false;
		}
		return flag;
	}

	public static int safeParseBinInt(String num)
	{
		try
		{
			return Integer.parseInt(num.trim(), 2);
		}
		catch(NumberFormatException e)
		{
			return 0;
		}
	}

	public static int safeParseOctInt(String num)
	{
		try
		{
			return Integer.parseInt(num.trim(), 8);
		}
		catch(NumberFormatException e)
		{
			return 0;
		}
	}

	public static int safeParseDecInt(String num)
	{
		try
		{
			return Integer.parseInt(num.trim(), 10);
		}
		catch(NumberFormatException e)
		{
			return 0;
		}
	}

	public static int safeParseHexInt(String num)
	{
		try
		{
			return Integer.parseInt(num.trim(), 16);
		}
		catch(NumberFormatException e)
		{
			return 0;
		}
	}

	public static String[] parseStringArray(String csv)
	{
		return csv.split(",", -1);
	}
}
