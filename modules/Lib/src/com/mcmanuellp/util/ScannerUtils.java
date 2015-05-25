package com.mcmanuellp.util;

import java.util.Scanner;

public class ScannerUtils
{
	Scanner sc;

	public ScannerUtils()
	{
		sc = new Scanner(System.in);
	}

	public Scanner getScanner()
	{
		return sc;
	}

	public String next()
	{
		return sc.next();
	}

	public String last()
	{
		String s = next();
		close();
		return s;
	}

	public String nextLine()
	{
		return sc.nextLine();
	}

	public String lastLine()
	{
		String s = nextLine();
		close();
		return s;
	}

	public boolean hasNext()
	{
		return sc.hasNext();
	}

	public void close()
	{
		sc.close();
	}
}
