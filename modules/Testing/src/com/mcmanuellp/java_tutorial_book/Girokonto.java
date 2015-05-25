package com.mcmanuellp.java_tutorial_book;

public class Girokonto extends Konto
{
	static double limit;
	
	public Girokonto(String kontonummer, double kontostand, double limit)
	{
		setLimit(limit);
		Konto.ktstand = kontostand;
		Konto.ktnummer = kontonummer;
	}
	
	double getLimit()
	{
		return limit;
	}
	
	void setLimit(double l)
	{
		limit = l;
	}
	
	public static void auszahlen(double betrag)
	{
		System.out.println(betrag);
		
		if(Konto.ktstand - betrag >= limit)
		{
			Konto.ktstand -= betrag;
		}
		else
		{
			System.out.println("�ber dem Limit");
		}
	}
}