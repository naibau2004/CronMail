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
		Path dir = Paths.get(logDir) ;
		if ( ! (Files.exists(dir) ) ){
			try {
				
				Files.createDirectories(dir) ;
			}catch (Exception e){
				System.out.println("log Directory can not create !");
				return -1 ;
			}
		}else if ( ! (Files.isDirectory(dir) ) ){
			System.out.println("Log Directory name be used !");
			return -1 ;
		}
		
		Path log = Paths.get(errLogPath) ;
		if ( ! (Files.exists(log) ) ) {
			try {
				Files.createFile(log) ;
				
			}catch (Exception e){
				System.out.println("Log File can not create !");
				return -1 ;
			}
		}else if ( ! (Files.isReadable(log) ) ){
			System.out.println("Log File not readable !" );
			return -1 ;
		}else if ( ! (Files.isWritable(log) ) ){
			System.out.println("Log File not writable !" );
			return -1 ;
		}

		return 0 ;
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
