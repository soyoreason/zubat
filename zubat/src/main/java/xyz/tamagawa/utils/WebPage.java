package xyz.tamagawa.utils;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebPage {
//	private static final Logger log = LoggerFactory.getLogger(WebPage.class);
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private final URL address;
	private final Instant downloadTime;
	private final List<String> dump;
	
	public WebPage(String addressStrg) throws IOException {
		this(new URL(addressStrg));
	}
	
	public WebPage(URL address) throws IOException {
//		log.info("getting text copy of webpage {}", address.getPath());
		this.address = address;
		this.dump = grabPage(address);
		this.downloadTime = Instant.now();
	}

	// ---===---
	public URL getAddress() { return address; }
	public Instant getDownloadTime() { return downloadTime; }
	public List<String> getDump() { return Collections.unmodifiableList(dump);	}
	
	// ---===---
	public static List<String> grabPage(URL address) throws IOException {
//		log.info("downloading {}", address.getPath());
		List<String> dump = new ArrayList<>();
		HttpURLConnection conn = (HttpURLConnection) address.openConnection();
		InputStreamReader strmRdr = new InputStreamReader(conn.getInputStream());
//		log.debug("http response encoded as {}", strmRdr.getEncoding());
		BufferedReader bufRdr = new BufferedReader(strmRdr);
		for(;;) {
			String s = bufRdr.readLine();
			if(s == null) break;
			dump.add(s);
		}
		bufRdr.close();
//		log.debug("lines downloaded {}", dump.size());
		return dump;
	}
	
	

	@Test(priority=1, description="first desc is as follows.")
	void testGetAddress() {
		try {
			String addrStrg = "http://www.google.com";
			URL url = new URL(addrStrg);
		} catch(Exception e) { e.printStackTrace(); }
	}

}
