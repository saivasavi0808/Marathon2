package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.sukgu.Shadow;

public class Service {

	public static void main(String[] args) throws InterruptedException, IOException {
		//Launch browser
		ChromeOptions opt=new ChromeOptions();
		opt.addArguments("guest");
		ChromeDriver driver=new ChromeDriver(opt);
		//load url
		driver.get("https://dev181504.service-now.com/");
		//maximize window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Enter username and password
        driver.findElement(By.id("user_name")).sendKeys("admin");
        driver.findElement(By.id("user_password")).sendKeys("F/*jyM6Q6pHh");
        //click on login 
        driver.findElement(By.name("not_important")).click();
        //shadow handling
        Shadow shadow=new Shadow(driver);
        shadow.setImplicitWait(10);
        //click on all
        shadow.findElementByXPath("//div[text()='All']").click();
        //enter service catalog
        shadow.findElementByXPath("//input[@id='filter']").sendKeys("Service catalog");
        //click on service catalog
        shadow.findElementByXPath("//mark[text()='Service Catalog']").click();
        Thread.sleep(3000);
        //switch to frame
        WebElement fr=shadow.findElementByXPath("//iframe[@title='Main Content']");
        driver.switchTo().frame(fr);
        //click on mobiles
        driver.findElement(By.xpath("//h2[contains(text(),'Mobiles')]")).click();
        //click on apple iphone 13pro
        driver.findElement(By.xpath("//strong[text()='Apple iPhone 13 pro']")).click();
        Thread.sleep(2000);
        //select yes under lost or broken iPhone
        driver.findElement(By.xpath("(//label[@class='radio-label'])[1]")).click();
        Thread.sleep(2000);
        //enter 99 under  original phonenumber field
        driver.findElement(By.xpath("//input[@aria-required='true']")).sendKeys("99");
        //Select Unlimited from the dropdown in Monthly data allowance
        WebElement sort=driver.findElement(By.xpath("//select[@class='form-control cat_item_option ']"));
		Select sel=new Select(sort);//instantiate the select class
		sel.selectByValue("unlimited");
		//Update color field to SierraBlue and storage field to 512GB
		driver.findElement(By.xpath("(//label[@class='radio-label'])[7]")).click();
		driver.findElement(By.xpath("(//label[@class='radio-label'])[10]")).click();
		Thread.sleep(3000);
		//click on order now
		driver.findElement(By.xpath("//button[@name='oi_order_now_button']")).click();
		Thread.sleep(3000);
		//get text of request number
		WebElement s=driver.findElement(By.id("requesturl"));
		System.out.println("the request number:"+s.getText());
		//get screenshot
		File source=driver.getScreenshotAs(OutputType.FILE);
		//path
		File destination=new File("./snap/result.png");
		//connect source and destination
		FileUtils.copyFile(source, destination);		
		//close the window
		driver.quit();
	}

}
