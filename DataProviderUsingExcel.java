package learning;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.SystemProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class DataProviderUsingExcel    {  
	
	WebDriver driver;
	
	
	@Test(dataProvider ="sender", priority =2)
	public void login(String username , String password)
	{
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@DataProvider(name ="sender")
	@Test
	 public Object[][] dataProvider() throws IOException  {
		
		
		File file = new File(System.getProperty("user.dir")+"./src/test/resources/Logindata.xlsx");
		//System.out.println(file.exists());

		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet("data");
		
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object [][] data = new Object [rows][cols];
		
		System.out.println(rows +" "+ cols);
		
		for(int r = 1; r<rows; r++)
		{
			XSSFRow row = sheet.getRow(r);
			for(int c =0; c<cols;c++)
			{
				XSSFCell cell = row.getCell(c);
			   org.apache.poi.ss.usermodel.CellType  celltype =cell.getCellType();
			   
			   switch (celltype)
			   {
			   case  STRING:
				  data[r][c] = cell.getStringCellValue();
				   break;
			   case  NUMERIC:
				   data[r][c] = cell.getNumericCellValue();
				   break;
			   case  BOOLEAN:
				   data[r][c] = cell.getBooleanCellValue();
				   break;
			   }
 
			}
			System.out.println(" ");
		}
		return data;
		
	
	  
	}
	
}
