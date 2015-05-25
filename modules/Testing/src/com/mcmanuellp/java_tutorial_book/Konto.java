package com.mcmanuellp.java_tutorial_book;

public class Konto
{
	static String ktnummer;
	static double ktstand;
	
	public static void kontoErstellen(String kontonummer, double kontostand)
	{
		if(kontonummer.length() == 10)
		{
			ktnummer = kontonummer;
		}
		ktstand = kontostand;
	}
	
	public static double getKontostand()
	{
		return ktstand;
	}
	
	public static String getKontonummer()
	{
		return ktnummer;
	}
	
	public static void einzahlen(double betrag)
	{
		System.out.println(betrag);
		ktstand += betrag;
	}
	
	public static void auszahlen(double betrag)
	{
		System.out.println(betrag);
		ktstand -= betrag;
	}
}