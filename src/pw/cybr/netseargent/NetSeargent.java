package pw.cybr.netseargent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;

import pw.cybr.netseargent.commands.*;

public class NetSeargent {
	
	public static PircBotX bot = new PircBotX();
	
	public static void main(String[] args) throws Exception {
		//Attempt to load properties file
		try {
			ConfigHandler.parseConfig(); // Parse the configuration file
		} catch (FileNotFoundException ex) { // If the file does not exist, lets generate one with some defaults
			ConfigHandler.config.setProperty("nickname", "NetSeargent");
			ConfigHandler.config.setProperty("username", "SGT");
			ConfigHandler.config.setProperty("nickserv", "");
			ConfigHandler.config.setProperty("botadmin", "Foo,Bar");
			ConfigHandler.config.setProperty("delay", "1000");
			ConfigHandler.config.setProperty("server", "foo.net");
			ConfigHandler.config.setProperty("port", "6667");
			ConfigHandler.config.setProperty("SSL", "false");
			ConfigHandler.config.setProperty("serverpassword", "");
			ConfigHandler.config.setProperty("channels", "#Foo,#Bar");
			
			ConfigHandler.config.store(new FileOutputStream("config"), null); // Store default data if the file does not exist
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("[NetSeargent] Initializing... ");
		
		// Begin initialization of bot
		bot.setAutoNickChange(true); // Make the bot change nickname if the requested nickname is taken upon join
		bot.setVersion("NetSeargent - Network Assistant"); // Set the VERSION/real name of bot
		bot.setLogin(ConfigHandler.username); // Set the username of the bot
		bot.setName(ConfigHandler.nickname); // Set the nickname of the bot
		bot.identify(ConfigHandler.nickserv); // Identify bot with nickserv
		bot.setVerbose(false); // Debugging mode true/false (developers only)
		
		// Begin the connection to IRC server
		if (ConfigHandler.SSL && !ConfigHandler.serverpassword.isEmpty()) {
			bot.connect(ConfigHandler.server, ConfigHandler.port, ConfigHandler.serverpassword, new UtilSSLSocketFactory().trustAllCertificates());
		} else if (ConfigHandler.SSL && ConfigHandler.serverpassword.isEmpty()) {
			bot.connect(ConfigHandler.server, ConfigHandler.port, new UtilSSLSocketFactory().trustAllCertificates());
		} else {
			bot.connect(ConfigHandler.server, ConfigHandler.port);
		}
		bot.setAutoReconnect(true); // Automagically re-connect to the IRC server if disconnected
		bot.setAutoReconnectChannels(true); // Automagically re-join IRC channels upon disconnect
		
		bot.setMessageDelay(ConfigHandler.delay); // Set the delay between messages to avoid IRC flood
		
		loadChannels(); // Connect to the channels stated in the configuration file
		loadListeners(); // Load listeners (such as commands/features of the bot)
		
		System.out.print("[NetSeargent] Connected to " + ConfigHandler.server + " as " + ConfigHandler.nickname);
	}
	
	// Handles joining to auto-connect channels upon connection
	public static void loadChannels() throws Exception {
		for (int i = 0; i < ConfigHandler.channels.length; i++) {
			bot.joinChannel(ConfigHandler.channels[i]);
		}
	}
	
	public static void loadListeners() throws Exception {
		//TODO: Declare command modules here
		bot.getListenerManager().addListener(new Ping());
		bot.getListenerManager().addListener(new GetIPs());
	}
}
