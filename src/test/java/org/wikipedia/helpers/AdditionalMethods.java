package org.wikipedia.helpers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AdditionalMethods {
    public boolean compareData(List<String> firstList, String value) {
        List<String> secondList = new ArrayList<>();
        for (int i = 0; i < value.length(); i++) {
            secondList.add(String.valueOf(value.charAt(i)));
        }
        for (int i = 0; i < secondList.size(); i++) {
            if (!secondList.get(i).equals(firstList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void performLongPress(SelenideElement selenideElement, int durationSeconds) {
        WebDriver driver = getWebDriver();
        Actions actions = new Actions(driver);

        actions.clickAndHold(selenideElement)
                .pause(Duration.ofSeconds(durationSeconds))
                .release()
                .perform();
    }
}
