Feature: Se debe validar que la contraseña sea debil

  @test
  Scenario: Validar contrasena debil
    Given Estoy en el stio de logeo
    Then Cargo la informacion del DOM registroPassDebil.json
    And debo hacer clic en el campo Email
    And debo escribir el Email lo siguiente yonoma93@yopmail.com
    And debo escribir la contrasena que sea dsa1
    And validar elemento mensaje
    Then Confimar si el mensaje contiene esto Weak - Please enter a stronger password.
