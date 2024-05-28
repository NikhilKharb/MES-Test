
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//import org.openqa.selenium.JavacriptExecutor;
public class IQC_Old {

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
		driver.navigate().to("http://180.151.246.51:8087/app/createforms/1215");

		driver.findElement(By.id("GRN_No")).click();
		List<WebElement> options = driver.findElements(By.cssSelector("mat-option[role='option']"));

		for (WebElement option : options) {

			if (option.getText().equalsIgnoreCase("GRN4003280324118")) {

				option.click();
				break;
			}
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,150)");
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"excel-table\"]/tbody/tr"));
		System.out.println("No. of rows = " + rows.size());
		int NoOfRows = rows.size();
		for (int k = 1; k <= NoOfRows; k++) {

			driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[" + k + "]/td[1]/input")).click();
			String s = driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[" + k + "]/td[5]")).getText();

			driver.findElement(By.id("Scan_Barcode")).sendKeys(s);
			driver.findElement(By.id("Scan_Barcode")).clear();
			Thread.sleep(500);
		}
		Thread.sleep(2000);
	    js.executeScript("window.scrollBy(0,250)");
		driver.findElement(By.id("QC_Type")).sendKeys("OK");
	    js.executeScript("window.scrollBy(0,150)");
		Thread.sleep(1000);
		driver.findElement(By.id("Complete")).click();

		//driver.findElement(By.xpath("//*[@id=\"accordion\"]/div/div/form/div/div[8]/div")).click();
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText());
		
		driver.quit();
	}
}
