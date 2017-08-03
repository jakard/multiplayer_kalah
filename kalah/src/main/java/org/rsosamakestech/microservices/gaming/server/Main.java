package org.rsosamakestech.microservices.gaming.server;

import java.util.logging.Logger;

/**
 * The Class Main for booth servers
 */
public class Main {

	/** The logger. */
	protected static Logger logger = Logger.getLogger(Main.class.getName());

	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		String serverName = "NO-VALUE";

		switch (args.length) {
		case 2:
			// In case you want to override the value in the *-server.yml file
			System.setProperty("server.port", args[1]);
            break;
		case 1:
			serverName = args[0].toLowerCase();
			break;

		default:
			usage();
			return;
		}

		if (serverName.equals("eureka")) {
			RegistrationServer.main(args);
		} else if (serverName.equals("game")) {
			GamingServer.main(args);
		} else {
			usage();
		}
	}

	/**
	 * Usage.
	 */
	protected static void usage() {
		logger.warning("Usage: java -jar ... <server-name> [server-port]\n where server-name is 'reg', 'registration', " + "'accounts' or 'web' and server-port > 1024");
		
		
	}

}
