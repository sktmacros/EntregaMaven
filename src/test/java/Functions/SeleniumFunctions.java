package Functions;


import StepDefinitions.Hooks;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.util.calendar.Gregorian;

import javax.xml.bind.Element;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.GregorianCalendar;
import java.util.Properties;

public class SeleniumFunctions {

    public static Properties prop = new Properties();
    public static InputStream in = SeleniumFunctions.class.getResourceAsStream("../test.properties");

    public String ElementText = "";
    public static boolean isDisplayed = Boolean.parseBoolean(null);

    public String readProperties(String property) throws IOException{
        prop.load(in);
        return prop.getProperty(property);
    }



    private static final Duration EXPLICIT_TIMEOUT = Duration.ofSeconds(5);
    static WebDriver driver;

    public SeleniumFunctions() {
        driver = Hooks.driver;


    }

    private static Logger log = Logger.getLogger(SeleniumFunctions.class);
    public static String FileName = "";
    public static String PagesFilePath = "src/test/resources/Pages/";

    public static String GetFieldBy = "";
    public static String ValueToFind = "";

    /**
     *
     * @return
     * @throws Exception
     */
    public static Object readJson() throws Exception {
        FileReader reader = new FileReader(PagesFilePath + FileName);
        try {
            if (reader != null) {
                JSONParser jsonParser = new JSONParser();
                return jsonParser.parse(reader);
            } else {
                return null;
            }

        } catch (FileNotFoundException | NullPointerException e) {
            log.error("No existe archivo" + FileName);
            throw new IllegalStateException("No existe el archivo" + FileName, e);
        }
    }


    public static JSONObject ReadEntity(String element) throws Exception {
        JSONObject Entity = null;

        JSONObject jsonObject = (JSONObject) readJson();
        Entity = (JSONObject) jsonObject.get(element);
        log.info(Entity.toJSONString());
        return Entity;

    }

    public static By getCompleteElement(String element) throws Exception {
        By result = null;
        JSONObject Entity = ReadEntity(element);

        GetFieldBy = (String) Entity.get("GetFieldBy");
        ValueToFind = (String) Entity.get("ValueToFind");

        if ("id".equalsIgnoreCase(GetFieldBy)) {
            result = By.id(ValueToFind);
        } else if ("cssSelector".equalsIgnoreCase(GetFieldBy)) {
            result = By.cssSelector(ValueToFind);
        } else if ("xpath".equalsIgnoreCase(GetFieldBy)) {
            result = By.xpath(ValueToFind);
        } else if ("name".equalsIgnoreCase(GetFieldBy)) {
            result = By.name(ValueToFind);

        }
        return result;
    }

    public void selectOptionDropdownByText(String element, String option) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        log.info(String.format("Elemento de espera: %s ", element));

