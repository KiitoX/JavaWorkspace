package com.mcmanuellp.lib;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command implements ICommand
{
	public final String name;
	public final String shortHelp;
	public final ArrayList<String> fullHelp;
	public final ArrayList<Command> args;

	public Command(String name)
	{
		this(name, "unspecified");
	}

	public Command(String name, String shortHelp)
	{
		this(name, shortHelp, shortHelp);
	}

	public Command(String name, String shortHelp, String... fullHelp)
	{
		this(name, shortHelp, new ArrayList<>(Arrays.asList(fullHelp)), new ArrayList<>());
	}

	public Command(String name, String shortHelp, ArrayList<String> fullHelp, ArrayList<Command> args)
	{
		if(!name.contains(" ")) this.name = name;
		else this.name = name.substring(0, name.indexOf(" "));
		this.shortHelp = shortHelp;
		this.fullHelp = fullHelp;
		this.args = args;
	}

	public final void printHelp()
	{
		fullHelp.forEach(System.out::println);
	}

	@Override
	public void onCalled(String args)
	{
		boolean foundUsage = false;
		for(Command c : this.args)
		{
			if(args.startsWith(c.name))
			{
				foundUsage = true;
				c.onCalled(args.substring(c.name.length()));
				break;
			}
		}
		if(!foundUsage)
		{
			System.out.println("Illegal Parameters!");
			printHelp();
		}
	}
}
