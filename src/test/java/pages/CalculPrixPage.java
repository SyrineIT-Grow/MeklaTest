package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculPrixPage {

	WebDriverWait wait;
	static WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public CalculPrixPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void selectionnerPlat(String nomPlat, int quantiteSouhaitee) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// 1. Trouver tous les éléments contenant des noms de plats
		List<WebElement> plats = driver.findElements(By.cssSelector("span.text-sm.font-medium"));
		WebElement platCible = null;

		for (WebElement p : plats) {
			if (p.getText().trim().equalsIgnoreCase(nomPlat)) {
				platCible = p;
				break;
			}
		}

		if (platCible == null) {
			throw new NoSuchElementException("Plat non trouvé : " + nomPlat);
		}

		// 2. Scroll jusqu'au plat et cliquer sur son label (si besoin)
		js.executeScript("arguments[0].scrollIntoView(true);", platCible);

		WebElement label = platCible.findElement(By.xpath("./ancestor::label"));
		js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", label);
		wait.until(ExpectedConditions.elementToBeClickable(label));
		try {
			label.click();
		} catch (ElementClickInterceptedException e) {
			System.out.println("Clic intercepté, utilisation du JavaScript...");
			js.executeScript("arguments[0].click();", label);

		}

		// 3. Récupérer le conteneur du bloc

		WebElement conteneur = label.findElement(
				By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[2]/label/span"));

		WebElement boutonPlus = conteneur.findElement(By
				.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div/div/button[2]"));
		WebElement champQuantite = conteneur.findElement(
				By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div/div/span"));

		// 4. Lire la quantité actuelle
		String valeurQuantite = champQuantite.getAttribute("value");
		if (valeurQuantite == null || valeurQuantite.isEmpty()) {
			valeurQuantite = champQuantite.getText();
		}

		int quantiteActuelle = Integer.parseInt(valeurQuantite.trim());

		// 5. Cliquer sur le + jusqu’à atteindre la quantité souhaitée
		while (quantiteActuelle < quantiteSouhaitee) {
			boutonPlus.click();
			Thread.sleep(300); // Remplaçable par WebDriverWait si besoin
			valeurQuantite = champQuantite.getAttribute("value");
			if (valeurQuantite == null || valeurQuantite.isEmpty()) {
				valeurQuantite = champQuantite.getText();
			}
			quantiteActuelle = Integer.parseInt(valeurQuantite.trim());
		}
	}

	private boolean platPrincipalClique = false;

	public void cliquerSurPlatPrincipalSiPasDéjàFait() {
		if (!platPrincipalClique) {
			// Trouver la checkbox du plat principal
			WebElement checkboxPlatPrincipal = driver.findElement(By
					.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/label/div/input"));

			// Vérifier si la checkbox est déjà cochée
			boolean estCochee = checkboxPlatPrincipal.isSelected();

			// Si la checkbox n'est pas cochée, on clique dessus
			if (!estCochee) {
				checkboxPlatPrincipal.click(); // Coche la checkbox
				System.out.println("✅ Checkbox du plat principal cochée !");
			} else {
				System.out.println("✅ La checkbox du plat principal est déjà cochée.");
			}

			platPrincipalClique = true; // Marquer que le plat principal a été cliqué
			System.out.println("✅ Bouton 'Plat principal' cliqué !");
		}
	}

	public void selectionnerSalade(String nomSalade, int quantiteSouhaitee) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Trouver le plat par nom
		List<WebElement> plats = driver.findElements(
				By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/div/div/div[2]/label/span"));
		WebElement platCible = null;

		for (WebElement p : plats) {
			if (p.getText().trim().equalsIgnoreCase(nomSalade)) {
				platCible = p;
				break;
			}
		}

		if (platCible == null) {
			throw new NoSuchElementException("Plat non trouvé : " + nomSalade);
		}

		// Scroll vers le plat + click via JS si intercepté
		WebElement label = platCible.findElement(By.xpath("./ancestor::label"));
		js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -150);", label);
		wait.until(ExpectedConditions.elementToBeClickable(label));

		try {
			label.click();
		} catch (ElementClickInterceptedException e) {
			System.out.println("⛔ Clic intercepté sur la salade, JS utilisé !");
			js.executeScript("arguments[0].click();", label);
		}
		// 3. Récupérer le conteneur du bloc

		WebElement conteneur = label.findElement(By.xpath(
				"/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/div[2]/div/div/div[2]/label/span"));

		WebElement boutonPlus = conteneur.findElement(By.xpath(
				"/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/div[1]/div/div[1]/div/div/button[2]"));
		WebElement champQuantite = conteneur.findElement(By
				.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/div/div/div[1]/div/div/span"));

		// 4. Lire la quantité actuelle
		String valeurQuantite = champQuantite.getAttribute("value");
		if (valeurQuantite == null || valeurQuantite.isEmpty()) {
			valeurQuantite = champQuantite.getText();
		}

		int quantiteActuelle = Integer.parseInt(valeurQuantite.trim());

		// 5. Cliquer sur le + jusqu’à atteindre la quantité souhaitée
		for (int i = 1; i < quantiteSouhaitee; i++) {
			try {
				// Rechercher le bouton plus
				WebElement boutonPlus1 = driver.findElement(By.xpath(
						"/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/div[1]/div/div[1]/div/div/button[2]"));

				// Attendre que le bouton soit cliquable et visible
				wait.until(ExpectedConditions.elementToBeClickable(boutonPlus1));

				// Vérifier et supprimer la classe 'disabled' si elle existe
				js.executeScript(
						"if (arguments[0].classList.contains('disabled:opacity-50')) {arguments[0].classList.remove('disabled:opacity-50');}",
						boutonPlus1);

				// Attendre que le bouton soit bien visible et dans la bonne position
				js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -150);", boutonPlus1);

				// Forcer le clic avec JavaScript
				js.executeScript("arguments[0].click();", boutonPlus1);

				// Attendre un peu pour s'assurer que l'action se termine correctement
				Thread.sleep(1000);

				System.out.println("✅ Clic sur le bouton '+' exécuté");

			} catch (Exception e) {
				System.out.println("⛔ Erreur pendant le clic JS: " + e.getMessage());
			}
		}

		System.out.println("✅ " + quantiteSouhaitee + " x '" + nomSalade + "' sélectionnés.");

	}

	public void selectionnerDessert(String nomDessert, int quantiteSouhaitee) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Localiser les desserts
		List<WebElement> desserts = driver.findElements(
				By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/div/div/label/span"));
		WebElement dessertCible = null;

		for (WebElement d : desserts) {
			if (d.getText().trim().equalsIgnoreCase(nomDessert)) {
				dessertCible = d;
				break;
			}
		}

		if (dessertCible == null) {
			throw new NoSuchElementException("Dessert non trouvé : " + nomDessert);
		}

		WebElement label = dessertCible.findElement(By.xpath("./ancestor::label"));
		js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -150);", label);
		wait.until(ExpectedConditions.elementToBeClickable(label));

		try {
			label.click();
		} catch (ElementClickInterceptedException e) {
			System.out.println("⛔ Clic intercepté sur le dessert, JS utilisé !");
			js.executeScript("arguments[0].click();", label);
		}

		WebElement conteneur = label.findElement(
				By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/div/div/label/span")); 
		WebElement boutonPlus = conteneur.findElement(By.xpath(
				"/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/div[2]/div/div/div/div/div/button[2]/span")); 
		WebElement champQuantite = conteneur.findElement(By
				.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/div/div/div/div/span")); 

		// 5. Cliquer sur le + jusqu’à atteindre la quantité souhaitée
		for (int i = 1; i < quantiteSouhaitee; i++) {
			try {
				// Rechercher le bouton plus
				WebElement boutonPlus1 = driver.findElement(By.xpath(
						"/html/body/div[1]/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/div[2]/div/div/div/div/div/button[2]/span"));

				// Attendre que le bouton soit cliquable et visible
				wait.until(ExpectedConditions.elementToBeClickable(boutonPlus1));

				// Vérifier et supprimer la classe 'disabled' si elle existe
				js.executeScript(
						"if (arguments[0].classList.contains('disabled:opacity-50')) {arguments[0].classList.remove('disabled:opacity-50');}",
						boutonPlus1);

				// Attendre que le bouton soit bien visible et dans la bonne position
				js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -150);", boutonPlus1);

				// Forcer le clic avec JavaScript
				js.executeScript("arguments[0].click();", boutonPlus1);

				// Attendre un peu pour s'assurer que l'action se termine correctement
				Thread.sleep(1000);

				System.out.println("✅ Clic sur le bouton '+' exécuté");

			} catch (Exception e) {
				System.out.println("⛔ Erreur pendant le clic JS: " + e.getMessage());
			}
		}

		System.out.println("✅ " + quantiteSouhaitee + " x '" + nomDessert + "' sélectionnés.");
	}

	public void verifierTotalGlobal(String totalAttendu) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement totalElement = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[3]/div")));

		String totalText = totalElement.getText().replace("Total:", "").replace("TND", "").trim();
		double totalAffiche = Double.parseDouble(totalText);
		double totalAttenduDouble = Double.parseDouble(totalAttendu);

		System.out.println("🧾 Total affiché : " + totalAffiche + " TND");
		System.out.println("🎯 Total attendu : " + totalAttenduDouble + " TND");

		Assertions.assertEquals(totalAttenduDouble, totalAffiche, "❌ Le total affiché est incorrect !");
	}

}
