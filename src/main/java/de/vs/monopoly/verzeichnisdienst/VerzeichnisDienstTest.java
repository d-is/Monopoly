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

		 
//		URL yahoo = new URL("http://vs-docker.informatik.haw-hamburg.de/ports/8053/services/1");
//
//		URLConnection uc = yahoo.openConnection();
//		uc.setRequestProperty("Authorization", "Basic YWJvNDc2OkRFMTAwNnN1ODc=");
//
//		InputStream content = (InputStream) uc.getInputStream();
//		BufferedReader in = new BufferedReader(new InputStreamReader(content));
//
//		String line;
//		while ((line = in.readLine()) != null) {
//			System.out.println(line);
//		}
//
//		in.close();
		
		System.out.println(v.getServices());
	
	
	}



	
}
