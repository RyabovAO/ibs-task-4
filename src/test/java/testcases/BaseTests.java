package testcases;

import constants.PropConst;
import managers.DriverManager;
import managers.InitFramework;
import managers.PageManager;
import managers.TestPropManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTests {

    private final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    private final TestPropManager props = TestPropManager.getInstance();


    @BeforeAll
    public static void beforeAll() {
        InitFramework.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
        driverManager.getDriver().get(props.getProperty(PropConst.BASE_URL));
    }

//    @AfterAll
//    public static void afterAll() {
//      InitFramework.quitFramework();
//    }
}
