import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.paulhammant.ngwebdriver.ByAngular;

public class TrialClass {

	@Test

	public static void main(String[] args) throws Exception {
		WebDriver driver = new ChromeDriver();

		SoftAssert sa = new SoftAssert();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String date = sdf.format(new Date());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://180.151.246.51:8087/");
		driver.findElement(By.id("mat-input-0")).sendKeys("quality");
		driver.findElement(By.name("password")).sendKeys("Bci@12345");
		driver.findElement(ByAngular.buttonText("Login")).click();
		Thread.sleep(2000);

		driver.navigate().to("http://180.151.246.51:8087/app/createforms/1206");
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
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

			if (option.getText().equalsIgnoreCase("4635(2022/10/06)")) {

				option.click();
				break;
			}
		}

		List<WebElement> noOfRows = driver.findElements(By.xpath("//table[@id='excel-table']/tbody/tr/td[4]"));
		System.out.println(noOfRows.size());
		List<WebElement> sendQty = driver.findElements(By.xpath("//table[@id='excel-table']/tbody/tr/td[7]/input"));
		System.out.println(sendQty.size());
		ArrayList<String> Qty = new ArrayList<String>();

		// Thread.sleep(500);
		int k = 0;
		Qty.clear();

		for (int i = 0; i < noOfRows.size(); i++) {
			String pendingQty = noOfRows.get(i).getText();
			System.out.println(pendingQty);
			Qty.add(pendingQty);

		}

		for (int j = 1; j <= sendQty.size(); j++) {

			driver.findElement(By.xpath("//table[@id='excel-table']/tbody/tr[" + j + "]/td[7]/input"))
					.sendKeys(Qty.get(k));

			driver.findElement(By.id("accordion")).click();
			Thread.sleep(500);
			System.out.println(k);

			k++;

		}

//		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[20]/td[7]/input")).sendKeys("20");
//		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[21]/td[7]/input")).click();
//		Thread.sleep(500);
//		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[21]/td[7]/input")).sendKeys("25");
//		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[22]/td[7]/input")).click();
//		Thread.sleep(500);
//		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[22]/td[7]/input")).sendKeys("18");
//		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[23]/td[7]/input")).click();
//		Thread.sleep(500);
//		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[23]/td[7]/input")).sendKeys("50");
//		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[24]/td[7]/input")).click();
//		Thread.sleep(500);
//		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[24]/td[7]/input")).sendKeys("8");

		driver.findElement(By.id("Supplier_Invoice_No")).sendKeys("INVOICENO001");
		driver.findElement(By.id("Invoice_Date")).sendKeys(date);
		driver.findElement(By.id("No_of_Boxes")).sendKeys("15");
		driver.findElement(By.id("No_of_Boxes_as_per_LR")).sendKeys("20");
		driver.findElement(By.id("Vehicle_No")).sendKeys("DL8SQ7777");
		driver.findElement(By.id("LR_No")).sendKeys("10077");
		driver.findElement(By.id("LR_Date")).sendKeys(date);
		driver.findElement(By.id("Eway_Bill_No")).sendKeys("10077");
		Thread.sleep(500);
		js1.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.id("Eway_Bill_Date")).sendKeys(date);
		driver.findElement(By.id("Eway_Bill_Expiry_Date")).sendKeys(date);
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
		driver.findElement(By.id("Save")).click();
		Thread.sleep(1000);

		Integration_Inward.takeScreenshot(driver);
		Thread.sleep(500);
		js1.executeScript("window.scrollBy(0,100)");
		String GateInNumber = driver.findElement(By.xpath(
				"/html/body/app-root/ng-component/section[2]/div/app-createforms/div/div/div/div[2]/div/div/form/div/div[30]/table/tbody/tr/td[2]"))
				.getText();
		System.out.println(GateInNumber);
		driver.quit();

	}
}