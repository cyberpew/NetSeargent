// Get all IPs associated with a host

package pw.cybr.netseargent.commands;

import java.util.List;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class GetIPs extends ListenerAdapter {
	
	public void onMessage(MessageEvent e) throws Exception {
		if (e.getMessage().startsWith("^listips") || e.getMessage().startsWith("^getips")) {
			String[] args = e.getMessage().split(" ");
			if (args.length == 2) {
				String host = args[1];
				
				String command = "host -t a " + host;
				List<String> output = (List<String>)CommandHandler.executeCommand(command);
				
				for(String temp : output) {
					e.getBot().sendMessage(e.getChannel(), temp);
				}
				
			} else {
				e.getBot().sendMessage(e.getChannel(), "[NetSeargent] Usage: ^listip <host>");
			}
		}
	}
}
