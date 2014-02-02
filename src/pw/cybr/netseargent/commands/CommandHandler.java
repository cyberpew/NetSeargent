// Handles the commands to and from shell

package pw.cybr.netseargent.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandHandler {
	
	public static String executeCommand(String command) {
		
		StringBuffer output = new StringBuffer();
		
		Process p;
		
		
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return output.toString();
	}

}
