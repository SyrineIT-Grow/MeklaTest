package helper;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
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

	  
		    public static void scrollToElement(WebElement element) {
		        try {
		            JavascriptExecutor js = (JavascriptExecutor) driver;
		            js.executeScript("arguments[0].scrollIntoView(true);", element);
		            // Petit délai pour stabiliser après le défilement
		            Thread.sleep(500);
		        } catch (Exception e) {
		            System.err.println("Échec de défilement vers l'élément: " + e.getMessage());
		            e.printStackTrace();
		        }
		    }

		    /**
		     * Attente explicite pour qu'un élément soit cliquable
		     */
		    public static WebElement waitForElementToBeClickable(WebElement element, int timeoutInSeconds) throws TimeoutException {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
				return wait.until(ExpectedConditions.elementToBeClickable(element));
		    }

		    /**
		     * Combine le défilement et le clic sur un élément avec attente
		     */
		    public static void scrollAndClickElement(By locator, int timeout) {
		        try {
		            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		            
		            scrollToElement(element);
		            
		            wait.until(ExpectedConditions.elementToBeClickable(element));
		            
		            element.click();
		        } catch (Exception e) {
		            System.err.println("Échec de l'opération scroll-and-click: " + e.getMessage());
		            e.printStackTrace();
		        }
		    }
		    
		    /**
		     * Méthode de diagnostic pour aider au débogage
		     */
		    public static void diagnoseElementVisibility(WebElement element) {
		        try {
		           
		            System.out.println("Élément trouvé: " + element);
		            System.out.println("Visible? " + element.isDisplayed());
		            System.out.println("Activé? " + element.isEnabled());
		            
		            Point location = element.getLocation();
		            Dimension size = element.getSize();
		            System.out.println("Position: " + location + ", Taille: " + size);
		            
		            // Obtenir dimensions viewport
		            JavascriptExecutor js = (JavascriptExecutor) driver;
		            long viewportWidth = (long) js.executeScript("return window.innerWidth");
		            long viewportHeight = (long) js.executeScript("return window.innerHeight");
		            System.out.println("Viewport: " + viewportWidth + "x" + viewportHeight);
		        } catch (Exception e) {
		            System.err.println("Diagnostic échoué: " + e.getMessage());
		        }
		    }
		}
	  
	  

