package fundamentals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGparameters {
	
	
	WebDriver driver;
	
	@Parameters("browsername")
	@BeforeTest
	public void browser(String browsername)
	
	{
		
		switch (browsername.toLowerCase())
		{
		case "chrome":
		    driver = new ChromeDriver();
		    break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "internetexplorer":
			driver = new InternetExplorerDriver();
			break;
		default:
			System.err.println("Invalid browsername");
		  
		}
	}
	
	@Parameters("url")
	@BeforeMethod()
	public void url(String url)
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		
	}
	
	
	@Parameters({"username", "password"})
	@Test
	public void login(String username, String password)
	{
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type=submit")).click();
		String actualurl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		String expectedurl = driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl);
		
	}
}
