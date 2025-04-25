package testSuites;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.Config;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CalculPrixPage;

public class CalculPrixStepDef {
	CalculPrixPage page = new CalculPrixPage(Config.driver);

	@Given("user est connecté avec {string} et {string}")
	public void user_est_connecté_avec_et(String email, String password) throws InterruptedException {
		
		    Config.driver.get("http://localhost:3000/sign-in");
		    Thread.sleep(3000);
		    Config.driver.findElement(By.xpath("/html/body/div[2]/div/button")).click();
		    Config.driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[3]/div/div/div/div/form/div[1]/div/input")).sendKeys(email);
		    Config.driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[3]/div/div/div/div/form/div[2]/div/input")).sendKeys(password);
		    Config.driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[3]/div/div/div/div/form/button")).click();
		    
		    // Attendre que la connexion soit effectuée
		    WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.urlContains("http://localhost:3000/"));
		}
	

@Given("user est sur la page {string}")
public void user_est_sur_la_page(String pageName) {
    String expectedUrl = "";
    switch(pageName) {
        case "Menu de la semaine":
            expectedUrl = "http://localhost:3000/menu";
            break;
        // Ajoutez d'autres cas au besoin
    }
    WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.urlContains(expectedUrl));
    assertTrue(Config.driver.getCurrentUrl().contains(expectedUrl));
}
   
@Given("user clique sur {string}")
public void user_clique_sur(String string) throws InterruptedException {
    Config.driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div[1]/button[5]")).click();
    Thread.sleep(1000);
}
@When("il sélectionne {int} plats principal {string} à {int} TND le plat")
public void il_sélectionne_plats_principal_à_tnd_le_plat(Integer quantité, String nomPlat, Integer prix) throws InterruptedException {
   page.selectionnerPlat(nomPlat, quantité);
}


@When("il sélectionne {int} salade {string} à {int} TND  le plat")
public void il_sélectionne_salade_à_tnd_le_plat(Integer quantité, String nomSalade, Integer prix) throws InterruptedException {
  page.cliquerSurPlatPrincipalSiPasDéjàFait();
  page.selectionnerSalade(nomSalade, quantité);
}
@When("il sélectionne {int} sucré {string} à {int} TND le plat")
public void il_sélectionne_sucré_à_tnd_le_plat(Integer quantité, String nomDessert, Integer prix) throws InterruptedException {
    page.cliquerSurPlatPrincipalSiPasDéjàFait();
    page.selectionnerDessert(nomDessert, quantité);
}

	@Then("le total affiché doit être {string}")
	public void le_total_affiché_doit_être(String total) {
	page.verifierTotalGlobal(total);
	}
	
}
