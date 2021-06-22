package xyz.tamagawa.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


interface BOM {
	Logger log = LoggerFactory.getLogger(BOM.class);
	
	enum XtdCharset {
		UTF8 ("EFBBBF"), // {239, 187, 191}
		UTF16be ("FEFF"), // { 254, 255 }
		UTF16le ("FFFE"), // { 255, 254 }
		UTF32be ("0000FEFF"), // { 00, 00, 254, 255 }
		UTF32le ("FFFE0000"); // { 255, 254, 00, 00 }
		
//		UTF1 ("F7644C"), // { 247, 100, 76 }
//		UTF7 ("2B2F76"), // { 43, 47, 118 }
//		UTF_EBCDIC ("DD736673"), // { 221, 115, 102, 115 } mainframe
//		BOCU1 ("FBEE28"),  // { 251, 238, 40 } binary compression for unicode
//		SCSU ("0EFEFF"), // { 14, 254, 255 } standard compression for unicode
//		GB18030 ("84319533"); // { 132, 49, 149, 51 } chinese standard
		
		byte[] BOM;
		String BOMhex;
		
		XtdCharset(String s) {
			BOMhex = "0x" + s;
			log.info("Creating enum XtdCharset: " + this.name() + "/" + BOMhex + ";");
			this.BOM = DatatypeConverter.parseHexBinary(s);
		}
	}	
	

	// ---===---
	static Map<Integer, XtdCharset> locateBOMs(File f) throws IOException {
		try {
			log.info("Searching for BOMs in file: " + f.getCanonicalPath() + ";");
			byte[] dump = Files.readAllBytes(f.toPath());
			return locateBOMs(dump);
		} catch (IOException e) {
			log.error("Error with reference to file:" + f.getCanonicalPath() + ";");
			throw e;
		}
	}
	
	static Map<Integer, XtdCharset> locateBOMs(byte[] data) {
		log.info("Searching for BOMs in " + data.length + " bytes;");
		Map<Integer, XtdCharset> bomLocations = new HashMap<>();
		int i_utf8 = 0, i_utf16be = 0, i_utf16le = 0, i_utf32be = 0, i_utf32le = 0;

		for(int i = 0; i < data.length; i++) {
			if(XtdCharset.UTF8.BOM[i_utf8]==data[i]) { i_utf8++; } else { i_utf8 = 0; }
			if(XtdCharset.UTF16be.BOM[i_utf16be]==data[i]) { i_utf16be++; } else { i_utf16be = 0; }
			if(XtdCharset.UTF16le.BOM[i_utf16le]==data[i]) { i_utf16le++; } else { i_utf16le = 0; }
			if(XtdCharset.UTF32be.BOM[i_utf32be]==data[i]) { i_utf32be++; } else { i_utf32be = 0; }
			if(XtdCharset.UTF32le.BOM[i_utf32le]==data[i]) { i_utf32le++; } else { i_utf32le = 0; }

			if(i_utf8==XtdCharset.UTF8.BOM.length) { 
				bomLocations.put(i - i_utf8 + 1, XtdCharset.UTF8); i_utf8 = 0; }
			if(i_utf16be==XtdCharset.UTF16be.BOM.length) {
				bomLocations.put(i - i_utf16be + 1, XtdCharset.UTF16be); i_utf16be = 0; }
			if(i_utf16le==XtdCharset.UTF16le.BOM.length) {
				bomLocations.put(i - i_utf16le + 1, XtdCharset.UTF16le); i_utf16le = 0; }
			if(i_utf32be==XtdCharset.UTF32be.BOM.length) {
				bomLocations.put(i - i_utf32be + 1, XtdCharset.UTF32be); i_utf32be = 0; }
			if(i_utf32le==XtdCharset.UTF32le.BOM.length) {
				bomLocations.put(i - i_utf32le + 1, XtdCharset.UTF32le); i_utf32le = 0; }	
		}
		log.debug(bomLocations.size() + " BOMs discovered;");
		return bomLocations;
	}

}
