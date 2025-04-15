package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuDeJour {

	@FindBy(xpath="/html/body/div[1]/div/div[1]/div/div/div[2]/h1")
	WebElement HomeDashboardMessage ;
	
	
	
	 public MenuDeJour(WebDriver driver) {
		  PageFactory.initElements(driver, this);
	  }
	  public String verifMessage() {
		String actualMessage = HomeDashboardMessage.getText();
		return actualMessage ;
	}
}
