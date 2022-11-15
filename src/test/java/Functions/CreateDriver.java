package Functions;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;




public class CreateDriver {
    private static String browser;
    private static String os;
    private static String logLevel;
    private static Properties pro = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("/test.properties");
    private static CreateDriver instance = null;

    private static Logger log = Logger.getLogger(CreateDriver.class);
    private  CreateDriver() throws IOException{
        CreateDriver.initConfig();
    }

    public static WebDriver initConfig() throws IOException {
        WebDriver driver;


        try {
            log.info("#########");
            log.info("[ POM Configuration] - Lee la configuracion de propiedades basicas del: ../test.properties");
            pro.load(in);
            browser = pro.getProperty("browser");
            os = pro.getProperty("os");
            logLevel = pro.getProperty("logLevel");

        } catch (IOException e) {
            log.error("initConfig Error", e);
        }
        log.info("[ POM Configuration] - OS: " + os +" | Browser:" + browser + "|");
        log.info("[ POM Configuration] - Logger Level: " + logLevel);
        log.info("#########");

        driver = WebDriverFactory.createNewWebDriver(browser, os);
        return driver;
    }

}