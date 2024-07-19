package starter.actions.navigation;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.screenplay.actions.OpenUrl;
import org.openqa.selenium.By;

public class HomePageActions extends PageObject {

    static By searchbar= By.cssSelector("textarea.gLFyf");

    @Step("Launching the browser and navigating to webpage")
    public void check(String url) {
        openUrl(url);
        waitFor(10000);
    }

    @Step("Enter the keyword and search for the results")
    public void searchKey(String keyword) {
        find(searchbar).typeAndEnter(keyword);
    }
}
