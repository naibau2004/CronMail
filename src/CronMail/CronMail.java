package CronMail;

public class CronMail {
	
	public static void main ( String [] args ){
		
		String cmd = args[0] ;
		String log = args[1] ;
		
		StartCMD sc = new StartCMD (cmd,log);
		int logStatus = sc.checkErrLogStatus() ;
		
		if ( logStatus == -1){
			System.out.println("Can not create log file !");
		}else if ( logStatus == -2 ){
			System.out.println("Can not read log file !");

		}else if (logStatus == -3){
			System.out.println("Can not write log file !");
		}else{
			sc.linuxCMD();
		}
		

	}
}
