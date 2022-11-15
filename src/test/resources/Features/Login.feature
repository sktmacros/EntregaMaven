Feature: Login con usuario y contraseña


  @test
    @registro
  Scenario: Cliente se debe logear exitosamente
    Given Estoy en el stio de logeo
    Then Cargo la informacion del DOM login.json
    And debo hacer clic en el campo Email
    And debo escribir el Email lo siguiente yonoma92@yopmail.com
    And debo escribir la contrasena que sea 1lb3rto1234
    And debo presionar en el boton
    And tomo capura captura login
    And tomo captura de pantalla login
