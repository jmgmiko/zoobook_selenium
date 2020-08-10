package Miko.SeleniumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;  

public class TestScript {
	
	private File fileName;

	
	public TestScript() {
		super();
		JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Excel files", "xlsx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	this.fileName = chooser.getSelectedFile();
        } else {
        	this.fileName = null;
        }
	}

	public File getFileName() {
		return fileName;
	}

	public void setFileName(File fileName) {
		this.fileName = fileName;
	}
	
	public void readTestScript() throws Exception {
		try {
			DataFormatter df = new DataFormatter();
			AutomationDriver driver = null;
			FileInputStream fis = new FileInputStream(this.fileName); 
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
			for (int x=1; x<=sheet.getLastRowNum(); x++)                 
			{  
				Row row = sheet.getRow(x);
				if (row.getCell(0) == null) {
					throw new Exception("Command column must not be empty (Empty value found at Row "+(x+1)+", Cell 1");
				} else if (row.getCell(2) == null) {
					throw new Exception("Name column must not be empty (Empty value found at Row "+(x+1)+", Cell 3");
				}
				String command = row.getCell(0).getStringCellValue();
				String findBy = row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue();
				String name = row.getCell(2).getStringCellValue();
				String customInput = row.getCell(3) == null ? "" : df.formatCellValue(row.getCell(3));
				String execute = row.getCell(4).getStringCellValue();
				if (findBy == "" && !command.equals("Open")) {
					throw new Exception("Findby column must not be empty (Empty value found at Row "+(x+1)+", Cell 2");
				}
				if (customInput == "" && (command.equals("Open"))) {
					throw new Exception("Input column must not be empty (Empty value found at Row "+(x+1)+", Cell 4");
				}
				if (execute.equals("Yes")) {
					switch (command) {
						case "Open":
							driver = new AutomationDriver(name);
							driver.getDriver().get(customInput);
							break;
						case "Input":
							WebAppTesting.inputTextField(driver.getDriver(), findBy, name, customInput);
							break;
						case "Click":
							WebAppTesting.clickButton(driver.getDriver(), findBy, name);
							break;
						case "Mouse Hover":
							WebAppTesting.mouseHover(driver.getDriver(), findBy, name);
							break;
						case "Select Dropdown":
							WebAppTesting.selectDropdown(driver.getDriver(), findBy, name, customInput);
							break;
					}		
				}		
			} 
			if (driver != null) {
				driver.getDriver().quit();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
	}
}
