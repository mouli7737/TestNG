package learning;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Dependecy {
	
	
	WebDriver driver;
	
	@Test
	public void makeMyTrip()
	{
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://www.makemytrip.com/");	
	}
	
	@Test(dependsOnMethods ="makeMyTrip")
	public void hotels()
	{
		driver.findElement(By.xpath("(//span[text() ='Hotels'])[1]")).click();
		
		driver.findElement(By.xpath("(//input[@id ='checkin'])[1]")).click();
		
		driver.findElement(By.xpath("((//div[@class='DayPicker-Week'])[4]/div)[1]")).click();
		
		driver.findElement(By.xpath("((//div[@class='DayPicker-Week'])[4]/div)[3]")).click();
		
		driver.findElement(By.xpath("//button[text()='Apply']")).click();
		
		driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
		
		driver.findElement(By.xpath("//span[text()='Hard Rock Hotel Goa Calangute']")).click();
		
	}
	
	@Test(dependsOnMethods ={"makeMyTrip","hotels"})
	public void bookingRoom()
	
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		Set<String>handles =driver.getWindowHandles();
		
		Iterator it = handles.iterator();
		String parenthandle =(String) it.next();
		String childhandle= (String)it.next();
		
		driver.switchTo().window(childhandle);
		driver.findElement(By.xpath("//button[text()='BOOK THIS NOW']")).click();
	}
	

}
