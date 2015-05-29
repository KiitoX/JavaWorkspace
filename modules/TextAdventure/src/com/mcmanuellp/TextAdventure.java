package com.mcmanuellp;

import com.mcmanuellp.lib.Changelog;
import com.mcmanuellp.util.AESUtils;
import com.mcmanuellp.util.SwingUtils;

import javax.swing.*;
import java.util.Arrays;

public class TextAdventure
{
	public static TextAdventure theGame = new TextAdventure();

	public static void main(String[] args)
	{
		//apply look and feel
		SwingUtils.setDefaultLookAndFeel();
		theGame.run(args);
	}

	class args
	{
		public String args;
		public String info;

		args()
		{
			info = "-path:<savePath>, -key:<encryptKey>, -jcefix";
		}

		public void read(String[] args)
		{
			this.args = Arrays.toString(args);

			for(String arg : args)
			{
				if(arg.startsWith("-jcefix"))
				{
					System.out.println("DISCLAIMER: Please remove this argument after running it once, you might have to rerun this after a java update.");
					System.out.println("DISCLAIMER: We do not take responsibility for any legal issues this might inflict.");
					AESUtils.applyJCEChanges();
				}
			}
		}
	}

	class changelog extends Changelog//TODO update this before every built artifact
	{
		public String version = "1.0";

		changelog()
		{
			super();
		}

		@Override
		public void init()
		{
			add("1.0", "initial build");
		}
	}

	public args      args;
	public changelog changelog;

	public Screen screen;

	TextAdventure()
	{
		args = new args();
		changelog = new changelog();
		screen = new Screen();
		screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void run(String[] args)
	{
		//read additional program arguments
		this.args.read(args);
	}

	public void exit()
	{
		System.exit(0);
	}
}
