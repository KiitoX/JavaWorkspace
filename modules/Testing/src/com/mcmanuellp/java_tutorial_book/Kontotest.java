package com.mcmanuellp.java_tutorial_book;

import java.util.Scanner;

public class Kontotest
{
	private static double startsatz = 1000;
	static String ktnr = "0";
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		System.out.println("Kontonummer(10 Stellig):");
		
		while(ktnr.length() != 10)
		{
			ktnr = sc.next();
			
			if(ktnr.length() == 10)
			{
				Konto.kontoErstellen(ktnr, startsatz);
			}
		}
		
		System.out.println(Konto.getKontonummer()+ " : " + Konto.getKontostand());
		
		Konto.einzahlen(500);
		Konto.auszahlen(750.50);
		
		System.out.println(Konto.getKontostand());
		
		sc.close();
	}
}