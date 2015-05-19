package CronMail;

public class CronMail {
	
	public static void main ( String [] args ){		
		
		if ( args.length != 3){
			System.out.println("Arguments Error !");
			System.out.println("[],[Job's Name],[Mail Address]");
			System.exit(0);
		}
		
		String cmd = args[0] ;			//Job's CMD
		String jobName = args[1] ;		//Job's Name
		String mail = args[2] ;			//Alert Mail Address
		int jobStatus = -1 ;

		StartCMD sc = new StartCMD(cmd, jobName) ;
		int returnNum = sc.checkErrLogStatus();
		
		if ( returnNum == 0){
			jobStatus = sc.systemCMD() ;
		}else {
			System.out.println("Log file can not access !");
		}
		
		sc.jobReturn(jobStatus) ;
	}
}
