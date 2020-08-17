package Miko.SeleniumFramework;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SnapshotClass {
	
    public static void takeSnapShot(WebDriver webdriver, String testName, int step) throws Exception{
        String directoryPath = "c:\\SeleniumFramework_Snapshots\\"+testName+"\\";
        String snapshot = directoryPath+"Step_"+step+".png";
    	TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(snapshot);
        File directory = new File(directoryPath);
        if(!directory.exists()){
        	directory.mkdir();
		}
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
