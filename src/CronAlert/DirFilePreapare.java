package CronAlert;

/** 最後撰寫時間：2015/05/19
 *  用途:
 *  檢查檔案或資料夾是否存在，依參數值決定是否自動建立
 *  或回傳Error值
 *  
 */

public class DirFilePreapare {

	private String filePath ;
	private boolean autoCarete ;
	
	public String getFilePath() {
		return filePath;
	}
	
	public boolean isAutoCarete() {
		return autoCarete;
	}

	public DirFilePreapare(String filePath , boolean autoCreate) {
		
		this.filePath = filePath;
		this.autoCarete = autoCreate;
		
	}
	
	public int dirCrete (String filePath){
		return 0 ;
	}
	
	public int fileCreate (String dirCreate){
	
		return 0 ;
	}
	
	
}
