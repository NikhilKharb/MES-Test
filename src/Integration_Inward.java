import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.paulhammant.ngwebdriver.ByAngular;

//import org.openqa.selenium.JavacriptExecutor;
public class Integration_Inward {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// Chrome
		WebDriver driver = new ChromeDriver();

		// Firefox
//		WebDriver driver = new FirefoxDriver();

		// Edge
//		WebDriver driver = new EdgeDriver();
		// Driver for Angular Elements
		// NgWebDriver ngdriver = new NgWebDriver((JavascriptExecutor) driver);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String date = sdf.format(new Date());

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		ScreenRecorderUtil.startRecord("main");

		login(driver);
		Thread.sleep(1000);
		String gateInNumber = getGateInNumber(driver, date);
		completePrinting(driver, gateInNumber, date);
		driver.quit();
		ScreenRecorderUtil.stopRecord();

	}

	public static void login(WebDriver driver) throws InterruptedException {
		driver.get("http://180.151.246.51:8087/");
		driver.findElement(By.id("mat-input-0")).sendKeys("quality");
		driver.findElement(By.name("password")).sendKeys("Bci@12345");
		driver.findElement(ByAngular.buttonText("Login")).click();
		Thread.sleep(1000);
	}

	public static void completePrinting(WebDriver driver, String gateInNumber, String date)
			throws InterruptedException, IOException {

		driver.navigate().to("http://180.151.246.51:8087/app/createforms/1218");
		// String[] array = { "Supplier1", "Supplier2", "Supplier3", "Supplier4",
		// "Supplier5" };

		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.findElement(By.id("Gate_In_Number")).click();
		List<WebElement> options = driver.findElements(By.cssSelector("mat-option[role='option']"));

		for (WebElement option : options) {

			if (option.getText().equalsIgnoreCase(gateInNumber)) {
				System.out.println(option.getText());
				option.click();
				break;

			}
		}
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"excel-table\"]/tbody/tr"));
		System.out.println("No. of rows = " + rows.size());

		int NoOfRows = rows.size();
		for (int k = 1; k <= NoOfRows; k++) {
			
			
			driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[" + k + "]/td[1]/input")).click();
			String s = driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[" + k + "]/td[7]")).getText();
			js.executeScript("window.scrollBy(0,300)");
			int i = Integer.parseInt(s);
			if (i <= 0) {
				System.out.println("Remaining quantity = " + i);
				continue;
			} else {
				System.out.println(i);
				driver.findElement(By.id("MFG_Date")).sendKeys(date);
				driver.findElement(By.id("Expiry_Date")).sendKeys(date);

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
				js.executeScript("window.scrollBy(0,300)");
				driver.findElement(By.id("Supplier_Batch_Code")).clear();
				driver.findElement(By.id("Supplier_Batch_Code")).sendKeys("QA Supplier");

				driver.findElement(By.id("Printer")).click();
				List<WebElement> options1 = driver.findElements(By.cssSelector("mat-option[role='option']"));

				for (WebElement option : options1) {

					if (option.getText().equalsIgnoreCase("Zebra ZD23")) {

						option.click();
						break;
					}
				}
				Thread.sleep(1000);
				driver.findElement(By.id("Print")).click();
				Thread.sleep(1000);
				takeScreenshot(driver);
				js.executeScript("window.scrollBy(0,-400)");
				Thread.sleep(1000);

			}

		}
		Thread.sleep(1500);
		js.executeScript("window.scrollBy(0,-400)");
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[1]/td[1]/input")).click();
		// Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(1000);
		driver.findElement(By.id("Complete")).click();
		Thread.sleep(2000);
		takeScreenshot(driver);
		System.out.println(driver.findElement(By.xpath("/html/body/div[4]/div/div")).getText());

	}

	public static String getGateInNumber(WebDriver driver, String date) throws InterruptedException, IOException {

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

			if (option.getText().equalsIgnoreCase("4615(2022/08/25)")) {

				option.click();
				break;
			}
		}
		// *[@id="excel-table"]/tbody/tr[20]/td[7]/input
		// *[@id="excel-table"]/tbody/tr[1]/td[7]/input
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[1]/td[7]/input")).sendKeys("50");
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[2]/td[7]/input")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[2]/td[7]/input")).sendKeys("100");
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[3]/td[7]/input")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[3]/td[7]/input")).sendKeys("50");
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[4]/td[7]/input")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[4]/td[7]/input")).sendKeys("100");
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[5]/td[7]/input")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"excel-table\"]/tbody/tr[5]/td[7]/input")).sendKeys("50");

		driver.findElement(By.id("Supplier_Invoice_No")).sendKeys("INVOICENO001");
		driver.findElement(By.id("Invoice_Date")).sendKeys(date);
		driver.findElement(By.id("No_of_Boxes")).sendKeys("5");
//		driver.findElement(By.id("No_of_Boxes_as_per_LR")).sendKeys("20");
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

		takeScreenshot(driver);
		Thread.sleep(500);
		js1.executeScript("window.scrollBy(0,100)");
		String GateInNumber = driver.findElement(By.xpath("/html/body/app-root/ng-component/section[2]/div/app-createforms/div/div/div/div[2]/div/div/form/div/div[27]/table/tbody/tr/td[2]")).getText();
		return GateInNumber;
	}

	public static void takeScreenshot(WebDriver driver) throws IOException {
		Date currentDate = new Date();
		String fileNamePrefix = currentDate.toString().replace(" ", "-").replace(":", "-");
		// System.out.println(fileNamePrefix);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,
				new File("C:\\Users\\nikhil.kharb\\Downloads\\MES Screenshots\\" + fileNamePrefix + ".png"));
	}
}
