package xyz.tamagawa.webcrawler;

import java.io.File;
import java.util.Map;

import org.openqa.selenium.chrome.*;
import org.testng.annotations.Test;

import xyz.tamagawa.utils.BOM;
import xyz.tamagawa.utils.BasicUtils;

public class Main {
	

	public static void main(String[] args) {
		System.out.println("xyz.tamagawa.webcrawler.Main->main()..._start_...");

		try {
//			Main mm = new Main();
			Main.fooTestng();
			
//			System.setProperty("webdriver.chrome.driver", "/Users/dwilkins/Documents/Tamagawa/Dev/Java/BrowserDrivers/chromedriver");
//			Main.doChrome();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("xyz.tamagawa.webcrawler.Main->main()...::END::...");
	}

	@Test(priority=1, description="first desc is as follows.")
	// ---===---
	public static void fooTestng() {
		System.out.println("=======================================aaaaa");
		System.out.println("=======================================xxxxx");
		System.out.println("=======================================ggggg");
		try {
			System.out.println("[testNG]_start_june18...__");
			String fileName = "/Users/dwilkins/Documents/Tamagawa/Data/BOMs/";
//			fileName += "bbeUTF16LEbom.txt";
//			fileName += "bbeUTF8bom.txt";
			fileName += "bbeUTF16.txt";
//			fileName += "bbeGB18030.txt"; // <-- no BOM
			

//			fileName += "bbeWinLatin1.txt";
//			fileName += "bomUtf16be.txt";
//			fileName += "bomUtf32le.txt";
			
//			fileName += "bbeUtf16le.txt";
//			fileName += "bbeUtf16.txt";
//			fileName += "bbeGb18030.txt";
//			fileName += "aaa.txt";
			
			File f = new File(fileName);
//			Map<Integer, BOM.XtdCharset> boms = BOM.locateByteOrderMarks(f);
//			Map<Integer, BOM.XtdCharset> boms = BOM.locateBOMs(f);
//			for(Map.Entry<Integer, BOM.XtdCharset> entry : boms.entrySet()) {
//				String s = entry.getValue().name();
//				System.out.println("[6/19]___" + s + "____at --> _" + entry.getKey() + "_");
//			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// ---===---
	static void doChrome() throws Exception {
//		ChromeDriver drv = new ChromeDriver();
//		drv.get("http://www.google.com");
	}

}
