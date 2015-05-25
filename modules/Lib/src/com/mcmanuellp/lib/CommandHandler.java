package com.mcmanuellp.lib;

import com.mcmanuellp.util.CompareUtils;

import java.util.HashMap;

public class CommandHandler
{
	HashMap<String, Command> commands;

	public CommandHandler(Command... commands)
	{
		this.commands = new HashMap<>();
		addAll(commands);
	}

	public void callCommand(String args)
	{
		String command = args;
		if(command.contains(" ")) command = args.substring(0, args.indexOf(" "));//TODO better handling of args
		if(commands.containsKey(command)) commands.get(command).onCalled(args.substring(command.length()));
		else viewHelp();
	}

	/*public void startL()TODO put in separate listener class
	{

	}

	public void stopL()
	{

	}*/

	public void addAll(Command... commands)
	{
		for(Command c : commands)
		{
			add(c);
		}
	}

	public void add(Command command)
	{
		if(!commands.containsKey(command.name))
		{
			this.commands.put(command.name, command);
		} else System.out.println("ERR: didn't add command as handler already contains same name " + command.name);
	}

	public void viewHelp()
	{
		System.out.println("Help:");
		for(Command c : commands.values())//TODO mby better alignment?
		{
			System.out.println("\n" + c.name + " - " + c.shortHelp);
		}
	}
}
