package CronMail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StartCMD {

	private String cmdPath ;
	private String errLogPath ;
	
	String getCmdPath() {
		return cmdPath;
	}

	String getErrLogPath() {
		return errLogPath;
	}

	public StartCMD (String cmdPath , String errLogPath){
		this.cmdPath = cmdPath ;
		this.errLogPath = errLogPath ;
	}
	
	private int checkErrLogStatus (String errLogPath){
		int logStatus = 0 ;
		File logFile = new File (errLogPath) ;
		
		if (logFile.exists() == false){
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				return -1 ;
			}
		}	
		
		if ( logFile.canRead() == false){
			return logStatus = -2 ;
		}
		
		if ( logFile.canWrite() == false){
			return logStatus = -3 ;
		}
		return 0 ;
	}
	
	public int linux (String cmdPath){
		int logStatus = checkErrLogStatus (getErrLogPath()) ;
		String [] cmdArray = new String[3] ;
		cmdArray[0] = ("/bin/sh");
    	cmdArray[1] = ("-c");
    	cmdArray[2] = (cmdPath) ;
    	
    	if (logStatus != 0){
    		return -1 ;
    	}
    	
		
		return 0;
	}
	
	
	
//	public int start (String cmdLine , String errorLogPath) {
//		
//		String [] cmdArray = new String[3] ;
//		cmdArray[0] = ("/bin/sh");
//    	cmdArray[1] = ("-c");s
//    	cmdArray[2] = (cmdLine) ;
//		Path p1 = Paths.get(errorLogPath);
//		boolean checkFile = Files.exists(p1) ;
//		
//		if ( checkFile == false ){
//			System.out.println ("log file can not access") ;
//			return -100 ;
//		}
//    	Process proc = null ;
//    	try{
//    		proc = Runtime.getRuntime().exec(cmdArray);
//			InputStream stderr = proc.getErrorStream();
//			InputStreamReader isr = new InputStreamReader(stderr);
//			BufferedReader br = new BufferedReader(isr);
//			
//			String line = null ;	
//			while ((line = br.readLine()) != null){
//				String next = line + "\n" ;
//				Files.write (p1 , line.getBytes(), StandardOpenOption.APPEND);
//			}
//			
//			File logFile = new File (errorLogPath) ;
//        	if ( logFile.length() != 0){
//        		return -11 ;
//        	}else{
//        		return 0 ;
//        	}
//				
//    	}catch (Exception e){
//    		try {
//				proc.getInputStream().close();
//				proc.getOutputStream().close();
//				proc.getErrorStream().close();
//			} catch (IOException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
//    		  		
//    		String except = e.getLocalizedMessage() + "\n" ;
//			try {
//				Files.write (p1 , except.getBytes(), StandardOpenOption.APPEND);
//				return -12 ;
//			} catch (IOException e1) {
//				e1.printStackTrace();
//				return -101 ;
//			}
//    	}
//	}
}
