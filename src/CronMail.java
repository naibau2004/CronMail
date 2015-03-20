import java.io.File;

public class CronMail {

    public static void main ( String [] args ){

    	cronMail ( args[0] , args[1] , args[2] , args[3] );

    }

    public static void cronMail (String cmdLine , String errorLogPath ,String alertTitle , String mailTo ){

    	String [] cmdArray = new String[3] ;
    	cmdArray[0] = ("/bin/sh");
    	cmdArray[1] = ("-c");
    	cmdArray[2] = (cmdLine + " 2> " + errorLogPath) ;

    	File logFile = new File (errorLogPath) ;
    	

        	try{
                Process process = Runtime.getRuntime().exec(cmdArray);
                process.waitFor();

        	}catch (Exception e){
                System.out.println("CmdLine Error");
                e.printStackTrace();
        	}

		if ( ! (logFile.length() == 0) ){
			String [] mailArray = new String [3] ;
			mailArray[0] = ("/bin/sh");
			mailArray[1] = ("-c");
			mailArray[2] = ("mail -s" + alertTitle + " " + mailTo + " < " + errorLogPath ) ;
			
			try {
				Process process = Runtime.getRuntime().exec(mailArray) ;
				process.waitFor();
			
			}catch (Exception e){
				System.out.println("mailArray Error");
				e.printStackTrace();
			}
			
			try{
				dateUtil today = new dateUtil () ;
				logFile.renameTo(new File (today.today("-") + "_" + errorLogPath));
				
			}catch (Exception e){
				System.out.println("rename Error");
				e.printStackTrace();
			}
		}
    }

	public static void  cronMail (String cmdLine , String errorLogPath , String alertTitle , String okTitle , String mailTo ){
    	String [] cmdArray = new String[3] ;
    	cmdArray[0] = ("/bin/sh");
    	cmdArray[1] = ("-c");
    	cmdArray[2] = (cmdLine + " 2> " + errorLogPath) ;
	}
}