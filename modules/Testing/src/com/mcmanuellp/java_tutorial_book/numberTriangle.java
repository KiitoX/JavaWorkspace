package com.mcmanuellp.java_tutorial_book;

public class numberTriangle
{
	public static void main(String[] args)
	{
		int i = 0;
		int j = 0;
		
		while(i < 9)
		{
			while(i <= j)
			{
				i++;
				System.out.print(i + " ");
			}
			System.out.println();
			i = 0;
			j++;
			if(j == 9)
			{
				break;
			}
		}
	}
}