        Select opt = new Select(driver.findElement(SeleniumElement));
        log.info("Selenium opcion " + option + "by text");
        opt.selectByVisibleText(option);

    }

    public void selectOptionDropdownByIndex(String element, int option) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        log.info(String.format("Elemento de espera: %s ", element));

        Select opt = new Select(driver.findElement(SeleniumElement));
        log.info("Selenium opcion " + option + "by text");
        opt.selectByIndex(option);
    }

    public void selectOptionDropdownByValue(String element, String option) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        log.info(String.format("Elemento de espera: %s ", element));

        Select opt = new Select(driver.findElement(SeleniumElement));
        log.info("Selenium opcion " + option + "by text");
        opt.selectByValue(option);

    }

    //espera al elemento que esta presente y si no esta, lo espera 5 segundos.
    public void waitForElementPresent(String element) throws Exception {

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait w = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        log.info("Esperando el elemento:" + element + "Que este presente");
        w.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));
    }



   /* private Duration EXPLICIT_TIMEOUT(int i) {
        return null;*/
    @Test
    @DisplayName("esperando que se valide elemento")
        public void waitForElementVisible(String element) throws Exception {

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait w = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        log.info("Esperando el elemento:" + element + "Que este visible");
        w.until(ExpectedConditions.visibilityOfElementLocated(SeleniumElement));
    }

    public void checkCheckbox(String element) throws Exception {

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);// se toma elemento para interactuar con la funcion
        boolean isChecked = driver.findElement(SeleniumElement).isSelected();// se pregunta si esta seleccionado
        if (!isChecked) { //pregunta si no esta seleccionado
            log.info("haciendo click en la casilla de verificacion para seleccionar" + element);
            driver.findElement(SeleniumElement).click();
        }
    }

    // esta funcion pregunta si esta seleccionado haga click para deseleccionar
    public void UncheckCheckbox(String element) throws Exception {

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        boolean isChecked = driver.findElement(SeleniumElement).isSelected();
        if (isChecked) {
            log.info("haciendo click en la casilla de verificacion para seleccionar" + element);
            driver.findElement(SeleniumElement).click();
        }
    }

    public void ClickJSElement(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);// toma el elemento de la pagina
        JavascriptExecutor jse = (JavascriptExecutor) driver; //se declara la variable y se enlaza a la variable
        log.info("Click al elemento:" + element);
        jse.executeScript("arguments[0].click()", driver.findElement(SeleniumElement)); // se le dice que se ejecute el siguiente scipt y el elemento con que se quiere interactuar

    }
    public void scrollPage(String to) throws Exception{
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        if(to.equals("top")){
            log.info("Desplazarse hasta la parte superior de la pagina");
            jse.executeScript("scroll(0, 250);");
        } else if (to.equals("end")) {
            log.info("Desplazarse hasta el final de la pagina");
            jse.executeScript("scroll(0, 250);");
        }
    }
    public void ScrollToElement(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);// toma el elemento de la pagina
        JavascriptExecutor jse = (JavascriptExecutor) driver; //se declara la variable y se enlaza a la variable
        log.info("desplazarse hacia el elemento:" + element);
        jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(SeleniumElement));
    }
    public void pagesStatus(){
        String GetActual = driver.getCurrentUrl();
        System.out.println(String.format("Comprobando si la pagina esta cargada", GetActual));
        log.info(String.format("Comprobando si la pagina esta cargada", GetActual));
        new WebDriverWait(driver, EXPLICIT_TIMEOUT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

    }
    public void ScreenShot(String Testcapture) throws IOException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmm");
        String screenShotName = readProperties("ScreenShotPath") + "\\" + readProperties("browser") + "\\" + Testcapture + "_(" + dateFormat.format(GregorianCalendar.getInstance().getTime()) + ")";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        log.info("Captura de pantalla guardada como: " + screenShotName);
        FileUtils.copyFile(scrFile, new File(String.format("%s.png", screenShotName)));
    }
    public String GetTextElement(String element) throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));
        log.info(String.format("Esperando el elemento: %s", element));

        ElementText = driver.findElement(SeleniumElement).getText();
        return ElementText;

    }
    public void checkPartialTextElementNotPresent(String element, String text) throws Exception{
        ElementText = GetTextElement(element);
        boolean isFoundFalse = ElementText.indexOf(text) !=-1? true: false;
        Assert.assertFalse("El texto esta presente en el elemento: " + element + "el texto actual es: " + ElementText, isFoundFalse);
    }
    public void checkPartialTextElementPresent(String element, String text) throws Exception{
        ElementText = GetTextElement(element);
        boolean isFound = ElementText.indexOf(text) !=-1? true: false;
        Assert.assertTrue("El texto no está presente en el elemento: " + element + "el texto actual es: " + ElementText, isFound);
    }
    public void checkTextElementEqualTo(String element, String text) throws Exception{
        ElementText = GetTextElement(element);
        Assert.assertEquals("El texto está presente en el elemento: " + element + "el texto actual es: " + ElementText, text, ElementText);
    }
    public boolean isElementDisplayed(String element) throws Exception{

        try{
            By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
            log.info(String.format("Elemento de espera: %s", element));
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            isDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement)).isDisplayed();

        }catch (NoSuchElementException | TimeoutException e){
            isDisplayed = false;
            log.info(e);
        }
        log.info(String.format("%s la visibilidad es: %s", element, isDisplayed));
        return isDisplayed;
    }
    public byte[] attachScreentShot(String nameCapture){

        log.info("Adjuntar captura de pantalla");
        byte[] sreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(nameCapture, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return sreenshot;
    }
    public void scrollToElement(String element) throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        log.info("Desplazarse al elemento: " + element);
        jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(SeleniumElement));
    }
}


