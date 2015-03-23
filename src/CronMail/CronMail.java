package CronMail;

public class CronMail {
	
	public static void main ( String [] args ){
		
		StartCMD sc = new StartCMD () ;
		sc.start(args[0], args[1]);
	}
}
