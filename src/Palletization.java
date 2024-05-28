import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//import org.openqa.selenium.JavacriptExecutor;
public class Palletization {

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
		driver.navigate().to("http://180.151.246.51:8087/app/createforms/1232");
		ArrayList<String> pallet = new ArrayList<String>();
		pallet.add("060523P112");
		pallet.add("080523P113");
		pallet.add("090523P115");
		pallet.add("100523P115");
		pallet.add("100523P485907");
		pallet.add("110523P501891");
		pallet.add("140523P501891");
		System.out.println(pallet);
		String[] palletid = new String[pallet.size()];
		palletid[0] = pallet.get(0);
		driver.findElement(By.id("Scan_Pallet")).sendKeys(palletid[0]);

		String[] itemcodes = { "SN40102803240001", "SN40102803240002", "SN40102803240003", "SN40102803240004",
				"SN40102803240005", "SN40102803240006", "SN40102803240007", "SN40102803240008" };
		for (int i = 0; i < itemcodes.length; i++) {
			driver.findElement(By.id("Scan_Item")).sendKeys(itemcodes[i]);

			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@id=\"accordion\"]/div/div/form")).click();
			driver.findElement(By.id("Scan_Item")).clear();
		}
	}
}