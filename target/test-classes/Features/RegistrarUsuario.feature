Feature: Registrar usuario con correo y contraseña

@test
    @registro
    Scenario: Cliente debe registrarse exitosamente
    Given Estoy en el stio de logeo
    Then Cargo la informacion del DOM registro.json
    And debo hacer clic en el campo Email
    And debo escribir el Email lo siguiente yonoma100@yopmail.com
    And debo escribir la contrasena que sea 1lb3rto1234
    And debo presionar en el boton







