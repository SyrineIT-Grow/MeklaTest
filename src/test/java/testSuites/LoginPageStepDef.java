package testSuites;

import  org.junit.Assert;
import helper.Config;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.MenuDeJour;

public class LoginPageStepDef {
	
	LoginPage page = new LoginPage(Config.driver);
	MenuDeJour M = new MenuDeJour(Config.driver);

    
	@Given("admin is on login Page")
	public void admin_is_on_login_page() {
		  page = new LoginPage(Config.driver);
	        M = new MenuDeJour(Config.driver);
	   Config.driver.get("http://127.0.0.1:3000/sign-in");
	}

	@When("admin enter correct Email {string} and correct password {string}")
	public void admin_enter_correct_email_and_correct_password(String Email, String password) {
	 
	   page.connect(Email, password);
	}

	@Then("admin is directed to Menu de la semaine Page that contains {string}")
	public void admin_is_directed_to_menu_de_la_semaine_page_that_contains(String menu) {
	    // de la méthode verifPage() pour récupérer le texte réel de la page
	   String actualMessage = page.verifPage();

	  //   Assertion pour comparer le texte récupéré avec celui attendu
	  Assert.assertEquals(menu, actualMessage);
	}

	@When("admin enter azert@gmail.com and m123")
	public void admin_enter_azert_gmail_com_and_m123(String email, String pwd) {
	    page.connect(email, pwd);
	}

	@Then("admin still on login page that contains message {string}")
	public void admin_still_on_login_page_that_contains_message(String msg) {
	 String actualMessage = page.verifPage();
	 Assert.assertEquals(msg, actualMessage);
	}

	@When("admin enter aetrbt and azetj")
	public void admin_enter_aetrbt_and_azetj(String Mail,String pw) {
	    page.connect(Mail, pw);
	}

	@When("admin enter  and ")
	public void admin_enter_and(String M1,String P1) {
		page.connect(M1, P1);
	    
	}



	
	
}
