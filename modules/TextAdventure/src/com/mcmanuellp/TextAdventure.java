package com.mcmanuellp;

import java.util.Arrays;

public class TextAdventure
{
	public static class args
	{
		public String args;
		public String info;

		args()
		{
			info = "-dm:<true/false>";
		}

		public void read(String[] args)
		{
			this.args = Arrays.toString(args);

			for(String arg : args)
			{
				if(arg.startsWith(""))
				{

				}
			}
		}
	}

	public static args args = new args();

	public static void main(String[] args)
	{
		TextAdventure.args.read(args);


	}


}
