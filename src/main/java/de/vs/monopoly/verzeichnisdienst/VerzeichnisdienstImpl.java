package de.vs.monopoly.verzeichnisdienst;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.security.cert.X509Certificate;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class VerzeichnisdienstImpl {

	public VerzeichnisdienstInterface verzeichnisdienstImplRetro;
	final String url = "https://vs-docker.informatik.haw-hamburg.de/ports/8053/services";
	
public VerzeichnisdienstImpl() {
	
	Retrofit retro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
	this.verzeichnisdienstImplRetro = retro.create(VerzeichnisdienstInterface.class);

}	

//public void accept(){ {
//	    // Create a trust manager that does not validate certificate chains
//	    TrustManager[] trustAllCerts = new TrustManager[] { 
//	      new X509TrustManager() {
//	        public X509Certificate[] getAcceptedIssuers() { 
//	          return new X509Certificate[0]; 
//	        }
//	        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
//	        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
//	    }};
//
//	    // Ignore differences between given hostname and certificate hostname
//	    HostnameVerifier hv = new HostnameVerifier() {
//	      public boolean verify(String hostname, SSLSession session) { return true; }
//	    };
//
//	    // Install the all-trusting trust manager
//	    try {
//	      SSLContext sc = SSLContext.getInstance("SSL");
//	      sc.init(null, trustAllCerts, new SecureRandom());
//	      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//	      HttpsURLConnection.setDefaultHostnameVerifier(hv);
//	    } catch (Exception e) {}
//	  }
//} 


	public static void main(String[] args) throws IOException {
		VerzeichnisdienstImpl v = new VerzeichnisdienstImpl();
		System.out.println(v.verzeichnisdienstImplRetro.holeServiceById("1").execute());
	}
}
