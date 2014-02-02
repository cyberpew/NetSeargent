//Pings a host/IP

package pw.cybr.netseargent.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class Ping extends ListenerAdapter {
	
	public void onMessage(MessageEvent e) throws Exception {
		if (e.getMessage().startsWith("^ping")) {
			String[] args = e.getMessage().split(" ");
			if (args.length == 3) {
				String host = args[1];
				String count = args[2];
				
				Ping obj = new Ping();
				String command = "ping -c " + count + " " + host;
				String output = CommandHandler.executeCommand(command);
				
				e.getBot().sendMessage(e.getChannel(), output);
			} else {
				e.getBot().sendMessage(e.getChannel(), "[NetSeargent] Usage: ^ping <host/ip> <times to ping>");
			}
		}
	}
	
	
}
