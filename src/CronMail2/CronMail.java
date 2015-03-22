package CronMail2;
import java.io.File;

public class CronMail {

	private static String cmdLine ;
	private static String errorLogPath ;
	private static String jobName ;
	private static String mailTo ;
	
    public static void main ( String [] args ){
  		
    	if ( args.length == 4 ){
    		
    		cmdLine = args[0] ;
    		errorLogPath = args[1] ;
    		jobName = args[2] ;
    		mailTo = args[3] ;
    		
        	cmdStart (cmdLine , errorLogPath );
        	File logFile = new File (errorLogPath) ;
        	
        	if ( (logFile.exists()) && !(logFile.length() == 0) ){
        		sendAlert () ;
        	}else{
        		sendOK() ;
        	}
        	
    	}else{
    		System.out.println("Argment Error !");
    	}
    }

    private static void cmdStart (String cmdLine , String errorLogPath ){

    	String [] cmdArray = new String[3] ;
    	cmdArray[0] = ("/bin/sh");
    	cmdArray[1] = ("-c");
    	cmdArray[2] = (cmdLine + " 2> " + errorLogPath) ;
    
        	try{
                Process process = Runtime.getRuntime().exec(cmdArray);
                process.waitFor();

        	}catch (Exception e){
                System.out.println("CmdLine Error");
                e.printStackTrace();
        	}
    }

	private static void sendAlert (){
		String jobStatus = "\"" + jobName + " : Error!\"" ;
	
		String [] mailArray = new String [3] ;
		mailArray[0] = ("/bin/sh");
		mailArray[1] = ("-c");
		mailArray[2] = ("mail -s" + jobStatus + " " + mailTo + " < " + errorLogPath ) ;
		
		try {
			Process process = Runtime.getRuntime().exec(mailArray) ;
		
		}catch (Exception e){
			System.out.println("mailAlertArray Error");
			e.printStackTrace();
		}
		
		try{
			dateUtil today = new dateUtil () ;
			File logFile = new File (errorLogPath) ;
			logFile.renameTo(new File (logFile.getParent() + "/" + today.today("-") + "_" + logFile.getName()));
			
		}catch (Exception e){
			System.out.println("rename Error");
			e.printStackTrace();
		}
	}
	
	private static void sendOK (){
		String jobStatus = "\"" + jobName + " : OK!\"" ;
		
		String [] mailArray = new String [3] ;
		mailArray[0] = ("/bin/sh");
		mailArray[1] = ("-c");
		mailArray[2] = ("mail -s" + jobStatus + " " + mailTo ) ;
		
		try {
			Process process = Runtime.getRuntime().exec(mailArray) ;
			
		}catch (Exception e){
			System.out.println("mailOKArray Error");
			e.printStackTrace();
		}
	}
}