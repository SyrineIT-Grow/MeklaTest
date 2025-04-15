

	package testSuites;

	import helper.Config;
	import io.cucumber.java.After;
	import io.cucumber.java.Before;

	public class Hooks {

	    @Before
	    public void setUp() {
	        System.out.println("🔧 [Before] Lancement du navigateur...");
	        Config.confChrome();
	        Config.maximazWindow();
	        Config.driver.get("http://127.0.0.1:3000/sign-in"); // Remplace avec ton URL exacte
	    }

	    @After
	    public void tearDown() {
	        System.out.println("🧹 [After] Fermeture du navigateur...");
	        if (Config.driver != null) {
	            Config.driver.quit();
	        }
	    }
	}


