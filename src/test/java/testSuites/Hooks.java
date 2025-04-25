

	package testSuites;

	import org.openqa.selenium.By;

import helper.Config;
import io.cucumber.java.After;
import io.cucumber.java.Before;

	public class Hooks {

	    @Before
	    public void setUp() throws InterruptedException {
	        System.out.println("🔧 [Before] Lancement du navigateur...");
	        Config.confChrome();
	        Config.maximazWindow();
	        Config.driver.get("http://127.0.0.1:3000/sign-in"); 
	        Thread.sleep(1000);
	        Config.driver.findElement(By.xpath("/html/body/div[2]/div/button")).click();
	    }

	    @After
	    public void tearDown() {
	        System.out.println("🧹 [After] Fermeture du navigateur...");
	        if (Config.driver != null) {
	            Config.driver.quit();
	        }
	    }
	}


