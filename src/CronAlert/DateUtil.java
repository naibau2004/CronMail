package CronAlert;
//testDate		產生今日日期字串

import java.util.Calendar;

public class DateUtil
{
	private static Calendar cal = Calendar.getInstance() ;
	
	//取得現在的年份
	public int yearUtil ()
	{
		return cal.get( Calendar.YEAR ) ;
	}
	
	//取得現在月份
	public int monthUtil ()
	{
		int thisMonth = cal.get(Calendar.MONTH ) ;
		thisMonth += 1 ;
		
		return thisMonth ;
	}
	
	//取得今日日期
	public int dayUtil ()
	{
		return cal.get(Calendar.DAY_OF_MONTH) ;
	}

	//取得格式化後的今日日期
	public  String today( String a)		
	{
		return yearUtil() + a + monthUtil() + a + dayUtil() ;
	}
}
