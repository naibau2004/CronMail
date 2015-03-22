package CronMail;

public class CronMail {
	
	public static void main ( String [] args ){
		
		StartCMD sc = new StartCMD () ;
		int x = sc.start("calc.exe", "C:\\Java-Test\\err.log");
		
	}
}
