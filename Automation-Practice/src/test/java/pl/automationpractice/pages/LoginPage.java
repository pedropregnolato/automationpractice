package pl.automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pl.automationpractice.BasePage;

public class LoginPage extends BasePage {

    private By emailField = By.id("email");
    private By passwordField = By.id("passwd");
    private By submitLoginButton = By.id("SubmitLogin");


    public LoginPage(WebDriver navegador) {
        super(navegador);
    }

    public void campoEmail(String email) {
        navegador.findElement(emailField).sendKeys(email);
    }

    public void campoPassword(String password) {
        navegador.findElement(passwordField).sendKeys(password);
    }

    public void botaoSignIn() {
        navegador.findElement(submitLoginButton).click();
    }

    public void voltarHomePage() {
        navegador.findElement(By.id("header_logo")).click();
    }
}