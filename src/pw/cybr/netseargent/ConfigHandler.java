package pw.cybr.netseargent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {
	
	public static Properties config = new Properties();
	
	// Begin setting the IRC bots identity and misc
	static String nickname; // IRC nickname
	static String username; // IRC username
	static String nickserv; // Nickserv identify password
	public static String[] botadmin; // List of users with botadmin privilege
	static int delay; // Delay between messages to avoid flood
	
	// Begin setting server that IRC bot connects to
	static String server; // Server to connect to
	static int port; // Port of the IRC server
	static boolean SSL; // SSL enabled or not (true/false) 
	static String serverpassword; // Password to the IRC server if required (optional)
	static String[] channels; // List of channels to join on connect
	
	
	// Function serves to parse the configuration upon bot startup
	public static void parseConfig() throws FileNotFoundException, IOException {
		config.load(new FileInputStream("config")); // Load configuration file
		nickname = config.getProperty("nickname");
		username = config.getProperty("username");
		nickserv = config.getProperty("nickserv");
		botadmin = config.getProperty("botadmin").split(",");
		delay = Integer.parseInt(config.getProperty("delay"));
		server = config.getProperty("server");
		port = Integer.parseInt(config.getProperty("port"));
		SSL = Boolean.parseBoolean(config.getProperty("SSL"));
		serverpassword = config.getProperty("serverpassword");
		channels = config.getProperty("channels").split(",");
	}
}
