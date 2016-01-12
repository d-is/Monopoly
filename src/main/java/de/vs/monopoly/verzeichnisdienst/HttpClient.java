package de.vs.monopoly.verzeichnisdienst;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;

public class HttpClient {
	public static void main(String[] args) throws IOException {

		try {

			URL url = new URL("http://vs-docker.informatik.haw-hamburg.de/ports/8053/services");

			Base64 b = new Base64();

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
		//connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic YWJvNDc2OkRFMTAwNnN1ODc=");

			InputStream content = (InputStream) connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
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
