package com.mcmanuellp.java_tutorial_book;
// MK. V

import java.util.Scanner;
import java.util.Random;

public class Mastermind_Mk1
{
	final static int anzahlSteine = 4;
	
	static String[] stein = new String[anzahlSteine];
	static String[] geraten = new String[anzahlSteine];
	
	static int trefferPosUndFarbe = 0;
	static int trefferFarbe = 0;

	static void kombiEinlesen()
	{
		System.out.println("  Bitte eine Kombination aus vier Farben eingeben: ");
		Scanner sc = new Scanner(System.in);
		geraten[0] = sc.next();
		geraten[1] = sc.next();
		geraten[2] = sc.next();
		geraten[3] = sc.next();
	}
	
	static void kombiAuswerten()
	{
		boolean[] ausgewertetS = {false, false, false, false};
		boolean[] ausgewertetG = {false, false, false, false};
		
		for (int i = 0; i < stein.length; i++)
		{
			if (stein[i].equals(geraten[i]) == true)
			{
				trefferPosUndFarbe++;
				ausgewertetS[i] = true;
				ausgewertetG[i] = true;
			}
		}
		for (int i = 0; i < stein.length; i++)
		{
			if (ausgewertetS[i] == false)
			{
				for (int j = 0; j < geraten.length; j++)
				{
					if (j == i)
						continue;
					
					if (ausgewertetG[j] == false && stein[i].equals(geraten[j]) == true)
					{
						trefferFarbe++;
						ausgewertetS[i] = true;
						ausgewertetG[j] = true;
						break;
					}
				}
			}
		}
	}
	
	static void kombiBewerten()
	{
		if (trefferPosUndFarbe == 4)
		{
			System.out.println("\n\n  Gratulation!!! - du hast die Kombination erraten!\n");
		}
		else
		{
			System.out.println("\n Treffer (Position und Farbe): " + trefferPosUndFarbe + "\n Treffer              (Farbe): " + trefferFarbe + "\n\n");
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println("*****************************");
		System.out.println("  Willkommen in Mastermind!  ");
		System.out.println("  M�gliche Farben sind: ");
		System.out.println("  rot" + "\n" + "  gelb" + "\n" + "  gr�n" + "\n" + "  blau" + "\n" + "  schwarz" + "\n" + "  wei�" + "\n");
		System.out.println();
		
		Random generator = new Random();
		
		int n;
		
		for (int i = 0; i < stein.length; i++)
		{
			n = generator.nextInt(6) + 1;
			switch(n)
			{
				case 1:  stein[i] = "wei�";
						 break;
				case 2:  stein[i] = "schwarz";
						 break;
				case 3:  stein[i] = "rot";
						 break;
				case 4:  stein[i] = "gelb";
						 break;
				case 5:  stein[i] = "gr�n";
						 break;
				case 6:  stein[i] = "blau";
						 break;
				default: stein[i] = "";
			}
		}
		for (int i = 0; i < stein.length; i++) 
		{
			System.out.println(stein[i]);
		}
		do
		{
			trefferPosUndFarbe = 0;
			trefferFarbe = 0;
			
			kombiEinlesen();
			
			kombiAuswerten();
			
			kombiBewerten();
			
		} while (trefferPosUndFarbe < 4);
	}
}
