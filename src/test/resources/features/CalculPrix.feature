#Author: Syrine Khemiri
Feature: Calcul du total après sélection de plusieurs plats

  Scenario: Calcul du total après sélection de plusieurs plats
    Given user est connecté avec "abdel.d.hilali@gmail.com" et "abdel123"
    And user est sur la page "Menu de la semaine"
    And user clique sur "vendredi 25/04"
    When il sélectionne 2 plats principal "Soupe de Tomate Crémeuse" à 18 TND le plat
    And il sélectionne 4 salade "Salade de Chèvre Chaude Miniature" à 19 TND  le plat
    And il sélectionne 3 sucré "Gâteau au Chocolat Fondant" à 8 TND le plat
    Then le total affiché doit être "136.0"


   
