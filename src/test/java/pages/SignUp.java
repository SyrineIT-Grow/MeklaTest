package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.Config;

public class SignUp {

	@FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div[3]/div[1]/div/div/div")
	List<WebElement> Element;
	@FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div[3]/div[2]/button")
	WebElement bouton;
	@FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div[3]/h2")
	WebElement M;
	@FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div[3]/div[1]/div/div/div[1]")
	List<WebElement> Sport;
	@FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div[3]/div[1]/div/div/label/span")
	List<WebElement> allergie;
	@FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div[3]/h2")
	WebElement P;

	@FindBy(css = "input[placeholder='Entrez votre adresse'].w-full")
	WebElement adresse;

	@FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div[3]/div[1]/div/div[2]/input")
	WebElement ville;

	@FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div[3]/div[1]/div/div[4]/input")
	WebElement codePostal;

	@FindBy(xpath = "//select[contains(@class, 'zone') or @id='zone']")
	WebElement zoneDropdown;

	WebDriver driver;

	public SignUp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void ChoisirObjectifAlimentaire(String Obj) {

		Config.attente(10);
		try {
			for (WebElement element : Element) {
				System.out.println(element.getText());
				if (element.getText().contains(Obj)) {
					element.click();
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void Click() {
		bouton.click();
	}

	public String VerifPage1() {

		WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(M));

		String actualMessage = M.getText();
		return actualMessage;
	}

	public void ChoisirSport(String Op) {

		Config.attente(10);
		try {
			for (WebElement option : Sport) {
				System.out.println(option.getText());
				if (option.getText().contains(Op)) {
					option.click();
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void Allergie(String Allergie) {
		Config.attente(10);
		try {
			for (WebElement all : allergie) {
				System.out.println(all.getText());
				if (all.getText().contains(Allergie)) {
					all.click();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String VerifPage2() {

		WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(P));

		String actualMessage = P.getText();
		return actualMessage;
	}

	public void user_est_sur_la_page(String nomPage) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		if (nomPage.equalsIgnoreCase("Géolocalisation")) {
			// Attendre qu’un titre, un bouton ou le champ adresse soit visible
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div[2]/div[3]/h2")));

			System.out.println("📍 Page 'Géolocalisation' détectée !");
		} else {
			throw new IllegalArgumentException("Page inconnue : " + nomPage);
		}
	}

	public void RemplirAdresse(String add) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Utilise un XPath plus souple et résilient
		By inputAdresse = By.xpath("//input[contains(@placeholder, 'Entrez votre adresse')]");

		WebElement adresseInput = wait.until(ExpectedConditions.visibilityOfElementLocated(inputAdresse));

		// Vérifie que le champ est cliquable (optionnel mais plus sûr)
		wait.until(ExpectedConditions.elementToBeClickable(adresseInput));

		adresseInput.clear(); // facultatif, utile si un texte est déjà présent
		adresseInput.sendKeys(add);

	}

	public void RemplirVille(String vill) {
		Config.attente(10);
		ville.sendKeys(vill);
	}

	public void RemplirCodePostal(String code) {

		codePostal.sendKeys(code);
	}

	public void selectZone(String text) {
		Select select = new Select(zoneDropdown);
		select.selectByVisibleText(text);
	}

}
