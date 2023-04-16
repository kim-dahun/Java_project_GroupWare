package test;

import java.io.IOException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Stock {

	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
		// TODO Auto-generated method stub
		// 크롤링 에러 발생 시 인증키 관련 에러를 잡아주는 코드
		
//		TrustManager[] trustAllcerts = new TrustManager[] { new X509TrustManager() {
//			
//			public X509Certificate[] getAcceptedIssuers(){
//				return null;
//			}
//
//			@Override
//			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		} };
//		
//		final SSLContext sc = SSLContext.getInstance("SSL");
//		sc.init(null, trustAllcerts, new java.security.SecureRandom());
//		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//		
//		HostnameVerifier allHostsValid = new HostnameVerifier() {
//			
//			@Override
//			public boolean verify(String arg0, SSLSession arg1) {
//				// TODO Auto-generated method stub
//				return true;
//			}
//		};
//		
//		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		String searchKeyword = "101";
		
		
		try {
			String URL = "https://translate.google.co.kr/?hl=ko&sl=auto&tl=en&text="+ URLEncoder.encode(searchKeyword,"UTF-8") + "&op=translate";
			
			Document doc = Jsoup.connect(URL).get();
			
			Elements e1 = doc.getElementsByAttributeValue("class", "ryNqvb");
			
			//Elements e1 = doc.getElementsByAttributeValue("class", "no_today");
			
			System.out.println(doc.text());
			
			
			
			//Element e2 = e1.get(0);
			
			//Elements e3 = e2.select("span");
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
