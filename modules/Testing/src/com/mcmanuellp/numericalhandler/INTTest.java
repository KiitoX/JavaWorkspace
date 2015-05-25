package com.mcmanuellp.numericalhandler;

public class INTTest
{
	public static void main(String[] args)
	{
		XInteger val = new XInteger("016x1001");
		System.out.println(val.getValue());
		System.out.println(val.getByBase(10));
		System.out.println(val.getByBase(16));
		System.out.println(val.getByBase(8));
		System.out.println(val.getByBase(2));
		System.out.println(val.getByBase(0));
	}
}
