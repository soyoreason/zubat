package com.tamagawa.utils;

public interface JpChars {
	// --- punctuation ---
	String DBLWIDE_PERIOD = new String(new int[] { 0x3002 }, 0, 1); // "。"
	String DBLWIDE_SPACEBAR = new String(new int[] { 0x3000 }, 0, 1); // "　"
	String DBLWIDE_LEFT_PAREN = new String(new int[] { 0xFF08 }, 0, 1); // "（"
	String DBLWIDE_RIGHT_PAREN = new String(new int[] { 0xFF09 }, 0, 1); // "）"
	String DBLWIDE_TOPLEFT_BRACKET = new String(new int[] { 0x300E }, 0, 1); // "「"
	String DBLWIDE_BTMRIGHT_BRACKET = new String(new int[] { 0x300D }, 0, 1); // "」"
	int kata_prolonged_vowel = 0x30FC; // _ー_
	String DBLWIDE_DASH = new String(new int[] { kata_prolonged_vowel }, 0, 1); // _ー_

	// --- kana ---
	int hira_a = 0x3042, hira_i = 0x3044, hira_u = 0x3046, hira_e = 0x3048, hira_o = 0x3050;
	int hira_ka = 0x304B, hira_ki = 0x304D, hira_ku = 0x304F, hira_ke = 0x3051, hira_ko = 0x3053;
	int hira_sa = 0x3055, hira_shi = 0x3057, hira_su = 0x3059, hira_se = 0x305B, hira_so = 0x305D;
	int hira_ta = 0x305F, hira_chi = 0x3061, hira_tsu = 0x3064, hira_te = 0x3066, hira_to = 0x3068;
	int hira_da = 0x3060, hira_di = 0x3062, hira_dsu = 0x3065, hira_de = 0x3067, hira_do = 0x3069;
	int hira_na = 0x306A, hira_ni = 0x306B, hira_nu = 0x306C, hira_ne = 0x306D, hira_no = 0x306E;
	int hira_ma = 0x307E, hira_mi = 0x307F, hira_mu = 0x3080, hira_me = 0x3081, hira_mo = 0x3082;
	int hira_ya = 0x3084, hira_yu = 0x3086, hira_yo = 0x3088;
	int hira_ra = 0x3089, hira_ri = 0x308A, hira_ru = 0x308B, hira_re = 0x308C, hira_ro = 0x308D;
	int hira_wa = 0x308F, hira_wo = 0x3092, hira_n = 0x3093;
	int hira_small_ya = 0x3083, hira_small_yu = 0x3085, hira_small_yo = 0x3087;
	int hira_small_tte = 0x3063;
	
	int kata_a = 0x30A2, kata_i = 0x30A4, kata_u = 0x30A6, kata_e = 0x30A8, kata_o = 0x30AA;
	int kata_ka = 0x30AB, kata_ki = 0x30AD, kata_ku = 0x30AF, kata_ke = 0x30B1, kata_ko = 0x30B3;
	int kata_sa = 0x30B5, kata_shi = 0x30B7, kata_su = 0x30B9, kata_se = 0x30BB, kata_so = 0x30BD;
	int kata_ta = 0x30BF, kata_chi = 0x30C1, kata_tsu = 0x30C4, kata_te = 0x30C6, kata_to = 0x30C8;
	int kata_da = 0x30C0, kata_di = 0x30C2, kata_dsu = 0x30C5, kata_de = 0x30C7, kata_do = 0x30C9;
	int kata_na = 0x30CA, kata_ni = 0x30CB, kata_nu = 0x30CC, kata_ne = 0x30CD, kata_no = 0x30CE;
	int kata_ma = 0x30DE, kata_mi = 0x30DF, kata_mu = 0x30E0, kata_me = 0x30E1, kata_mo = 0x30E2;
	int kata_ya = 0x30E4, kata_yu = 0x30E6, kata_yo = 0x30E8;
	int kata_ra = 0x30E9, kata_ri = 0x30EA, kata_ru = 0x30EB, kata_re = 0x30EC, kata_ro = 0x30ED;
	int kata_wa = 0x30EF, kata_wo = 0x30F2, kata_n = 0x30F3;
	int kata_small_ya = 0x30E3, kata_small_yu = 0x30E5, kata_small_yo = 0x30E7;
	int kata_small_tte = 0x30C3;
//	int kata_prolonged_vowel = 0x30FC; // _ー_
//	String DBLWIDE_DASH = new String(new int[] { 0x30FC }, 0, 1); // _ー_
	
	// --- kanji ---
	int kj_economics_kei = 0x7D4C; // _経_
	int kj_build_san = 0x7523; // _産_
	int kj_continue_tsudsuku = 0x7D9A; // _続_
	int kj_read_yomu = 0x8AAD; // _読_
}
