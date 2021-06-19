package xyz.tamagawa.utils;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class BasicUtils {
//	private static final Logger log = LoggerFactory.getLogger(BasicUtils.class);

	// ---===---
	public static void pause(int millisec) throws InterruptedException {
//		log.info("pausing for millisec: {}", millisec);
		Thread.sleep(millisec);
	}

	public static void pause(int minWaitTimeMillis, int maxWaitTimeMillis) throws InterruptedException {
//		log.info("randomizing pause time;");
//		log.debug("randomizing pause time between " + minWaitTimeMillis + "-millisec and " + maxWaitTimeMillis + "-millisec:");
		Random rndm = new Random(System.currentTimeMillis());
		int waitTimeMillis = (maxWaitTimeMillis - minWaitTimeMillis);
		waitTimeMillis = rndm.nextInt(waitTimeMillis);
		waitTimeMillis += minWaitTimeMillis;
		BasicUtils.pause(waitTimeMillis);
	}
	
	// ---===---	
	public static <E> boolean writeToTextFile(String fileName, List<E> content) throws FileNotFoundException, IOException {
//		if(fileName==null) log.error("null reference detected;");
		File file = new File(fileName);
		return BasicUtils.writeToTextFile(file, content);
	}
	
	public static <E> boolean writeToTextFile(File file, List<E> content) throws FileNotFoundException, IOException {
//		if(file == null) log.error("null reference detected;");
//		if(content == null) log.error("null reference detected;");		
//		log.info("writing data with to {}", file.getCanonicalPath());
		
//		if(file.isDirectory()) {
//			log.error("directory, not file, ref given: {}", file.getCanonicalPath());
//			return false;
//		}
//		if(file.exists()) {
//			log.error("file already exists: {}", file.getCanonicalPath());
//			return false;
//		}
//		if(!file.createNewFile()) {
//			log.error("does not have write permission in dir, or dir does not exist: {}", file.getCanonicalPath());
//			return false;
//		}
		OutputStream outStrm = new FileOutputStream(file);
		BufferedWriter bufWtr = new BufferedWriter(new OutputStreamWriter(outStrm));
		for(int i = 0; i < content.size(); i++) {
			E element = content.get(i);
			bufWtr.write(element.toString());
			if(i < (content.size() - 1)) bufWtr.newLine();
		}
		bufWtr.close();
		return true;
	}
	
	// ---===---
//	public static byte[] readFileAsBytes(String name) throws Exception {
//		File f = new File(name);
//		InputStream inStrm = Base64.getDecoder().wrap(new FileInputStream(f));
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] word = new byte[8];
//		
//		for(;;) {
//			int bytesRead = inStrm.read(word);
//			System.out.println("___bytesRead = ___" + bytesRead + "___");
//			if(bytesRead==-1) break;
//			baos.write(word);
//		}
//		baos.close();
//		inStrm.close();
//		
//		byte[] data = baos.toByteArray();
//		return data;
//	}

	// ---===---
	public static boolean isJpCharsOrPunc(char c) {
//		log.info("checking if is a jp character or jp punctuation;");
		if(Character.UnicodeBlock.of(c)==Character.UnicodeBlock.HIRAGANA) return true;
		if(Character.UnicodeBlock.of(c)==Character.UnicodeBlock.KATAKANA) return true;
		if(Character.UnicodeBlock.of(c)==Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) return true;
		if(Character.UnicodeBlock.of(c)==Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) return true;
		if(Character.UnicodeBlock.of(c)==Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) return true;
		return false;
	}
	
	
}
