#Author: Syrine Khemiri
Feature: Créer un compte

  Scenario Outline: Choisir un objectif alimentaire
    Given User est sur la page "Objectif alimentaire"
    When il sélectionne option "<objectif>"
    And il clique sur le bouton Suivant
    Then il est redirigé vers la page "Activité physique"

    Examples: 
      | objectif                 |
      | Perte de poids           |
      | Réalignement alimentaire |
      | Prise de masse           |
  Scenario Outline: Choisir activité physique
    Given User est sur la page "Activité physique"
    When il sélectionne  option "<sport>"
    And il clique sur le  bouton Suivant
    Then il est redirigé vers la page "Allergies et spécificité"

    Examples: 
      | sport                       |
      | Je pratique le sport        |
      | Je ne pratique pas le sport |

  Scenario Outline: Choisir allergie
    Given User est sur la page "Allergies et spécificité"
    When il sélectionne option "<allergie>"
    And il clique sur le bouton Suivant
    Then il est redirigé vers la page "Géolocalisation"

    Examples: 
      | allergie                      |
      | Non, je n’ai pas d’allergies. |
      | Oui, j’ai des allergies.      |
      
      Scenario Outline: Saisir les champs de géolocalisation
    Given User est sur la page "Géolocalisation"
    When il saisit adresse "<adresse>"
    And il saisit la ville "<ville>"
    And il sélectionne la zone "<zone>"
    And il saisit le code postal "<codePostal>"
    And il clique sur le bouton Suivant
    Then il est redirigé vers la page "Parrainage"

    Examples: 
      | adresse          | ville  | zone                  | codePostal |
      | 10 rue des Lilas | Nasr   | Sélectionnez une zone |       3637 |
      | 25 bd de Metz    | Lac1   | El Manzeh             |       3031 |
      |                  | aouina | Lac de Tunis          |       1234 |
      | rue liberté      |        | El Nasr               |       7894 |
 
      



