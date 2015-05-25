package com.mcmanuellp.textadventure;

import com.mcmanuellp.textadventure.screen.Screen;

import java.io.*;
import java.util.Scanner;

public class TextAdventure
{
	public static CScanner      s;
	static        Screen        currentScreen;

	public static void main(String[] args)
	{
		O.readFromFile();
		s = new CScanner();
		setCurrentScreen(Screen.s.main_menu);
		s.close();
		O.writeToFile();
		System.out.println("Bye! o/");
	}

	public static void setCurrentScreen(Screen screen)
	{
		currentScreen = screen;
		screen.apply();
	}

	public static class CScanner
	{
		Scanner scanner;

		CScanner()
		{
			scanner = new Scanner(System.in);
		}

		public Scanner get()
		{
			return scanner;
		}

		public char getC(char[] chars)
		{
			while(true)
			{
				char in = get().next().charAt(0);
				for(char c : chars)
				{
					if(in == c)
					{
						return c;
					}
				}
			}
		}

		public void close()
		{
			scanner.close();
		}
	}

	public static class O
	{
		public static char debug;
		public static char test;

		static String fileName = "src\\com\\mcmanuellp\\textadventure\\options.txt";

		static void setInitialValues()
		{
			if(debug == ' ')
			{
				debug = '0';
			}
			if(test == ' ')
			{
				test = '1';
			}
		}

		public static void readFromFile()
		{
			setInitialValues();

			String line;

			try
			{
				FileReader fileReader = new FileReader(fileName);

				BufferedReader bufferedReader = new BufferedReader(fileReader);

				while((line = bufferedReader.readLine()) != null)
				{
					char id = line.charAt(0);
					char value = line.charAt(2);

					switch(id)
					{
						case 'd':
							if(value == '0' || value == '1')
							{
								debug = value;
							}
							break;
						case 't':
							if(value == 'y' || value == 'n')
							{
								test = value;
							}
							break;
					}
				}

				bufferedReader.close();
			}
			catch(FileNotFoundException ex)
			{
				System.out.println("Unable to open file '" + fileName + "'");
			}
			catch(IOException ex)
			{
				System.out.println("Error reading file '" + fileName + "'");
			}
		}

		public static void writeToFile()
		{
			try
			{
				FileWriter fileWriter = new FileWriter(fileName);

				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				bufferedWriter.write("d-"+debug+"\n");
				bufferedWriter.write("t-"+test+"\n");

				bufferedWriter.close();
			}
			catch(IOException ex)
			{
				System.out.println("Error writing to file '" + fileName + "'");
			}
		}

		public static char setToEither(char[] choice, char option)
		{
			char var1 = TextAdventure.s.getC(choice);
			switch(var1)
			{
				case '0':
					return '0';
				case '1':
					return '1';
				case 'x':
					break;
			}
			return option;
		}
	}
}
