import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//import org.openqa.selenium.JavacriptExecutor;
public class PutAwayGRN {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// Chrome
		WebDriver driver = new ChromeDriver();

		// Firefox
//		WebDriver driver = new FirefoxDriver();

		// Edge
//		WebDriver driver = new EdgeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://180.151.246.51:8087/");
		Thread.sleep(500);
		driver.findElement(By.id("mat-input-0")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("Bci@123456");
		driver.findElement(
				By.xpath("/html/body/app-root/ng-component/div/ng-component/div/div/form/div[3]/div[1]/button"))
				.click();
		Thread.sleep(1000);
		driver.navigate().to("http://180.151.246.51:8087/app/createforms/1240");
		// String[] array = { "Supplier1", "Supplier2", "Supplier3", "Supplier4",
		// "Supplier5" };
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.findElement(By.id("GRN_No")).click();
		List<WebElement> options = driver.findElements(By.cssSelector("mat-option[role='option']"));

		for (WebElement option : options) {

			if (option.getText().equalsIgnoreCase("GRN4025280324120")) {

				option.click();
				break;

			}
		}
		ArrayList<String> location = new ArrayList<String>();
		location.add("FG-Larah-GF-R1-0204");
		location.add("FG-Larah-GF-R1-0205");

		System.out.println(location);
		String[] locationid = new String[location.size()];
		locationid[0] = location.get(0);
		driver.findElement(By.id("Scan_Location")).sendKeys(locationid[0]);

		String[] itemcodes = { "SN40252803240001", "SN40252803240002", "SN40252803240003", "SN40252803240004",
				"SN40252803240005" };
		driver.findElement(By.id("mat-radio-2-input")).click();
		js.executeScript("window.scrollBy(0,100)");
		for (int i = 0; i < itemcodes.length; i++) {
			driver.findElement(By.id("Scan_Barcode")).sendKeys(itemcodes[i]);

			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@id=\"accordion\"]/div/div/form")).click();
			driver.findElement(By.id("Scan_Barcode")).clear();
		}
	}
}