import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Login_Test_MES {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();

		System.setProperty("webdriver.chrome.driver", "D:\\Web Drivers\\Chrome\\chrome-win64\\chrome-win64.exe");

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

		System.out.println("Login script run successfully");
		System.out.println(s);
		System.out.println("Test Run Successfully");
		driver.quit();

	}

}
