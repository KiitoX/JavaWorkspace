package com.mcmanuellp.twodpathfinding;

import java.util.Arrays;
import java.util.Random;

public class Field
{
	final char error  = '\u0489';
	final char trueC  = '\u25A0';
	final char falseC = '\u25A1';

	int       fieldSizeSqrt;
	boolean[] field;
	int       startField;
	int       endField;

	public Field()
	{
		this(9);
	}

	public Field(int fieldSizeSqrt)
	{
		this(fieldSizeSqrt, 0, 0);
	}

	public Field(int fieldSizeSqrt, int startField, int endField)
	{
		this(fieldSizeSqrt, startField, endField, new boolean[fieldSizeSqrt * fieldSizeSqrt]);
		randomizeField();
	}

	public Field(int fieldSizeSqrt, int startField, int endField, boolean[] field)
	{
		this.fieldSizeSqrt = fieldSizeSqrt;
		this.startField = startField;
		this.endField = endField;
		if(field.length != (fieldSizeSqrt*fieldSizeSqrt))
		{
			System.out.println("Had to adjust field size; randomizing...");
			this.field = new boolean[fieldSizeSqrt*fieldSizeSqrt];
			randomizeField();
		}
		else
		{
			this.field = field;
		}
	}

	public void randomizeField()
	{
		for(int i = 0; i < field.length; i++)
		{
			field[i] = new Random().nextBoolean();
		}
	}

	public void randomizeFieldPossible()
	{
		boolean flag = true;
		while(flag)
		{
			randomizeField();
			flag = !pathFound();
		}
	}

	public boolean pathFound()
	{
		return findPath() != null;
	}

	public int[] findPath()
	{
		//TODO yay procrastination of complicated things

		boolean[][] temp = new boolean[field.length][];

		for(int i = 0; i < field.length; i++)
		{
			temp[i] = getPosSpecs(i);
		}

		System.out.println(Arrays.deepToString(temp));

		return null;
	}

	public boolean[] getPosSpecs(int pos)
	{
		boolean posF = field[pos];
										  // UP ; DOWN ; LEFT ; RIGHT; field
		boolean[] pathAble = new boolean[]{false, false, false, false, posF};

		if(!posF)
		{
			if(pos < fieldSizeSqrt)
				pathAble[0] = true;
			if(pos < field.length && pos > field.length - (fieldSizeSqrt + 1))
				pathAble[1] = true;
			if(pos % fieldSizeSqrt == 0)
				pathAble[2] = true;
			if(pos % fieldSizeSqrt == fieldSizeSqrt - 1)
				pathAble[3] = true;

			if(!pathAble[0])
				pathAble[0] = field[pos + getDirIndexDiff(Dir.UP)];
			if(!pathAble[1])
				pathAble[1] = field[pos + getDirIndexDiff(Dir.DOWN)];
			if(!pathAble[2])
				pathAble[2] = field[pos + getDirIndexDiff(Dir.LEFT)];
			if(!pathAble[3])
				pathAble[3] = field[pos + getDirIndexDiff(Dir.RIGHT)];
		}
		return pathAble;
	}

	public char getFieldChar(boolean value)
	{
		return value ? trueC : falseC;
	}

	public char getFieldChar(int index)
	{
		if(index < field.length)
			getFieldChar(field[index]);

		return error;
	}

	public void draw()
	{
		draw(false);
	}

	public void draw(boolean mode)
	{
		System.out.println(String.format("f.length: %s, sqrt(f.length): %s", field.length, fieldSizeSqrt));

		if(mode)
		{
			draw(false);
			//currently unimplemented
		}
		else
		{
			for(int i = 0; i < field.length; i++)
			{
				System.out.print(getFieldChar(field[i]));
				if(i % fieldSizeSqrt == fieldSizeSqrt - 1)
					System.out.print("\n");
			}
		}
	}

	public int getDirIndexDiff(Dir direction)
	{
		switch(direction)
		{
			case UP:
				return -fieldSizeSqrt;
			case DOWN:
				return fieldSizeSqrt;
			case LEFT:
				return -1;
			case RIGHT:
				return 1;
			default:
				return 0;
		}
	}

	public enum Dir
	{
		UP, DOWN, LEFT, RIGHT
	}
}
