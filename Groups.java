package learning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class Groups {
	
	public WebDriver driver;
	
	
	@Test(groups ="smoke")
	public void login()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String actualurl = "https://admin-demo.nopcommerce.com/admin/";
		String Expectedurl = driver.getCurrentUrl();
		
		Assert.assertEquals(actualurl, Expectedurl,"the user logged in");
		
	}

	@AfterMethod(groups ="functional")
	public void Catalog()
	{
		driver.findElement(By.xpath("(//a[@href='#'])[4]//p")).click();
		driver.findElement(By.xpath("//a[@href='/Admin/Product/List']")).click();
		driver.findElement(By.xpath("//input[@name='SearchProductName']")).sendKeys("HP Laptop");
        WebElement catagory = driver.findElement(By.xpath("//select[@id='SearchCategoryId']"));
		Select select = new Select(catagory);
		
		select.selectByVisibleText("Computers");
		
		driver.findElement(By.xpath("//button[@id='search-products']")).click();
	}
	
	
	@AfterMethod(groups ="sanity")
	public void addnew()
	{
		driver.findElement(By.xpath("//a[@href='/Admin/Product/Create']")).click();
		driver.findElement(By.id("Name")).sendKeys("Apple 15 pro max");
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		
	}
	
	@AfterMethod(groups ="smoke")
	public void logout()
	{
		//driver.findElement(By.xpath("//a[text()='Logout']")).click();
		driver.findElement(By.linkText("Logout")).click();
	}
	
	
}
