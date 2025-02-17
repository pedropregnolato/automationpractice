package pl.automationpractice.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pl.automationpractice.SensitiveData;
import pl.automationpractice.pages.HomePage;
import pl.automationpractice.pages.LoginPage;
import java.time.Duration;


@DisplayName("Teste automatizado da tela de login")
public class LoginPageTests {

    private WebDriver navegador;
    private final String url = "https://www.automationpractice.pl/index.php";
    private HomePage homePage;
    private LoginPage loginPage;
    private SensitiveData sensitiveData;

    @BeforeEach
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

    @Test
    @DisplayName("Validar login usuário com dados válidos")
    public void validarLogin() {

        homePage.accessUrlHome(url);  //acessar o site
        homePage.acessarTelaLogin();  //acessar tela login
        loginPage.campoEmail(sensitiveData.email);  //acessar campo Email address
        loginPage.campoPassword(sensitiveData.password);  //acessar campo Password
        loginPage.botaoSignIn();  //clicar no Sign in

        //validar login com sucesso
        String welcomeAccount = navegador.findElement(By.className("info-account")).getText();
        Assertions.assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.", welcomeAccount);

        //voltar para homepage
        //loginPage.voltarHomePage();
    }

    @Test
    @DisplayName("Validar login usuário com dados inválidos")
    public void validarLoginDadosIncorretos() {


        homePage.accessUrlHome(url);  //acessar o site
        homePage.acessarTelaLogin();  //acessar tela login
        loginPage.campoEmail(sensitiveData.wrongEmail);  //acessar campo Email address
        loginPage.campoPassword(sensitiveData.wrongPassword);  //acessar campo Password
        loginPage.botaoSignIn();  //clicar no Sign in

        //validar login sem sucesso - utilizando xpath para validar mensagem de erro "Authentication failed."
        String falhaLogin = navegador.findElement(By.xpath("//li[text()=\"Authentication failed.\"]")).getText();
        Assertions.assertEquals("Authentication failed.", falhaLogin);
    }

    @Test
    @DisplayName("Validar login campo email invalido")
    public void validarLoginEmailInvalido() {
        homePage.accessUrlHome(url);
        homePage.acessarTelaLogin();
        loginPage.campoEmail("fbHBF");
        loginPage.campoPassword(sensitiveData.password);
        loginPage.botaoSignIn();

        //validar login sem sucesso - utilizando xpath para validar mensagem de erro "Invalid email address."
        String falhaLogin = navegador.findElement(By.xpath("//li[text()=\"Invalid email address.\"]")).getText();
        Assertions.assertEquals("Invalid email address.", falhaLogin);

    }


    @Test
    @DisplayName("Validar login usuário sem preencher nenhum campo")
    public void validarLoginSemPreencher() {

        homePage.accessUrlHome(url);
        homePage.acessarTelaLogin();
        loginPage.botaoSignIn();

        //validar login sem sucesso - utilizando xpath para validar mensagem de erro "An email address required."
        String falhaLogin = navegador.findElement(By.xpath("//li[text()=\"An email address required.\"]")).getText();
        Assertions.assertEquals("An email address required.", falhaLogin);
    }


    @AfterEach
    public void tearDown() {
        //fechar navegador
        if (navegador != null) {
            navegador.quit();
        }
    }

}