#Author: Syrine Khemiri
Feature: Selection menu de la semaine

  Scenario Outline: Sélection d un plat complet
    Given user est sur la page Menu de la semaine
    And il sélectionne le jour "lundi 21/04"
    When il séléctionne "<le plat principal>"
    And il sélectionne "<salade>"
    And il sélectionne "<En-cas sucré>"
    And il clique sur Suivant
    Then il est redérigé vers la page "Emballage"

    Examples: 
      | le plat principal | salade                          | En-cas sucré            |
      | Chiken salad      | Saumon Grillé et Haricots Verts | Curry Végétarien et Riz |
      | Grilled Salmon    | Soupe au Pistou                 | Pêche Melba             |
