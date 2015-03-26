package CronMail;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class StartCMD {

	private String cmdPath ;
	private String errLogPath ;
	private int logStatus ;
	
	public StartCMD (String cmdPath , String errLogPath){
		this.cmdPath = cmdPath ;
		this.errLogPath = errLogPath ;
	}
	
	public int checkErrLogStatus (){
		Path logFile = Paths.get(errLogPath) ;
		
		if (Files.exists(logFile) == false){
			try {
				Files.createFile(logFile);
			} catch (IOException e) {
				return -1 ;
			}
		}	
		
		if ( Files.isReadable(logFile) == false){
			return logStatus = -2 ;
		}
		
		if ( Files.isWritable(logFile) == false){
			return logStatus = -3 ;
		}
		return logStatus ;
	}
	
	public int linuxCMD (){
		String [] cmdArray = new String[3] ;
		cmdArray[0] = ("/bin/sh");
    	cmdArray[1] = ("-c");
    	cmdArray[2] = (cmdPath) ;
    	Path p1 = Paths.get(errLogPath);
    	
		Process proc = null ;
		String line = null ;
    	try {
    		proc = Runtime.getRuntime().exec(cmdArray);
    		InputStream stderr = proc.getErrorStream();
    		InputStreamReader isr = new InputStreamReader(stderr);
    		BufferedReader br = new BufferedReader(isr);
    		
			while ((line = br.readLine()) != null){
				String next = line + "\n" ;
				Files.write (p1 , next.getBytes(), StandardOpenOption.APPEND);
			}
				
    	}catch (Exception e ){
    		e.printStackTrace();
    		return -1 ;
    	}
    	
		return 0;
	}
	
	
}
