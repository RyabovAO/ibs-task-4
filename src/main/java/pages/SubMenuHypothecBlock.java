package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class SubMenuHypothecBlock extends BasePage {

    @FindBy(xpath = "//*[contains(@class,'menu__item_opened')]//div[contains(@class,'__subaction-block')]//a[contains(@rel,'noopener noreferrer')]")
    private WebElement menuHypothecId;

    @FindBy(xpath = "//*[contains(@class,'item_first kitt-top-menu__item_opened')]//*[contains(@class,'kitt-top-menu__link_second')]")
    private List<WebElement> subMenuHypothec;

    @Step("Проверка открытия блока 'Ипотека'")
    public SubMenuHypothecBlock checkHypothecBlock() {
        waitUtilElementToBeVisible(menuHypothecId);
        Assertions.assertTrue(isPageOpen(menuHypothecId), "Блок подменю 'Ипотеки' не открыт");
        return this;
    }

    @Step("В меню блока 'Ипотека' выбрать подменю {menuName}")
    public HypothecPage chooseSubMenu(String menuName) {
        for (WebElement menuItem : subMenuHypothec) {
            if (menuItem.getText().contains(menuName)) {
                menuItem.click();
                return pageManager.getPage(HypothecPage.class);
            }
        }
        Assertions.fail("Подменю '" + menuName + "' не было найдено");
        return pageManager.getPage(HypothecPage.class);
    }

}
