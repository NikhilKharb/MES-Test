import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;

//import org.openqa.selenium.JavacriptExecutor;
public class Label_Printing {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// Chrome
		WebDriver driver = new ChromeDriver();
		// Firefox
//		WebDriver driver = new FirefoxDriver();
		// Edge
//		WebDriver driver = new EdgeDriver();

		NgWebDriver ngdriver = new NgWebDriver((JavascriptExecutor)driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://180.151.246.51:8087/");
		Thread.sleep(500);
		driver.findElement(By.id("mat-input-0")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("Bci@123456");
		driver.findElement(ByAngular.buttonText("Login")).click();
		Thread.sleep(1000);
		driver.navigate().to("http://180.151.246.51:8087/app/createforms/1218");
		// String[] array = { "Supplier1", "Supplier2", "Supplier3", "Supplier4",
		// "Supplier5" };
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.findElement(By.id("Gate_In_Number")).click();
		List<WebElement> options = driver.findElements(By.cssSelector("mat-option[role='option']"));

		for (WebElement option : options) {

			if (option.getText().equalsIgnoreCase("G/24)040424001")) {

				option.click();
				break;

			}
		}

		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"excel-table\"]/tbody/tr"));
		System.out.println("No. of rows = " + rows.size());

		int NoOfRows = rows.size();
		for (int k = 1; k <= NoOfRows; k++) {

			driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[" + k + "]/td[1]/input")).click();
			String s = driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[" + k + "]/td[3]")).getText();
			js.executeScript("window.scrollBy(0,100)");
			int i = Integer.parseInt(s);
			if (i <= 0) {
				System.out.println("Remaining quantity = " + i);
				continue;
			} else {
				System.out.println(i);
				driver.findElement(By.id("MFG_Date")).sendKeys("20-03-2024");
				driver.findElement(By.id("Expiry_Date")).sendKeys("20-03-2024");

				if (i % 10 == 0) {
					driver.findElement(By.id("Pack_Size")).clear();
					driver.findElement(By.id("Pack_Size")).sendKeys("10");
					int i1 = i / 10;
					System.out.println("No. of prints = " + i1);
					String str = Integer.toString(i1);
					driver.findElement(By.id("No_of_Print")).clear();
					driver.findElement(By.id("No_of_Print")).sendKeys(str);
				} else if (i % 5 == 0) {
					driver.findElement(By.id("Pack_Size")).clear();
					driver.findElement(By.id("Pack_Size")).sendKeys("5");
					int i1 = i / 5;
					System.out.println("No. of prints = " + i1);
					String str = Integer.toString(i1);
					driver.findElement(By.id("No_of_Print")).clear();
					driver.findElement(By.id("No_of_Print")).sendKeys(str);
				} else if (i % 3 == 0) {
					driver.findElement(By.id("Pack_Size")).clear();
					driver.findElement(By.id("Pack_Size")).sendKeys("3");
					int i1 = i / 3;
					System.out.println("No. of prints = " + i1);
					String str = Integer.toString(i1);
					driver.findElement(By.id("No_of_Print")).clear();
					driver.findElement(By.id("No_of_Print")).sendKeys(str);
				} else if (i % 2 == 0) {
					driver.findElement(By.id("Pack_Size")).clear();
					driver.findElement(By.id("Pack_Size")).sendKeys("2");
					int i1 = i / 2;
					System.out.println("No. of prints = " + i1);
					String str = Integer.toString(i1);
					driver.findElement(By.id("No_of_Print")).clear();
					driver.findElement(By.id("No_of_Print")).sendKeys(str);
				}
				js.executeScript("window.scrollBy(0,500)");
				driver.findElement(By.id("Supplier_Batch_Code")).clear();
				driver.findElement(By.id("Supplier_Batch_Code")).sendKeys("QA Supplier");

				driver.findElement(By.id("Printer")).click();
				List<WebElement> options1 = driver.findElements(By.cssSelector("mat-option[role='option']"));

				for (WebElement option : options1) {

					if (option.getText().equalsIgnoreCase("Label Printer")) {

						option.click();
						break;
					}
				}
				Thread.sleep(1500);
				driver.findElement(By.id("Print")).click();
				js.executeScript("window.scrollBy(0,-300)");
				Thread.sleep(1000);

			}

		}
		ngdriver.waitForAngularRequestsToFinish();
		js.executeScript("window.scrollBy(0,-300)");
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[1]/td[1]/input")).click();
		ngdriver.waitForAngularRequestsToFinish();
		js.executeScript("window.scrollBy(0,400)");
		ngdriver.waitForAngularRequestsToFinish();

		driver.findElement(By.id("Complete")).click();
		System.out.println(driver.findElement(By.xpath("/html/body/div[4]/div/div")).getText());
		Assert.assertEquals("GRN Generated Successfully.",driver.findElement(By.xpath("/html/body/div[4]/div/div")).getText());
        driver.quit();
		
	}
}
