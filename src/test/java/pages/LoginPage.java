package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.Config;

public class LoginPage {



	@FindBy(xpath="/html/body/div[1]/div/div/div[2]/div[3]/div/div/div/div/form/div[1]/div/input")
	WebElement Email ;
	@FindBy(xpath="/html/body/div[1]/div/div/div[2]/div[3]/div/div/div/div/form/div[2]/div/input")
	WebElement pswd ;
	@FindBy(xpath ="/html/body/div[1]/div/div/div[2]/div[3]/div/div/div/div/form/button")
	WebElement submit ;

	@FindBy(xpath = "//h1[contains(text(),'Menu de la semaine')]")
	WebElement Msg;
	@FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div")
	WebElement M;

	WebDriver driver;

	public LoginPage(WebDriver driver) {
	    this.driver = driver;
	    PageFactory.initElements(driver, this); // ici on utilise bien le driver passé en paramètre
	}


	 public void connect(String email, String paswd) {

			    WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));

			    // Attendre que l'élément Email soit visible
			    wait.until(ExpectedConditions.visibilityOf(Email));

			    Email.sendKeys(email);
			    pswd.sendKeys(paswd);
			    Config.attendreClic(submit);
			    submit.click();
			}



		public String verifMessage() {
			  // Attente explicite jusqu'à ce que l'élément soit visible
		    WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.visibilityOf(M)); // Msg est l'élément avec ton XPath

		    // Récupération du texte de l'élément une fois visible
		    String actualMessage = M.getText();
		    return actualMessage;
		}



	 public String verifPage() {
		    // Attente explicite jusqu'à ce que l'élément soit visible
		    WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.visibilityOf(Msg)); // Msg est l'élément avec ton XPath

		    // Récupération du texte de l'élément une fois visible
		    String actualMessage = Msg.getText();
		    return actualMessage;
		}

}
