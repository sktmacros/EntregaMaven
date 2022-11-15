package StepDefinitions;

import Functions.CreateDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;



import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;


public class Hooks {
    public static WebDriver driver;
    Logger log = Logger.getLogger(Hooks.class);
    Scenario scenario = null;
    @Before
    public void initDriver(Scenario scenario) throws IOException{
        log.info("#######");
        log.info("[ Configuration ] - Inicializando la configuracion del controlador");
        log.info("#######");
        driver = CreateDriver.initConfig();
        this.scenario = scenario;
        log.info("#######");
        log.info("[ Scenario] - " + scenario.getName());
        log.info("#######");
    }
    @After
    public void embedScreenshot(Scenario scenario) throws IOException{
       if(scenario.isFailed()){
            try{
                Class<Scenario> scenarioClass = Scenario.class;
                String s = "El escenario falló";
                scenario.wait(Long.parseLong("La URL de la pagina actual es " + driver.getCurrentUrl()));
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                byte[] pngBytes = new byte[0];
                scenario.attach(pngBytes, String.valueOf(screenshot), "src/test/resources/Data/Screenshots/Failed");

            }catch (WebDriverException somePlatformsDontSupportScreenshots){
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
       }
        log.info("#######");
        log.info("[ Driver Status ] - Limpiar y cerrar la instancia del controlador");
        log.info("#######");
        driver.quit();

    }


}
