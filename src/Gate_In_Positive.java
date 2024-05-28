import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Gate_In_Positive {

	public static void main(String[] args) throws InterruptedException, IOException {
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
		String s = driver.findElement(By.xpath("//*[@id=\"navbar-collapse\"]/ul/li[2]/div/label[1]/a")).getText();
		// Print User Name
		System.out.println(s);

		// Verify UserName
		Assert.assertEquals("admin admin", s);

		driver.navigate().to("http://180.151.246.51:8087/app/createforms/1206");

		driver.findElement(By.id("Document_Type")).click();

		List<WebElement> options = driver.findElements(By.cssSelector("mat-option[role='option']"));

		for (WebElement option : options) {

			if (option.getText().equalsIgnoreCase("PO")) {

				option.click();
				break;
			}
		}

		driver.findElement(By.id("DocumentNo")).click();
		Thread.sleep(500);
		List<WebElement> options2 = driver.findElements(By.cssSelector("mat-option[role='option']"));

		for (WebElement option : options2) {

			if (option.getText().equalsIgnoreCase("4614(2022/08/25)")) {

				option.click();
				break;
			}
		}
		// driver.findElement(By.xpath("//mat-option[@id='mat-option-7']")).click();

		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[20]/td[7]/input")).sendKeys("20");
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[21]/td[7]/input")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[21]/td[7]/input")).sendKeys("25");
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[22]/td[7]/input")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[22]/td[7]/input")).sendKeys("18");
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[23]/td[7]/input")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[23]/td[7]/input")).sendKeys("50");
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[24]/td[7]/input")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[24]/td[7]/input")).sendKeys("8");

		driver.findElement(By.id("Supplier_Invoice_No")).sendKeys("INVOICENO001");
		driver.findElement(By.id("Invoice_Date")).sendKeys("22-03-2024");
		driver.findElement(By.id("No_of_Boxes")).sendKeys("15");
		driver.findElement(By.id("No_of_Boxes_as_per_LR")).sendKeys("20");
		driver.findElement(By.id("Vehicle_No")).sendKeys("DL8SQ7777");
		driver.findElement(By.id("LR_No")).sendKeys("10077");
		driver.findElement(By.id("LR_Date")).sendKeys("20-03-2024");
		driver.findElement(By.id("Eway_Bill_No")).sendKeys("10077");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		driver.findElement(By.id("Eway_Bill_Date")).sendKeys("28-03-2024");
		driver.findElement(By.id("Eway_Bill_Expiry_Date")).sendKeys("18-03-2025");
		driver.findElement(By.id("Driver_Name")).sendKeys("Driver");
		driver.findElement(By.id("Driver_Contact_No")).sendKeys("9876543210");
		driver.findElement(By.id("Dock_Reference")).sendKeys("D0077");
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"Priority\"]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"Priority\"]")).click();
		Thread.sleep(500);
		List<WebElement> options1 = driver.findElements(By.cssSelector("mat-option[role='option']"));

		for (WebElement option : options1) {

			if (option.getText().equalsIgnoreCase("High")) {

				option.click();
				break;
			}
		}
		driver.findElement(By.id("Remark")).sendKeys("Ok Checked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("mat-mdc-checkbox-2-input")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id=\"mat-mdc-checkbox-3-input\"]")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"accordion\"]/div/div/form/div/div[29]/div")).click();

		Date currentDate = new Date();
		String fileNamePrefix = currentDate.toString().replace(" ", "-").replace(":", "-");
		System.out.println("Today's Date is " + fileNamePrefix);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,
				new File("C:\\Users\\nikhil.kharb\\Downloads\\MES Screenshots\\" + fileNamePrefix + ".png"));
		// Thread.sleep(1000);
		String GateInNumber = driver.findElement(By.xpath(
				"/html/body/app-root/ng-component/section[2]/div/app-createforms/div/div/div/div[2]/div/div/form/div/div[30]/table/tbody/tr/td[2]"))
				.getText();
		System.out.println(GateInNumber);
		driver.quit();
		System.out.println("Gate In generated successfully");
	}

}