package starter.actions.navigation;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPageActions extends PageObject {


    static By listData = By.cssSelector(".YmvwI");

    static By moreOptions=By.cssSelector(".SF7xd");

    @Step("Verify the results based on the criteria")
    public List<String> getData() {
        /*List<WebElement> listOfElements=this.getDriver().findElements(listData);
        for(WebElement elements:listOfElements)
        {
          elementText.add(elements.getText());
        }
        return elementText;*/
        return findAll(listData).stream().map(WebElementFacade::getText).collect(Collectors.toList());
    }

    public void viewOptions() {
        $(moreOptions).click();
    }
}
