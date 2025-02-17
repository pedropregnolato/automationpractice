Feature: Testes de login no site Automation Practice

  Scenario: Validar login com dados válidos
    Given que o usuário acessa a página inicial
    When ele clica no botão de login
    And insere o email "email"
    And insere a senha "password"
    And clica no botão Sign In
    Then ele deve ver a mensagem "Welcome to your account. Here you can manage all of your personal information and orders."

  Scenario: Validar login com dados inválidos
    Given que o usuário acessa a página inicial
    When ele clica no botão de login
    And insere o email invalido "wrongEmail@test.com"
    And insere a senha invalida "wrongPassword"
    And clica no botão Sign In
    Then ele deve ver a mensagem de erro "Authentication failed."

  Scenario: Validar login com email inválido
    Given que o usuário acessa a página inicial
    When ele clica no botão de login
    And insere o email invalido "fbHBF"
    And insere a senha invalida "senha123"
    And clica no botão Sign In
    Then ele deve ver a mensagem de erro "Invalid email address."

  Scenario: Validar login sem preencher nenhum campo
    Given que o usuário acessa a página inicial
    When ele clica no botão de login
    And clica no botão Sign In
    Then ele deve ver a mensagem de erro "An email address required."
