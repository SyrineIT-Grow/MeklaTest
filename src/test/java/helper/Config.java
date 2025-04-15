package helper;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Config {

	public static WebDriver driver ;
	public static void confChrome() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		
	}
	
	public static void maximazWindow() {
		
		
		driver.manage().window().maximize();
	}
	
	  public static void attente(long s) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(s));
		}
	  
	  public static void attendreClic(WebElement element) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(element));
		}

}
