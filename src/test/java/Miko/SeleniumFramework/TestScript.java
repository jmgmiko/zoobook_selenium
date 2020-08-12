package Miko.SeleniumFramework;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
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
        if (returnVal == JFileChooser.APPROVE_OPTION) {
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
	
	public CellStyle setStatus(XSSFWorkbook wb, short color) {
		CellStyle givenCell = wb.createCellStyle();
		Font givenFont = wb.createFont();
		givenFont.setBold(true);
		givenFont.setColor(color);
		givenCell.setFont(givenFont);
		return givenCell;
	}
	
	public void readTestScript() throws Exception {
		try {
			boolean result = false;
			DataFormatter df = new DataFormatter();
			AutomationDriver driver = null;
			FileInputStream fis = new FileInputStream(this.fileName); 		
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet("Steps");     
			CellStyle failStyle = setStatus(wb, HSSFColor.RED.index);
			CellStyle passStyle = setStatus(wb, HSSFColor.GREEN.index);
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
				Cell status = row.getCell(5);
				if (status == null) {
					status = row.createCell(5);
				}
				if (findBy == "" && !command.equals("Open")) {
					throw new Exception("Findby column must not be empty (Empty value found at Row "+(x+1)+", Cell 2");
				}
				if (customInput == "" && (command.equals("Open"))) {
					throw new Exception("Input column must not be empty (Empty value found at Row "+(x+1)+", Cell 4");
				}
				if (execute.equals("No") && command.equals("Open")) {
					throw new Exception("Open command must be executed");
				}
				if (execute.equals("Yes")) {
					switch (command) {
						case "Open":
							driver = new AutomationDriver(name);
							driver.getDriver().get(customInput);
							result = true;
							break;
						case "Input":
							result = WebAppTesting.inputTextField(driver.getDriver(), findBy, name, customInput);
							break;
						case "Click":
							result = WebAppTesting.clickButton(driver.getDriver(), findBy, name);
							break;
						case "Mouse Hover":
							result = WebAppTesting.mouseHover(driver.getDriver(), findBy, name);
							break;
						case "Select Dropdown":
							result = WebAppTesting.selectDropdown(driver.getDriver(), findBy, name, customInput);
							break;
						case "Check If Exists":
							result = WebAppTesting.checkIfElementExists(driver.getDriver(), findBy, name, customInput);
							break;
					}
					
					if (result == true) {
						status.setCellValue("PASS");
						status.setCellStyle(passStyle);
					} else if (result == false) {
						status.setCellValue("FAIL");
						status.setCellStyle(failStyle);
					} 
				} else {
					status.setCellValue("");
				}
			}
			fis.close();
			FileOutputStream fos = new FileOutputStream(this.fileName);
			wb.write(fos);
			fos.close();
			wb.close();
			if (driver != null) {
				driver.getDriver().quit();
			}
			Desktop.getDesktop().open(this.fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
	}
}
