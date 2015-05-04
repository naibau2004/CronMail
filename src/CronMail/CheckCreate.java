package CronMail;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckCreate {
	
	private String filePath ;
	private boolean creater ;
	
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
