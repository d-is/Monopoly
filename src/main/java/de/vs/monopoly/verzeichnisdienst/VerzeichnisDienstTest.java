package de.vs.monopoly.verzeichnisdienst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.codec.binary.Base64;

import com.squareup.okhttp.OkHttpClient;

import spark.utils.IOUtils;



public class VerzeichnisDienstTest {
	

	public static void main(String[] args) throws IOException {
		VerzeichnisdienstImpl v = VerzeichnisdienstImpl.init();
		//SSLCertificateValidation.disable();
		
//		Authenticator.setDefault(new MyAuthenticator("abo476","DE1006bw"));
//		 InputStream in = new URL("https://vs-docker.informatik.haw-hamburg.de/ports/8053/services/1" ).openStream();
//
//		 try {
//		   System.out.println( IOUtils.toString( in ) );
//		 }
		 
		 URL yahoo = new URL("https://vs-docker.informatik.haw-hamburg.de/ports/8053/services/1");

		   String passwdstring = "abo476:PASS";
		   String encoding = new 
		          sun.misc.BASE64Encoder().encode(passwdstring.getBytes());

		   URLConnection uc = yahoo.openConnection();
		   uc.setRequestProperty("Authorization", "Basic " + encoding);

		   InputStream content = (InputStream)uc.getInputStream();
		   BufferedReader in   =   
		            new BufferedReader (new InputStreamReader (content));

		   String line;
		   while ((line = in.readLine()) != null) {
		      System.out.println (line);
		   }   

		   in.close();
		
		//System.out.println(v.getVerzeichnisdienstInterface().holeServiceById("1").execute() );
	
	
	}



	
}
