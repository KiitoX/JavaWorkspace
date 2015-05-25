package com.mcmanuellp.textad_take1.screens;

import java.util.Scanner;

public class ScreenHelper
{
	public static char waitForInput(char[] expecting, Scanner sc)
	{
		char in = sc.next().charAt(0);
		for(char s : expecting)
		{
			if(in == s)
			{
				return s;
			}
		}
		return 'x';
	}
}
