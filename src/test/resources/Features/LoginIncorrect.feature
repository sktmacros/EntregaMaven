Feature: Login con usuario y contraseña incorrecta


  @test
  Scenario: Se debe indicar que la contraseña es incorrecta indicando un mensaje de error
    Given Estoy en el stio de logeo
    Then Cargo la informacion del DOM loginIncorrecto.json
    And debo hacer clic en el campo Email
    And debo escribir el Email lo siguiente yonoma92@yopmail.com
    And debo escribir la contrasena que sea 1lb3rto1235
    And debo presionar en el boton
    And validar elemento mensaje
    Then Confimar si el mensaje contiene esto Error: the password you entered for the username yonoma92@yopmail.com is incorrect. Lost your password?

