#Author:Syrine Khemiri
Feature: Mekla Login Page

  Background: 
    Given admin is on login Page

  Scenario: Login with valid credentials
    When admin enter correct Email "hilali.d.abdel@gmail.com" and correct password "abdel123"
    Then admin is directed to Menu de la semaine Page that contains "Menu de la semaine"

  Scenario Outline: Login with invalid credentials
    When admin enter "<Email>" and "<password>"
    Then admin still on login page that contains message "Authentication failed"

    Examples: 
      | Email           | password |
      | azert@gmail.com | m123     |
      | aetrbt@yrdc.fe  | azetj    |
