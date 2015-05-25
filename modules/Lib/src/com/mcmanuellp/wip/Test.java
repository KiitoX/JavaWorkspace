package com.mcmanuellp.wip;

import com.mcmanuellp.lib.Command;
import com.mcmanuellp.lib.CommandHandler;
import com.mcmanuellp.util.ScannerUtils;

public class Test
{
	public static void main(String[] args)
	{
		CommandHandler handler = new CommandHandler();
		Command c1 = new Command("ce1", "ce1 executes nothing") {
			@Override
			public void onCalled(String args)
			{
				System.out.println("c1 was called with '" + args + "' as arguments");
			}
		};
		Command c2 = new Command("ce2", "ce2 executes nothing") {
			@Override
			public void onCalled(String args)
			{
				System.out.println("c2 was called with '" + args + "' as arguments");
			}
		};
		Command c3 = new Command("ce3", "ce3 executes nothing") {
			@Override
			public void onCalled(String args)
			{
				System.out.println("c3 was called with '" + args + "' as arguments");
			}
		};
		handler.addAll(c1, c2, c3);
		ScannerUtils scannerUtils = new ScannerUtils();
		String input = "";
		while(!input.equals("@"))
		{
			input = scannerUtils.next();
			handler.callCommand(input);
		}
		scannerUtils.close();
	}
}