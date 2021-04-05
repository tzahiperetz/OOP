package homework0;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Files ; 

/**
 * This main extract all the comment from a given file . 
 * extract any comment that start with '//' or wrapped by '/\*  *\/'
 *
 */

public class section2 {

	public static void main(String [] args) throws IOException {
		// Must receive <file_name> 
		if (args.length != 1 ) {
			throw new IOException("wrong number of arguments ");
		}
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		String currentLine;
		Boolean isStillComment=false;
		// Read line by line untill EOF 
		while ((currentLine = in.readLine()) != null) { 
			// check if the previous line had /* type comment 
			if(isStillComment == true) {
				// if this line has the ending */ prefix 
				if (currentLine.contains("*/")) {
					String[] arrOfStr = currentLine.split("\\*");
					if (!currentLine.startsWith("*/")) {
						System.out.println((arrOfStr[0]));
					}
					isStillComment=false;
					continue;
				}
				else {
					// if this line doesn't have the ending */ prefix 
					System.out.println(currentLine);
					continue;
				}
			}
			// looks for "//" prefix 
			if (currentLine.contains("//")) {
				String[] arrOfStr = currentLine.split("//");
				System.out.println(arrOfStr[arrOfStr.length-1]);
				continue;
			}
			// look for "/*" prefix 
			else if (currentLine.contains("/*")) {
				// current has "/*" begin and "*/" ending prefix 
				if (currentLine.contains("*/")) {
					String[] arrOfStr = currentLine.split("\\*");
					System.out.println((arrOfStr[1]));
					continue;
				}
				else {
					// current has only "/*" begin prefix 
					String[] arrOfStr = currentLine.split("\\*");
					if(arrOfStr.length > 1)
						System.out.println((arrOfStr[1]));
					isStillComment=true;
				}
			}
	    }
	}
} 






