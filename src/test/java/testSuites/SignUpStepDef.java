package testSuites;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helper.Config;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.SignUp;

public class SignUpStepDef {

	SignUp Sign = new SignUp(Config.driver);

	@Given("User est sur la page {string}")
	public void user_est_sur_la_page(String string) {
		Sign = new SignUp(Config.driver);
		Config.driver.get("http://localhost:3000/sign-up");
	}

	@When("il sélectionne option {string}")
	public void option(String objectif) {
		Sign.ChoisirObjectifAlimentaire(objectif);
	}

	@When("il clique sur le bouton Suivant")
	public void bouton_suivant() {
		Sign.Click();
	}

	@Then("il est redirigé vers la page {string}")
	public void il_est_redirigé_vers_la_page(String string) {

		Sign.VerifPage1();
	}

	@When("il sélectionne  option {string}")
	public void il_sélectionne_option(String Option) {
		Sign.ChoisirSport(Option);
	}

	@When("il clique sur le  bouton Suivant")
	public void il_clique_sur_le_bouton_suivant() {
	Sign.Click();
	}

	@When("il sélectionne  allergie {string}")
	public void il_sélectionne_allergie(String Allergie) {
		Sign.Allergie(Allergie);
		Sign.Click();
	}

	
	
	@When("il saisit adresse {string}")
	public void il_saisit_adresse(String add) throws InterruptedException {
		Sign.user_est_sur_la_page("Géolocalisation");
		// Debug : attendre un peu pour voir si l’input se charge
		Thread.sleep(5000);
		List<WebElement> inputs = Config.driver.findElements(By.xpath("//input"));
		System.out.println("🔍 Nombre de champs input : " + inputs.size());

		for (WebElement input : inputs) {
		    System.out.println("📝 placeholder : " + input.getAttribute("placeholder"));
		}
		

		Thread.sleep(1000);
	    Sign.RemplirAdresse(add);
	}

	@When("il saisit la ville {string}")
	public void il_saisit_la_ville(String vill) {
	   Sign.RemplirVille(vill);
	}

	@When("il sélectionne la zone {string}")
	public void il_sélectionne_la_zone(String Zone) {
	    Sign.selectZone(Zone);
	}

	@When("il saisit le code postal {string}")
	public void il_saisit_le_code_postal(String code) {
	  Sign.RemplirCodePostal(code);
	}
	@When("il clique sur le  bouton suivant")
	public void il_clique_sur_le_boutonSuivant() {
	Sign.Click();
	}
}
