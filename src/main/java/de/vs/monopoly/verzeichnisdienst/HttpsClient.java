package de.vs.monopoly.verzeichnisdienst;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;

public class HttpsClient {
	public static void main(String[] args) throws IOException {

		String passwdstring = "abo476:password";
		String encoding = new sun.misc.BASE64Encoder().encode(passwdstring.getBytes());

		String httpsURL = "https://vs-docker.informatik.haw-hamburg.de/ports/8053/services/1";
		URL myurl = new URL(httpsURL);
		HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
		con.setAllowUserInteraction(true);
		con.setRequestProperty("Authorization", "Basic"+ encoding);
		InputStream ins = con.getInputStream();

		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader in = new BufferedReader(isr);

		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}

		in.close();

	}
}
//
//
//
// URLConnection uc = yahoo.openConnection();
// uc.setRequestProperty("Authorization", "Basic " + encoding);
//
// InputStream content = (InputStream)uc.getInputStream();
// BufferedReader in =
// new BufferedReader (new InputStreamReader (content));
//
// String line;
// while ((line = in.readLine()) != null) {
// System.out.println (line);
// }
//
// in.close();
