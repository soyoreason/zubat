package com.tamagawa.utils;

public interface StringConst {
	// --- byte order marks ---	
	String BOM_utf8 = new String(new int[] { 0xEFBBBF }, 0, 1);
	String BOM_utf16be = new String(new int[] { 0xFEFF }, 0, 1);
	String BOM_utf16le = new String(new int[] { 0xFFFE }, 0, 1);
	

//	public static String JP_CONTINUE_READING = "続きを読む";
//	public static String JP_SANKEI_TITLE = " - 産経ニュース";
}
