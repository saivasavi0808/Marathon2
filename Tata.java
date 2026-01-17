package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Tata {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//launch the browser
		ChromeOptions opt= new ChromeOptions();
		opt.addArguments("guest");
		ChromeDriver driver = new ChromeDriver(opt);
		//load url
		driver.get(" https://www.tatacliq.com/");
		//maximize window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//button[contains(text(),'No, Thanks')]")).click();
		//mousehover on brands
		WebElement mouHover = driver.findElement(By.xpath("//div[text()='Brands']"));
		//instantiate the actions
		Actions act=new Actions(driver);
		act.moveToElement(mouHover).perform();
     
		//mousehover on watches & accessories
		WebElement mouHover1 = driver.findElement(By.xpath("//div[text()='Watches & Accessories']"));
		//instantiate the actions
		Actions act1=new Actions(driver);
		act1.moveToElement(mouHover1).perform();
		//select first one ont the result of features brand
		driver.findElement(By.xpath("(//div[text()='Casio'])[1]")).click();
		Thread.sleep(4000);
		//sort by new arrivals
		WebElement sort=driver.findElement(By.xpath("//select[@label='Popularity']"));
		Select sel=new Select(sort);//instantiate the select class
		sel.selectByVisibleText("New Arrivals");
		driver.findElement(By.xpath("(//div[text()='Men'])[1]")).click();
		//list of prices of watches
		//locate price of watches 
		Thread.sleep(3000);
		List<WebElement>list1=driver.findElements(By.xpath("//div[@class='ProductDescription__priceHolder']"));
        System.out.println("size of prices of watches:"+list1.size());
        //create list to store prices
        List<String> list12=new ArrayList<String>();
		for (WebElement each : list1) {
		
			String price = each.getText();
			list12.add(price);
		}
		//print prices
		System.out.println("prices of watches:"+list12);
		Thread.sleep(2000);
		//click on first watch
		driver.findElement(By.xpath("//div[@class='ProductModule__dummyDiv']")).click();
		//window handling
		Set<String> childWindow = driver.getWindowHandles();
		//convert set into list
		List<String> listWindow=new ArrayList<String>(childWindow);
		//navigate to child window
		driver.switchTo().window(listWindow.get(1));
		//locate add to cart
		WebElement scrolldown=driver.findElement(By.xpath("//span[text()='ADD TO BAG']"));
		Actions act3=new Actions(driver);
		act3.scrollToElement(scrolldown).perform();
		Thread.sleep(2000);
		//click on add to bag
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		Thread.sleep(2000);
		//get count of cart items
		WebElement s=driver.findElement(By.xpath("//span[@class='DesktopHeader__cartCount']"));
		System.out.println(s.getText());
		//click on cart
		driver.findElement(By.xpath("//div[@class='DesktopHeader__myBagShow']")).click();
		Thread.sleep(3000);
		//to take screenshot
		File source=driver.getScreenshotAs(OutputType.FILE);
		File destination=new File("./snap/resultant of tata.png");
		FileUtils.copyFile(source, destination);
		//close all open windows
		driver.quit();
		

	}

}
