//Pings a host/IP

package pw.cybr.netseargent.commands;

import java.util.ArrayList;
import java.util.List;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class Ping extends ListenerAdapter {
	
	public void onMessage(MessageEvent e) throws Exception {
		if (e.getMessage().startsWith("^ping")) {
			String[] args = e.getMessage().split(" ");
			if (args.length == 3) {
				String host = args[1];
				String count = args[2];
				
				String command = "ping -c " + count + " " + host;
				List<String> output = (List<String>)CommandHandler.executeCommand(command);
				
				for(String temp : output) {
					e.getBot().sendMessage(e.getChannel(), temp);
				}

			} else {
				e.getBot().sendMessage(e.getChannel(), "[NetSeargent] Usage: ^ping <host/ip> <times to ping>");
			}
		}
	}
	
	
}
