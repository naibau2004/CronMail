package CronMail2;
//testDate		產生今日日期字串

import java.util.Calendar;

public class dateUtil
{
	private static Calendar cal = Calendar.getInstance() ;
	
	public int yearUtil ()
	{
		return cal.get( Calendar.YEAR ) ;
	}
	
	public int monthUtil ()
	{
		int thisMonth = cal.get(Calendar.MONTH ) ;
		thisMonth += 1 ;
		
		return thisMonth ;
	}
	
	public int dayUtil ()
	{
		return cal.get(Calendar.DAY_OF_MONTH) ;
	}

	public  String today( String a)		
	{
		return yearUtil() + a + monthUtil() + a + dayUtil() ;
	}
}
