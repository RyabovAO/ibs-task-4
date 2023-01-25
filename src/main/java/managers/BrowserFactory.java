package managers;

import constants.PropConst;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class BrowserFactory {

    private final String CHROME = "chrome";
    private final String FIREFOX = "firefox";
    private static BrowserFactory browserFactory = null;

    private BrowserFactory() {
    }

    public static BrowserFactory getInstance() {
        if (browserFactory == null) {
            browserFactory = new BrowserFactory();
        }
        return browserFactory;
    }

    protected WebDriver getDriver() {
        WebDriver driver = null;

        switch (TestPropManager.getInstance().getProperty(PropConst.TYPE_BROWSER)) {
            case CHROME:
                driver = new CreateChromeDriver().create();
                break;
            case FIREFOX:
                driver = new CreateFireFoxDriver().create();
                break;
            default:
                Assertions.fail("Неверное имя браузера");
        }
        return driver;
    }

}
