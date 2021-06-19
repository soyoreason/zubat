package xyz.tamagawa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;

public class BOM {
	
	public enum XtdCharset {
		UTF8 ("EFBBBF"),
		UTF16be ("FEFF"),  // big endian
		UTF16le ("FFFE"),
		UTF32be ("0000FEFF"),
		UTF32le ("FFFE0000"),
		SCSU ("0EFEFF"),  // Standard Compression for Unicode
		UTF1 ("F7644C"),
		UTF7 ("2B2F76"),
		UTF_EBCDIC ("DD736673"), // legacy mainframe
		BOCU1 ("FBEE28"),  // binary compression for unicode
		GB18030 ("84319533"); // Chinese standard	
		
		final public String BOMAsHex;
		final public byte[] BOM;
		
		XtdCharset(String s) {
			this.BOMAsHex = s;
			this.BOM = DatatypeConverter.parseHexBinary(s);
		}
	}
	
	
	// ---===---
	public static Map<Integer, XtdCharset> locateBOMs(File f) throws IOException {
		byte[] dump = Files.readAllBytes(f.toPath());
		return locateBOMs(dump);
	}

	public static Map<Integer, XtdCharset> locateBOMs(byte[] data) {
		Map<Integer, XtdCharset> bomLocations = new HashMap<>();
		int i_utf8 = 0, i_utf7 = 0, i_utf1 = 0, i_scsu = 0;
		int i_utf16be = 0, i_utf16le = 0, i_utf32be = 0, i_utf32le = 0;
		int i_bocu1 = 0, i_gb18030 = 0, i_utfEbcdic = 0;
		
		for(int i = 0; i < data.length; i++) {
			byte b = data[i];
			int usByte = b & (0xff);
			System.out.println("unsignedByte__" + usByte + "__");
			if(XtdCharset.UTF8.BOM[i_utf8]==data[i]) { i_utf8++; } else { i_utf8 = 0; }
			if(XtdCharset.UTF7.BOM[i_utf7]==data[i]) { i_utf7++; } else { i_utf7 = 0; }
			if(XtdCharset.UTF1.BOM[i_utf1]==data[i]) { i_utf1++; } else { i_utf1 = 0; }
			if(XtdCharset.SCSU.BOM[i_scsu]==data[i]) { i_scsu++; } else { i_scsu = 0; }
			if(XtdCharset.UTF16be.BOM[i_utf16be]==data[i]) { i_utf16be++; } else { i_utf16be = 0; }
			if(XtdCharset.UTF16le.BOM[i_utf16le]==data[i]) { i_utf16le++; } else { i_utf16le = 0; }
			if(XtdCharset.UTF32be.BOM[i_utf32be]==data[i]) { i_utf32be++; } else { i_utf32be = 0; }
			if(XtdCharset.UTF32le.BOM[i_utf32le]==data[i]) { i_utf32le++; } else { i_utf32le = 0; }
			if(XtdCharset.BOCU1.BOM[i_bocu1]==data[i]) { i_bocu1++; } else { i_bocu1 = 0; }
			if(XtdCharset.GB18030.BOM[i_gb18030]==data[i]) { i_gb18030++; } else { i_gb18030 = 0; }
			if(XtdCharset.UTF_EBCDIC.BOM[i_utfEbcdic]==data[i]) { i_utfEbcdic++; } else { i_utfEbcdic = 0; }

			if(i_utf8==XtdCharset.UTF8.BOM.length) { bomLocations.put(i - i_utf8 + 1, XtdCharset.UTF8); i_utf8 = 0; }
			if(i_utf7==XtdCharset.UTF7.BOM.length) { bomLocations.put(i - i_utf7 + 1, XtdCharset.UTF7); i_utf7 = 0; }
			if(i_utf1==XtdCharset.UTF1.BOM.length) { bomLocations.put(i - i_utf1 + 1, XtdCharset.UTF1); i_utf1 = 0; }
			if(i_scsu==XtdCharset.SCSU.BOM.length) { bomLocations.put(i - i_scsu + 1, XtdCharset.SCSU); i_scsu = 0; }
			if(i_utf16be==XtdCharset.UTF16be.BOM.length) { bomLocations.put(i, XtdCharset.UTF16be); i_utf16be = 0; }
			if(i_utf16le==XtdCharset.UTF16le.BOM.length) { bomLocations.put(i, XtdCharset.UTF16le); i_utf16le = 0; }
			if(i_utf32be==XtdCharset.UTF32be.BOM.length) { bomLocations.put(i, XtdCharset.UTF32be); i_utf32be = 0; }
			if(i_utf32le==XtdCharset.UTF32le.BOM.length) { bomLocations.put(i, XtdCharset.UTF32le); i_utf32le = 0; }
			if(i_bocu1==XtdCharset.BOCU1.BOM.length) { bomLocations.put(i, XtdCharset.BOCU1); i_bocu1 = 0; }
			if(i_gb18030==XtdCharset.GB18030.BOM.length) {
				bomLocations.put(i, XtdCharset.GB18030); i_gb18030 = 0; } 
			if(i_utfEbcdic==XtdCharset.UTF_EBCDIC.BOM.length) {
				bomLocations.put(i, XtdCharset.UTF_EBCDIC); i_utfEbcdic = 0; }			
		}
		return bomLocations;
	}
	


}
