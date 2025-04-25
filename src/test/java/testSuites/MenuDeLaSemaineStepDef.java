package testSuites;

import helper.Config;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MenuDeLaSemaine;

public class MenuDeLaSemaineStepDef {

	MenuDeLaSemaine Menu = new MenuDeLaSemaine(Config.driver);

	@Given("user est sur la page Menu de la semaine")
	public void user_est_sur_la_page_menu_de_la_semaine() {
		Menu = new MenuDeLaSemaine(Config.driver);
		Config.driver.get("http://localhost:3000/menu");
	}

	@Given("il sélectionne le jour {string}")
	public void il_sélectionne_le_jour(String jour) {
		Menu.selectionJour(jour);
	}

	@When("il séléctionne {string}")
	public void il_séléctionne(String platPrincipal) {
		Menu.selectionnerPlatParNom(platPrincipal);
	}

	@When("il sélectionne {string}")
	public void il_sélectionne(String EnCas) {
		Menu.selectionnerPlatParNom(EnCas);
	}

	@When("il selectionne {string} ")
	public void Il_selectionne(String salad) {
		Menu.selectionnerPlatParNom(salad);
	}

	@When("il clique sur Suivant")
	public void il_clique_sur_suivant() {
		Menu.Click();
	}

	@Then("il est redérigé vers la page {string}")
	public void il_est_redérigé_vers_la_page(String string) {
		Menu.VerifPage();
	}

}
