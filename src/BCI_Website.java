import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BCI_Website {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.barcodeindia.com/");

		// driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		// Get the No. of links on the page
		System.out.println(driver.findElements(By.tagName("a")).size());

		WebElement footerdriver = driver.findElement(By.xpath("//footer"));
		System.out.println(footerdriver.findElements(By.tagName("a")).size());

		WebElement firstcolumndriver = driver.findElement(By.xpath("//div[@class=' w-full'][1]"));
		System.out.println(firstcolumndriver.findElements(By.tagName("a")).size());

		WebElement secondcolumndriver = driver.findElement(By.xpath("//div[@class=' w-full'][2]"));
		System.out.println(secondcolumndriver.findElements(By.tagName("a")).size());

		WebElement thirdcolumndriver = driver.findElement(By.xpath("//div[@class='w-full  ']"));
		System.out.println(thirdcolumndriver.findElements(By.tagName("a")).size());

		WebElement fourthcolumndriver = driver.findElement(By.xpath("//div[@class='w-full '][2]"));
		System.out.println(fourthcolumndriver.findElements(By.tagName("a")).size());

		for (int i = 1; i < firstcolumndriver.findElements(By.tagName("a")).size(); i++) {

			String clicknewtab = Keys.chord(Keys.CONTROL, Keys.ENTER);

			firstcolumndriver.findElements(By.tagName("a")).get(i).sendKeys(clicknewtab);
			// Thread.sleep(3000);

		}

		for (int i = 1; i < secondcolumndriver.findElements(By.tagName("a")).size(); i++) {

			String clicknewtab = Keys.chord(Keys.CONTROL, Keys.ENTER);

			secondcolumndriver.findElements(By.tagName("a")).get(i).sendKeys(clicknewtab);
			// Thread.sleep(3000);

		}
//		for (int i = 1; i < thirdcolumndriver.findElements(By.tagName("a")).size(); i++) {
//
//			String clicknewtab = Keys.chord(Keys.CONTROL, Keys.ENTER);
//
//			thirdcolumndriver.findElements(By.tagName("a")).get(i).sendKeys(clicknewtab);
//			// Thread.sleep(3000);
//
//		}
//		for (int i = 1; i < fourthcolumndriver.findElements(By.tagName("a")).size(); i++) {
//
//			String clicknewtab = Keys.chord(Keys.CONTROL, Keys.ENTER);
//
//			fourthcolumndriver.findElements(By.tagName("a")).get(i).sendKeys(clicknewtab);
//			// Thread.sleep(3000);
//
//		}
		
		
		Set<String> tabs = driver.getWindowHandles();

		Iterator<String> it = tabs.iterator();

		while (it.hasNext()) {
			

			driver.switchTo().window(it.next());
			Thread.sleep(1000);
			Integration_Inward.takeScreenshot(driver);
			System.out.println(driver.getTitle());
			
			driver.switchTo().window(it.next());
			Thread.sleep(1000);
			Integration_Inward.takeScreenshot(driver);
			System.out.println(driver.getTitle());
			
			driver.switchTo().window(it.next());
			Thread.sleep(1000);
			Integration_Inward.takeScreenshot(driver);
			System.out.println(driver.getTitle());
			
		}

		driver.quit();
	}

}
