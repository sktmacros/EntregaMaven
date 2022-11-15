
Feature: Recuperar contraseña


  @test
  Scenario: Se debe poder acceder a la seccion de recuperar contraseña
    Given Estoy en el stio de logeo
    Then Cargo la informacion del DOM recuperarcontrasena.json
    And debo hacer click en el linkpassword
    And debo hacer click para cerrarmensaje
    And debo escribir el Email lo siguiente yonoma92@yopmail.com
    And debo presionar en el boton
