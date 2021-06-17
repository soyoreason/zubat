package xyz.tamagawa.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BOM {
	public enum XtdCharset {
		UTF8, UTF16be, UTF16le, UTF32be, UTF32le, UTF7, UTF1,
		UTF_EBCDIC, SCSU, BOCU1, GB18030_CHINA
	}
	static byte[] UTF32be    = new byte[] {(byte) 0x00, (byte) 0x00, (byte) 0xFE, (byte) 0xFF }; // big endian
	static byte[] SCSU       = new byte[] {(byte) 0x0E, (byte) 0xFE, (byte) 0xFF };  // Standard Compression for Unicode
	static byte[] UTF7       = new byte[] {(byte) 0x2B, (byte) 0x2F, (byte) 0x76 };
	static byte[] GB18030    = new byte[] {(byte) 0x84, (byte) 0x31, (byte) 0x95, (byte) 0x33 }; // Chinese standard	
	static byte[] UTF_EBCDIC = new byte[] {(byte) 0xDD, (byte) 0x73, (byte) 0x66, (byte) 0x73 };  // legacy mainframe
	static byte[] UTF8       = new byte[] {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
	static byte[] UTF1       = new byte[] {(byte) 0xF7, (byte) 0x64, (byte) 0x4C };
	static byte[] BOCU1      = new byte[] {(byte) 0xFB, (byte) 0xEE, (byte) 0x28 };  // binary compression for unicode
	static byte[] UTF16be    = new byte[] {(byte) 0xFE, (byte) 0xFF };	
	static byte[] UTF16le    = new byte[] {(byte) 0xFF, (byte) 0xFE };
	static byte[] UTF32le    = new byte[] {(byte) 0xFF, (byte) 0xFE, (byte) 0x00, (byte) 0x00 };
	
	
	// ---===---
	public static int[] locateByteOrderMarks(File f) throws IOException {
		byte[] dump = Files.readAllBytes(f.toPath());
		return locateByteOrderMarks(dump);
	}

	public static int[] locateByteOrderMarks(byte[] data) {
		List<Integer> bomLocations = new ArrayList<>();
		int i_utf8 = 0; int i_utf16be = 0; int i_utf16le = 0; int i_utf32be = 0;
		int i_utf32le = 0; int i_utf7 = 0; int i_utf1 = 0;
		int i_utfEbcdic = 0; int i_scsu = 0; int i_bocu1 = 0; int i_gb18030 = 0;
		
		for(int i = 0; i < data.length; i++) {
			byte b = (byte) data[i];
			if(UTF8[i_utf8]==b) { i_utf8++;	} else { i_utf8 = 0; }
			if(UTF16be[i_utf16be]==b) { i_utf16be++; } else { i_utf16be = 0; }
			if(UTF16le[i_utf16le]==b) { i_utf16le++; } else { i_utf16le = 0; }
			if(UTF32be[i_utf32be]==b) { i_utf32be++; } else { i_utf32be = 0; }
			if(UTF32le[i_utf32le]==b) { i_utf32le++; } else { i_utf32le = 0; }
			if(UTF7[i_utf7]==b) { i_utf7++; } else { i_utf7 = 0; }
			if(UTF1[i_utf1]==b) { i_utf1++; } else { i_utf1 = 0; }
			if(UTF_EBCDIC[i_utfEbcdic]==b) { i_utfEbcdic++; } else { i_utfEbcdic = 0; }
			if(SCSU[i_scsu]==b) { i_scsu++; } else { i_scsu = 0; }
			if(BOCU1[i_bocu1]==b) { i_bocu1++; } else { i_bocu1 = 0; }
			if(GB18030[i_gb18030]==b) { i_gb18030++; } else { i_gb18030 = 0; }

			if(i_scsu==SCSU.length) { bomLocations.add(i - i_scsu); i_scsu = 0; }
			if(i_bocu1==BOCU1.length) { bomLocations.add(i - i_bocu1); i_bocu1 = 0; }
			if(i_utf7==UTF7.length) { bomLocations.add(i - i_utf7); i_utf7 = 0; }
			if(i_utf1==UTF1.length) { bomLocations.add(i - i_utf1); i_utf1 = 0; }
			if(i_gb18030==GB18030.length) { bomLocations.add(i - i_gb18030); i_gb18030 = 0; } 
			if(i_utfEbcdic==UTF_EBCDIC.length) { bomLocations.add(i - i_utfEbcdic); i_utfEbcdic = 0; }
			if(i_utf8==UTF8.length) { bomLocations.add(i - i_utf8); i_utf8 = 0; }
			if(i_utf16be==UTF16be.length) { bomLocations.add(i - i_utf16be); i_utf16be = 0; }
			if(i_utf16le==UTF16le.length) { bomLocations.add(i - i_utf16le); i_utf16le = 0; }
			if(i_utf32be==UTF32be.length) { bomLocations.add(i - i_utf32be); i_utf32be = 0; }
			if(i_utf32le==UTF32le.length) { bomLocations.add(i - i_utf32le); i_utf32le = 0; }
		}
		int[] boms = new int[bomLocations.size()];
		for(Integer i = 0; i < bomLocations.size(); i++) {
			boms[i] = bomLocations.get(i);
		}		
		return boms;
	}
	

	// ---===---
	public static XtdCharset detectCharsetWithByteOrderMark(File f) throws IOException {
		byte[] dump = Files.readAllBytes(f.toPath());
		return BOM.detectCharsetWithByteOrderMark(dump);
	}
	
	public static XtdCharset detectCharsetWithByteOrderMark(byte[] data) {
		XtdCharset charset = null;
		int i_utf8 = 0; int i_utf16be = 0; int i_utf16le = 0; int i_utf32be = 0;
		int i_utf32le = 0; int i_utf7 = 0; int i_utf1 = 0;
		int i_utfEbcdic = 0; int i_scsu = 0; int i_bocu1 = 0; int i_gb18030 = 0;
		
		for(int i = 0; i < data.length; i++) {
			byte b = (byte) data[i];
			if(UTF8[i_utf8]==b) { i_utf8++;	} else { i_utf8 = 0; }
			if(UTF16be[i_utf16be]==b) { i_utf16be++; } else { i_utf16be = 0; }
			if(UTF16le[i_utf16le]==b) { i_utf16le++; } else { i_utf16le = 0; }
			if(UTF32be[i_utf32be]==b) { i_utf32be++; } else { i_utf32be = 0; }
			if(UTF32le[i_utf32le]==b) { i_utf32le++; } else { i_utf32le = 0; }
			if(UTF7[i_utf7]==b) { i_utf7++; } else { i_utf7 = 0; }
			if(UTF1[i_utf1]==b) { i_utf1++; } else { i_utf1 = 0; }
			if(UTF_EBCDIC[i_utfEbcdic]==b) { i_utfEbcdic++; } else { i_utfEbcdic = 0; }
			if(SCSU[i_scsu]==b) { i_scsu++; } else { i_scsu = 0; }
			if(BOCU1[i_bocu1]==b) { i_bocu1++; } else { i_bocu1 = 0; }
			if(GB18030[i_gb18030]==b) { i_gb18030++; } else { i_gb18030 = 0; }

			if(i_scsu==SCSU.length) { charset = XtdCharset.SCSU; i_scsu = 0; }
			if(i_bocu1==BOCU1.length) { charset = XtdCharset.BOCU1; i_bocu1 = 0; }
			if(i_utf7==UTF7.length) { charset = XtdCharset.UTF7; i_utf7 = 0; }
			if(i_utf1==UTF1.length) { charset = XtdCharset.UTF1; i_utf1 = 0; }
			if(i_gb18030==GB18030.length) { charset = XtdCharset.GB18030_CHINA; i_gb18030 = 0; }
			if(i_utfEbcdic==UTF_EBCDIC.length) { charset = XtdCharset.UTF_EBCDIC; i_utfEbcdic = 0; }
			if(i_utf8==UTF8.length) { charset = XtdCharset.UTF8; i_utf8 = 0; }
			if(i_utf16be==UTF16be.length) { charset = XtdCharset.UTF16be; i_utf16be = 0; }
			if(i_utf16le==UTF16le.length) { charset = XtdCharset.UTF16le; i_utf16le = 0; }
			if(i_utf32be==UTF32be.length) { charset = XtdCharset.UTF32be; i_utf32be = 0; }
			if(i_utf32le==UTF32le.length) { charset = XtdCharset.UTF32le; i_utf32le = 0; }
		}
		
		return charset;
	}

}
