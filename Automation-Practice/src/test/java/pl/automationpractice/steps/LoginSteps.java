package pl.automationpractice.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pl.automationpractice.SensitiveData;
import pl.automationpractice.pages.HomePage;
import pl.automationpractice.pages.LoginPage;
import java.time.Duration;

public class LoginSteps {

    private WebDriver navegador;
    private final String url = "https://www.automationpractice.pl/index.php";
    private HomePage homePage;
    private LoginPage loginPage;
    private SensitiveData sensitiveData;

    @Before
    public void setUp() {
        //abrir navegador
        WebDriverManager.edgedriver().setup();
        navegador = new EdgeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        //instanciando homepage como objeto
        homePage = new HomePage(navegador);
        //instanciando loginpage como objeto
        loginPage = new LoginPage(navegador);
        //instanciando dadossensiveis como objeto
        sensitiveData = new SensitiveData();
    }

    @Given("que o usuário acessa a página inicial")
    public void que_o_usuário_acessa_a_página_inicial() {
        homePage.accessUrlHome(url);
    }
    @When("ele clica no botão de login")
    public void ele_clica_no_botão_de_login() {
        homePage.acessarTelaLogin();
    }
    @When("insere o email {string}")
    public void insere_o_email(String email) {
        loginPage.campoEmail(sensitiveData.email);
    }
    @When("insere a senha {string}")
    public void insere_a_senha(String password) {
        loginPage.campoPassword(sensitiveData.password);
    }
    @When("clica no botão Sign In")
    public void clica_no_botão_sign_in() {
        loginPage.botaoSignIn();
    }
    @Then("ele deve ver a mensagem {string}")
    public void ele_deve_ver_a_mensagem(String string) {
        String welcomeAccount = navegador.findElement(By.className("info-account")).getText();
        Assertions.assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.", welcomeAccount);
    }
    @When("insere o email invalido {string}")
    public void insere_o_email_invalido(String email) {
        loginPage.campoEmail(email);
    }
    @When("insere a senha invalida {string}")
    public void insere_a_senha_invalida(String password) {
        loginPage.campoPassword(password);
    }
    @Then("ele deve ver a mensagem de erro {string}")
    public void ele_deve_ver_a_mensagem_de_erro(String mensagemErro) {
        String falhaLogin = navegador.findElement(By.xpath("//li[text()='"+mensagemErro+"']")).getText();
        Assertions.assertEquals(mensagemErro, falhaLogin);
    }



    @After
    public void tearDown() {
        //fechar navegador
        if (navegador != null) {
            navegador.quit();
        }
    }

}
