package StepDefinitions;

import Functions.CreateDriver;
import Functions.SeleniumFunctions;
import gherkin.lexer.Th;
import io.cucumber.java.en.And;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import io.cucumber.junit.Cucumber;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;


public class StepDefinitions {
    SeleniumFunctions functions = new SeleniumFunctions();

    private static Properties pro = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("/test.properties");

    WebDriver driver;


    public StepDefinitions() {
        driver = Hooks.driver;

    }

    Logger log = Logger.getLogger(StepDefinitions.class);

    @Given("^Estoy en el stio de logeo$")
    public void Estoyenelstiodelogeo() throws IOException {

        pro.load(in);
        String url = pro.getProperty("MainAppUrlBase");
        log.info(("Navegar a:" + url));
        driver.get(url);

    }


    @Then("^Cargo la informacion del DOM (.*)$")
    public void cargoLaInformacionDelDOM(String file) throws Exception {
        SeleniumFunctions.FileName = file;
        SeleniumFunctions.readJson();
        log.info("Inicializa el archivo: " + file);
        Thread.sleep(8);
    }

    @And("^debo hacer clic en el campo (.*)$")
    public void deboHacerClicEnElCampo(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        functions.waitForElementPresent(element);
        driver.findElement(SeleniumElement).click();
        log.info("clic en el elemento" + element);

    }


    @And("^debo escribir el (.*) lo siguiente (.*)$")
    public void deboEscribirElLoSiguiente(String element, String text) throws Exception {
        Thread.sleep(8000);
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).sendKeys(text);


    }


    @And("^debo escribir la (.*) que sea (.*)$")
    public void deboEscribirLaQueSea(String element, String text) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).sendKeys(text);
        Thread.sleep(8000);
    }


    @And("debo presionar en el (.*)$")
    public void deboPresionarEnEl(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).click();
        log.info("clic en el elemento" + element);
        Thread.sleep(2000);


    }


    @And("tomo capura captura (.*?)$")
    public void tomoCapuraCaptura(String name) throws IOException {
        functions.ScreenShot(name);
    }


    @And("tomo captura de pantalla (.*)$")
    public void tomoCapturaDePantalla(String name) throws Exception {
        functions.attachScreentShot(name);

    }


    @And("debo validar error de (.*)$")
    public void deboValidarErrorDe(String element) throws Exception {
        functions.waitForElementPresent(element);
    }





    @Then("Confimar si el (.*) contiene esto (.*)$")
    public void confimarSiElContieneEsto(String element, String text) throws Exception{
     functions.checkPartialTextElementPresent(element, text);
    }





    @And("me desplazo al elemento (.*)$")
    public void meDesplazoAlElemento(String element) throws Exception {
        functions.scrollToElement(element);
    }


    @And("validar elemento (.*)$")
        public void validarElemento(String element) throws Exception {
        functions.waitForElementVisible(element);
    }




    @And("debo hacer click en el (.*)$")
    public void deboHacerClickEnEl(String element) throws Exception {
        Thread.sleep(8);
        functions.ClickJSElement(element);


    }



    }




