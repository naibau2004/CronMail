package CronAlert;

/**最後撰寫時間：2015/05/19
 *  用途:
 *  Main Class
 */

public class CronAlert {

	public static void main (String [] args ){
		
		String jobName = args[0] ;
		String scriptPath = args[1] ;
		String mailAddr = args[2] ;
		
		if ( args.length != 3){
			System.out.println("Arguments Error !");
			System.out.println("java -jar CronAlert [Job's Name] [Scripts Path] [Mail Address]");
			System.exit(0);
		}
		
		
		
	}
}
