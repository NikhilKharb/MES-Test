import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//import org.openqa.selenium.JavacriptExecutor;
public class InternalMovement {

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
		driver.navigate().to("http://180.151.246.51:8087/app/createforms/1239");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.findElement(By.id("mat-radio-2-input")).click();

		ArrayList<String> location = new ArrayList<String>();
		location.add("FG-Larah-GF-R1-0204");
		location.add("FG-Larah-GF-R1-0205");

		String[] locationid = new String[location.size()];
		locationid[0] = location.get(0);
		locationid[1] = location.get(1);
		driver.findElement(By.id("Scan_Source_Location")).sendKeys(locationid[1]);
		System.out.println("Source location is :- " + locationid[1]);
		String[] itemcodes = { "SN40252803240001", "SN40252803240002", "SN40252803240003", "SN40252803240004",
				"SN40252803240005" };
		js.executeScript("window.scrollBy(0,100)");
		for (int i = 0; i < itemcodes.length; i++) {
			driver.findElement(By.id("Scan_Item_Barcode")).clear();
			driver.findElement(By.id("Scan_Item_Barcode")).sendKeys(itemcodes[i]);
			System.out.println("Item codes which are moved from one location to another :- " + itemcodes[i]);
			driver.findElement(By.id("Scan_Destination_Location")).sendKeys(locationid[0]);
			Thread.sleep(200);
			driver.findElement(By.xpath("/html/body")).click();
			driver.findElement(By.id("Scan_Destination_Location")).clear();

		}
		System.out.println("Destination Location is :- " + locationid[0]);

	}
}