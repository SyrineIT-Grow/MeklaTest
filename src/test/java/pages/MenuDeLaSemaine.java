package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.Config;

public class MenuDeLaSemaine {

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[3]/button")
	WebElement Suivant;

	@FindBy(xpath = "/html/body/div[1]/div/div[2]/div[1]/div[2]/div[1]/p")
	WebElement emballage;

	WebDriver driver;

	public MenuDeLaSemaine(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectionJour(String jour) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement jour1 = wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//button[contains(., '21/04')]")));
		jour1.click();


	
	}

	public void selectionnerPlatParNom(String nomPlat) {
		try {
			WebElement checkbox = driver.findElement(
					By.xpath("//label[contains(text(),'" + nomPlat + "')]/preceding-sibling::input[@type='checkbox']"));
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Plat non trouvé : " + nomPlat);
		}
	}

	public void Click() {
		Suivant.click();
	}

	public String VerifPage() {

		WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(emballage));

		String actualMessage = emballage.getText();
		return actualMessage;
	}

}
