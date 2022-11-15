package Functions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverFactory {

    private static Properties pro = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("/test.properties");
    private static String resourceFolder;

    private static Logger log = Logger.getLogger(CreateDriver.class);

    private static WebDriverFactory instance = null;

    private WebDriverFactory(){

    }
    public static WebDriverFactory getInstance(){
        if(instance == null){
            instance = new WebDriverFactory();
        }
        return instance;
    }

    public static WebDriver createNewWebDriver(String browser, String os) throws IOException {
        WebDriver driver;
        pro.load(in);
        resourceFolder = pro.getProperty("resourceFolder");

        if ("FIREFOX".equalsIgnoreCase(browser)) {
            if ("WINDOWS".equalsIgnoreCase(os)) {
                System.setProperty("webdriver.gecko.driver", resourceFolder + os + "/geckodriver.exe");
            } else {
                System.setProperty("webdriver.gecko.driver", resourceFolder + os + "/geckodriver");

            }
            driver = new FirefoxDriver();

        } else if ("CHROME".equalsIgnoreCase(browser)) {
            if ("WINDOWS".equalsIgnoreCase(os)) {
                System.setProperty("webdriver.chrome.driver", resourceFolder + os + "/chromedriver.exe");
            } else {
                System.setProperty("webdriver.chrome.driver", resourceFolder + os + "/chromedriver");
            }
            driver = new ChromeDriver();
        } else {
            log.error("The Driver is not selected properly, invalid name:" + browser + "," + os);
            return null;
        }
        driver.manage().window().maximize();
        return driver;

    }


}