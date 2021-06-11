package xyz.tamagawa.utils;

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
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private final URL address;
	private final Instant downloadTime;
	private final List<String> dump;
	
	public WebPage(String addressStrg) throws IOException {
		this(new URL(addressStrg));
	}
	
	public WebPage(URL address) throws IOException {
		this.address = address;
		this.dump = grabPage(address);
		this.downloadTime = Instant.now();
	}

	public URL getAddress() { return address; }
	public Instant getDownloadTime() { return downloadTime; }
	public List<String> getDump() { return Collections.unmodifiableList(dump);	}
	
	public static List<String> grabPage(URL address) throws IOException {
		List<String> dump = new ArrayList<>();
		HttpURLConnection conn = (HttpURLConnection) address.openConnection();
		BufferedReader bufRdr = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_CHARSET));
		for(;;) {
			String s = bufRdr.readLine();
			if(s == null) break;
			dump.add(s);
		}
		bufRdr.close();
		return dump;
	}

}
