package CronAlert;

/** 最後撰寫時間：2015/05/19	
 *   用途:
 *  
 *  接收使用者之Linux scipt，執行完畢後判斷是否成功，
 *  並將錯誤碼或標準輸出寫至Log中
 *  
 */

public class LinuxScript {

	String scriptPath ;
	String jobName ;
	
	public LinuxScript(String scriptPath , String jobName ) {
		
		this.scriptPath = scriptPath ;
		this.jobName = jobName ;
		
	}

	public String getScriptPath() {
		return scriptPath;
	}

	public String getJobName() {
		return jobName;
	}
	
	
	
}
