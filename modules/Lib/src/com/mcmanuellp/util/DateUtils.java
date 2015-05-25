package com.mcmanuellp.util;

public class DateUtils
{
	public enum day
	{
		SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6);

		int i;

		day(int i)
		{
			this.i = i;
		}

		public int getI()
		{
			return i;
		}

		public static day get(int i)
		{
			switch(i)
			{
				case 0:
					return SUNDAY;
				case 1:
					return MONDAY;
				case 2:
					return TUESDAY;
				case 3:
					return WEDNESDAY;
				case 4:
					return THURSDAY;
				case 5:
					return FRIDAY;
				case 6:
					return SATURDAY;
				default:
					return get(i - 7);
			}
		}
	}

	public enum month
	{
		JANUARY(0), FEBRUARY(3), MARCH(3), APRIL(6), MAY(1), JUNE(4), JULY(6), AUGUST(2), SEPTEMBER(5), OCTOBER(0), NOVEMBER(3), DECEMBER(5);

		int i;

		month(int i)
		{
			this.i = i;
		}

		public int getI()
		{
			return i;
		}
	}

	public static class date
	{
		int d;
		month month;
		int century;
		int year;

		public date(int d, month month, int century, int year)
		{
			this.d = d;
			this.month = month;
			this.century = century;
			this.year = year;
		}

		public int getDC()
		{
			return d % 7;
		}

		public int getMonthC()
		{
			return month.getI();
		}

		public int getCenturyC()
		{
			return (3-(century % 4))*2;
		}

		public int getYearC()
		{
			return (year + (year / 4)) % 7;
		}

		public int getYC()
		{
			return ((year % 4 == 0) && CompareUtils.equalsAny(month, DateUtils.month.JANUARY, DateUtils.month.FEBRUARY)) ? -1 : 0;
		}

		public day getDay()
		{
			return DateUtils.day.get((getDC() + getMonthC() + getCenturyC() + getYearC() + getYC()) % 7);
		}
	}
}
