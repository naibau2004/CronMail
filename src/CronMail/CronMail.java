package CronMail;

public class CronMail {
	
	public static void main ( String [] args ){		
		if ( args.length != 3){
			System.out.println("Arguments Error !");
			System.exit(0);
		}
		
		String cmd ;
		String jobName ;
		String mail ;
		int jobStatus ;
		
		cmd = args[0] ;
		jobName = args[1] ;
		mail = args[2] ;
		
		StartCMD sc = new StartCMD(cmd, jobName) ;
		int returnNum = sc.checkErrLogStatus();
		
		if ( returnNum == 0){
			jobStatus = sc.linuxCMD() ;
		}else {
			System.out.println("Log file can not access !");
		}
	}
}
