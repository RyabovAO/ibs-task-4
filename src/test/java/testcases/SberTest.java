package testcases;

import org.junit.jupiter.api.Test;
import pages.StartPage;

public class SberTest extends BaseTests{

    @Test
    public void test() {
        pageManager.getPage(StartPage.class)
                .checkStartPAge()
                .chooseMenu("Ипотека")
                .checkHypothecBlock()
                .chooseSubMenu("Ипотека на вторичное жильё")
                .checkHypothecPage()
                .switchFrame()
                .fillFields("Стоимость недвижимости", "5180000")
                .fillFields("Первоначальный взнос", "3058000")
                .fillFields("Срок кредита", "30")
                .clickInsuranceCheckBox()
                .checkInsuranceCheckBox()
                .checkCreditInfo("2122000", "21664", "36829", "11");
    }

}
