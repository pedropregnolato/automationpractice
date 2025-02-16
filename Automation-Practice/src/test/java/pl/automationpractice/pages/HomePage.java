package pl.automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pl.automationpractice.BasePage;

public class HomePage extends BasePage {

    public HomePage(WebDriver navegador) {
        super(navegador);
    }

    public void accessUrlHome(String url) {
        navegador.get(url);
    }

    public void acessarTelaLogin() {
        navegador.findElement(By.className("login")).click();
    }


}
