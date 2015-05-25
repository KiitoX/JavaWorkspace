package com.mcmanuellp.util;

import java.util.Collection;

public class CompareUtils
{
	public static boolean doStringsMatch(String a, String b)
	{
		return (a == null && b == null) || !(a != null && b == null) && (a != null && a.equals(b));
	}

	public static boolean doCollectionsMatch(Collection c1, Collection c2)
	{
		boolean flag = false;
		if(!c1.isEmpty() && !c2.isEmpty())
		{
			if(c1.size() == c2.size())
			{
				for(int i = 0; i < c1.size(); i++)
				{
					if(c1.toArray()[i].equals(c2.toArray()[i])) flag = true;
					if(!flag) break;
				}
			}
		}
		return flag;
	}

	public static boolean equalsEach(Object object, Object... objects)
	{
		for(Object o : objects)
		{
			if(!object.equals(o)) return false;
		}
		return true;
	}


	public static boolean equalsNone(Object object, Object... objects)
	{
		for(Object o : objects)
		{
			if(object.equals(o)) return false;
		}
		return true;
	}

	public static boolean equalsAny(Object object, Object... objects)
	{
		for(Object o : objects)
		{
			if(object.equals(o)) return true;
		}
		return false;
	}
}
