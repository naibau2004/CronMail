package CronMail;

/**最後撰寫時間：2015/05/19
 * 用途:
 *  
 *  此class用來檢查檔案(資料夾)是否存在，是否可讀可寫
 *  並依需求判斷是否協助建立
 *  
 */

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckCreate {
	
	private String filePath ;		//資料夾或檔案路徑
	private boolean creater ;		//判別是否建立
	
	//檢查檔案是否存存在，是否可讀可寫，依boolean值決定是否建立
	public int fileCheck (String filePath , boolean creater ){
		this.filePath = filePath ;
		this.creater = creater ;
		Path file1 = Paths.get(filePath) ;
		
		if ( ! (Files.exists(file1) ) ) {	
			if ( creater == true ){
				try {
					Files.createFile(file1) ;
					return 0 ;
				}catch (Exception e){
					return -1 ;
				}
			}else {
				return -2 ;
			}	
		}
	
		if ( ! (Files.isReadable(file1) ) ){
			return -3 ;
		}else if ( ! (Files.isWritable(file1) ) ){
			return -4 ;
		}
		return 0 ;
	}

	//檢查資料夾是否存在，是否可讀可寫，依boolean值決定是否建立
	public int dirCheck (String filePath , boolean creater ){
		this.filePath = filePath ;
		this.creater = creater ;
		Path file1 = Paths.get(filePath) ;
		
		if ( ! (Files.exists(file1) ) ) {	
			if ( creater == true ){
				try {
					Files.createDirectories(file1) ;
					return 0 ;
				}catch (Exception e){
					return -1 ;
				}
			}else {
				return -2 ;
			}
		}
		
		if ( ! (Files.isReadable(file1) ) ){
			return -3 ;
		}else if ( ! (Files.isWritable(file1) ) ){
			return -4 ;
		}
		
		return 0 ;
	}
}
