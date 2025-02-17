package pl.automationpractice.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/pl/automationpractice/features",
        glue = "pl/automationpractice/steps",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)

public class TestRunner {
}