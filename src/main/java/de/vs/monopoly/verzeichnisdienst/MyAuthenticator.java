package de.vs.monopoly.verzeichnisdienst;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class MyAuthenticator extends Authenticator {

	public static final String USERNAME_KEY = "username";
	public static final String PASSWORD_KEY = "password";
	private final PasswordAuthentication authentication;
	
	public MyAuthenticator(String user, String passwort) {
	    String userName = user;
	    String password = passwort;
	    if (userName == null || password == null) {
	        authentication = null;
	    } else {
	        authentication = new PasswordAuthentication(userName, password.toCharArray());
	    }
	}

	protected PasswordAuthentication getPasswordAuthentication() {
	    return authentication;
	}
}
