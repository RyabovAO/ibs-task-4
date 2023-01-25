package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HypothecPage extends BasePage {

    private final String locator = "//span[contains(text(),'%s')]/..//span[contains(text(),'₽')]";

    @FindBy(xpath = "//*[contains(@sandbox,'allow-forms allow-scripts allow-same')]")
    private WebElement formRegistration;

    @FindBy(xpath = "//label[contains(@class,'inpt-root__label-6-3-4-beta-1-for-calculator')]")
    private List<WebElement> listFieldName;

    @FindBy(xpath = "//input[contains(@class,'inpt-root__input-6-3-4-beta-1-for-calculator')]")
    private List<WebElement> listInputField;

    @FindBy(xpath = "//span[contains(text(),'Страхование')]/..//input[contains(@class,'switch-input')]")
    private WebElement insuranceCheckBox;

    @FindBy(xpath = "//span[contains(text(),'Страхование')]/..//label[contains(@class,'switch-root-4-0-1')]")
    private WebElement checkInsuranceCheckBox;

    @FindBy(xpath = "//span[contains(text(),'Ежемесячный платеж')]/..//span[contains(text(),'₽')]")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//span[contains(text(),'Сумма кредита')]/..//span[contains(text(),'₽')]")
    private WebElement creditSum;

    @FindBy(xpath =
            "//div[@data-test-id='main-results-block']//span[contains(text(),'Процентная ставка')]/..//span[contains(text(),'%')]")
    private WebElement interestRate;

    @FindBy(xpath = "//span[contains(text(),'Необходимый доход')]/..//span[contains(text(),'₽')]")
    private WebElement requiredIncome;

    @Step
    public HypothecPage checkHypothecPage() {
        waitUtilElementToBeVisible(formRegistration);
        Assertions.assertTrue(isPageOpen(formRegistration), "Страница 'Ипотека' не открыта");
        return this;
    }

    @Step
    public HypothecPage switchFrame() {
        scrollToElementJs(formRegistration);
        toFrame(formRegistration);
        return this;
    }

    @Step
    public HypothecPage fillFields(String fieldName, String value) {
        for (WebElement fieldItem : listFieldName) {
            if (fieldItem.getText().contains(fieldName)) {
                listInputField.get(listFieldName.indexOf(fieldItem)).click();
                listInputField.get(listFieldName.indexOf(fieldItem)).sendKeys(Keys.CONTROL + "a");
                listInputField.get(listFieldName.indexOf(fieldItem)).sendKeys(Keys.BACK_SPACE);
                sendKeysByOneCharacter(listInputField.get(listFieldName.indexOf(fieldItem)), value);
                waitPageLoad(500, 250);
                return this;
            }
        }
        Assertions.fail("Поле '" + fieldName + "' не было найдено");
        return this;
    }

    private void sendKeysByOneCharacter(WebElement element, String value) {
        String[] stringArr = value.split("");
        for (String charItem : stringArr) {
            action.moveToElement(element).pause(50).sendKeys(charItem).pause(50).perform();
        }
    }

    @Step
    public HypothecPage clickInsuranceCheckBox() {
        clickToElementJs(insuranceCheckBox);
        return this;
    }

    @Step
    public HypothecPage checkInsuranceCheckBox() {
        waitUtilElementToBeVisible(checkInsuranceCheckBox);
        Assertions.assertTrue(checkInsuranceCheckBox.isDisplayed(),
                "Чекбокс 'Страхование жизни и здоровья' выключен");
        return this;
    }

    @Step
    public HypothecPage checkCreditInfo(String creditSum1, String monthlyPayment1, String requiredIncome1, String interestRate1) {
        Assertions.assertAll(() -> Assertions.assertEquals(creditSum1, getElementText(creditSum),
                        "Сумма кредита не равно введенной"),
                () -> Assertions.assertEquals(monthlyPayment1, getElementText(monthlyPayment),
                        "Рассчитаный ежемесячный платеж не равен введенному"),
                () -> Assertions.assertEquals(requiredIncome1, getElementText(requiredIncome),
                        "Ежемесячный платёж не равен введенному"),
                () -> Assertions.assertEquals(interestRate1, getElementText(interestRate),
                        "Процентная ставка не равна введенной"));
        return this;
    }



}
