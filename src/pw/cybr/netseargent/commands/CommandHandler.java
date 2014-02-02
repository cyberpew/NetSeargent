// Handles the commands to and from shell

package pw.cybr.netseargent.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
	
	public static List<String> executeCommand(String command) {
		
		//StringBuffer output = new StringBuffer();
		List<String> stringArray = new ArrayList<String>();
		Process p;
		
		
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine())!= null) {
				stringArray.add(line.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stringArray;
	}

}
