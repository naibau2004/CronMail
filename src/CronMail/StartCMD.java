package CronMail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class StartCMD {

	private String cmdPath ;
	private String logDir ;
	private String errLogPath ;
	private String jobName ;
	
	public StartCMD (String cmdPath , String jobName){
		this.cmdPath = cmdPath ;
		this.jobName = jobName ;
		String logTemp = System.getProperty("user.dir") ;
		this.logDir = logTemp + "/errLog" ;
		this.errLogPath = logDir + "/" + jobName + ".log" ;	
	}
	
	public int checkErrLogStatus (){
		checkCreate cc = new checkCreate () ;
		int tempReturn = 0 ;
		
		if (! (cc.dirCheck(logDir, true) == 0 ) ){
			tempReturn = -1 ;	
		}else if ( ! (cc.fileCheck(errLogPath, true) == 0 )){
			tempReturn = -1 ;
		}

		return tempReturn ;
	}
	
	public int systemCMD (){
		int returnCode = -1 ;
		String [] cmdArray = new String[3] ;
		cmdArray[0] = ("/bin/sh");
    	cmdArray[1] = ("-c");
    	cmdArray[2] = (cmdPath) ;
    	Path log = Paths.get(errLogPath);

		Process proc = null ;
		String line = null ;
    	try {
    		proc = Runtime.getRuntime().exec(cmdArray);
    		InputStream stderr = proc.getErrorStream();
    		InputStreamReader isr = new InputStreamReader(stderr);
    		BufferedReader br = new BufferedReader(isr);
	
			while ((line = br.readLine()) != null){
				String next = line + "\n" ;
				Files.write (log , next.getBytes(), StandardOpenOption.APPEND);
			}
			
			if (Files.size(log) == 0 ){
				returnCode = 0 ;
			}
			
			reNameLog () ;
			
    	}catch (Exception e ){
    		e.printStackTrace();
    		return returnCode ;
    	}

		return returnCode ;
	}
	
	private void reNameLog (){	
		try {
			Path oldLog = Paths.get(errLogPath) ;	
			if ( Files.size(oldLog) != 0){
				DateUtil today = new DateUtil () ;
				Path newLog = Paths.get(errLogPath + "_" + today.today("-")) ;
				Files.move( oldLog , newLog , StandardCopyOption.REPLACE_EXISTING );
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void jobReturn (int returnCode){
		checkCreate cc = new checkCreate () ;
		String jobListPath = logDir + "/" + "jobList" ;
		Path pathTemp = Paths.get(jobListPath) ;
		String okStatus = jobName + " : OK " + "\n" ;
		String errStatus = jobName + " : error " + "\n" ;
		
		if ( ! (cc.fileCheck(jobListPath, true) == 0) ){
			System.out.println("can not create jobList !");
		}else{
			if ( returnCode == 0){
				try {
					Files.write(pathTemp, okStatus.getBytes() , StandardOpenOption.APPEND );
				}catch (Exception e){
					e.printStackTrace();
				}
			}else{
				try {
					Files.write(pathTemp, errStatus.getBytes() , StandardOpenOption.APPEND );
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
