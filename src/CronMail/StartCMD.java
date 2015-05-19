package CronMail;

/**最後撰寫時間：2015/05/19
 *  用途:
 *  
 *  此class用來
 *  
 *  
 */

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

	private String cmdPath ;		//Scripts路徑
	private String logDir ;			//Log路徑
	private String errLogPath ;		//Log檔名
	private String jobName ;
	
	//Constructor
	public StartCMD (String cmdPath , String jobName){
		this.cmdPath = cmdPath ;
		this.jobName = jobName ;
		//取得執行程式的路徑
		String logTemp = System.getProperty("user.dir") ;
		//建立log檔路徑
		this.logDir = logTemp + "/errLog" ;
		this.errLogPath = logDir + "/" + jobName + ".log" ;	
	}
	
	//檢查是否有Error log產生
	public int checkErrLogStatus (){
		CheckCreate cc = new CheckCreate () ; //建立檢查檔案的物件
		
		int tempReturn = 0 ;					//預設回傳值為正常(0)
		
		//若資料夾或檔案檢查並新增失敗的話(回傳值不為0)，則回傳-1
		if (! (cc.dirCheck(logDir, true) == 0 ) ){
			tempReturn = -1 ;	
		}else if ( ! (cc.fileCheck(errLogPath, true) == 0 )){
			tempReturn = -1 ;
		}

		return tempReturn ;
	}
	
	//執行程式，將標準錯誤輸出至log，並回傳結果
	public int systemCMD (){
		//預設回傳值為異常
		int returnCode = -1 ;
		String [] cmdArray = new String[3] ;
		cmdArray[0] = ("/bin/sh");
    	cmdArray[1] = ("-c");
    	cmdArray[2] = (cmdPath) ;
    	Path log = Paths.get(errLogPath);

		Process proc = null ;
		String line = null ;	//log的暫存文字串
    	try {
    		
    		//執行程式
    		proc = Runtime.getRuntime().exec(cmdArray);
    		
    		//標準錯誤輸出
    		InputStream stderr = proc.getErrorStream();
    		InputStreamReader isr = new InputStreamReader(stderr);
    		BufferedReader br = new BufferedReader(isr);
    		
    		//程式執行的回傳值
    		returnCode = proc.waitFor() ;
	
    		//將剛標準錯誤讀出，寫入log檔內
			while ((line = br.readLine()) != null){
				String next = line + "\n" ;
				Files.write (log , next.getBytes(), StandardOpenOption.APPEND);
			}
			
			reNameLog () ;
			
    	}catch (Exception e ){
    		e.printStackTrace();
    		return returnCode ;
    	}

		return returnCode ;
	}
	
	//log有更新的話，就加上日期
	private void reNameLog (){	
		try {
			Path oldLog = Paths.get(errLogPath) ;
			if ( Files.size(oldLog) != 0){
				DateUtil today = new DateUtil () ;
				Path newLog = Paths.get(errLogPath + "_" + today.today("-")) ;
				
				//修改檔名
				Files.move( oldLog , newLog , StandardCopyOption.REPLACE_EXISTING );
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//將Job的結果寫入Log檔
	public void jobReturn (int returnCode){
		CheckCreate cc = new CheckCreate () ;
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
