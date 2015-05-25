package com.mcmanuellp.textad_take1.play;

public class Player
{
	public static final Player player = new Player();
	public Inventory inventory;

	private Player()
	{
		this.inventory = new Inventory();
	}

	public class Inventory
	{
		public int[] slots = new int[12];

		private Inventory()
		{

		}
	}
}
