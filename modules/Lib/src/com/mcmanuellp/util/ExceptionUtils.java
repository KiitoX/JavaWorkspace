package com.mcmanuellp.util;

public class ExceptionUtils
{
	public static void handle(Exception e, boolean stackTrace, boolean exitOnThrown, String... customMessages)
	{
		for(String s : customMessages)
		{
			System.out.println(s);
		}
		if(stackTrace) e.printStackTrace();
		else System.out.println(e.getClass().getName() + ": " + e.getMessage());
		if(exitOnThrown) System.exit(-1);
	}
}
