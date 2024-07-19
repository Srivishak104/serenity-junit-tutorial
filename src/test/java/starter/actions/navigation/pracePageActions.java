package starter.actions.navigation;

import net.serenitybdd.annotations.Step;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import net.serenitybdd.core.pages.WebElementState;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.ui.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;


public class pracePageActions extends PageObject {

    //String term;
    static By autoCompleteBox = By.id("autocomplete");
    static By autoSuggestionList=By.xpath("//ul[@id='ui-id-1']/li/div");
    //ul[@id='ui-id-1']/li

    static By windowText =By.xpath("//p[contains(text(),'Need Help')]/../span");
    static By chooseOption(String term){
        return By.xpath("//ul[@id='ui-id-1']/li/div[text()='"+term+"']");
    }

    static By radioField(String field) {
        return By.xpath("//input[@value='"+field+"']");
    }
    static By checkBoxField(String field) {
        return By.cssSelector("#checkBoxOption"+field);
    }

    static By windowSwitch=By.cssSelector("#openwindow");

    static By tabSwitch=By.cssSelector("#opentab");

    static By alertName=By.cssSelector("#name");

    static By alertButton=By.cssSelector("#alertbtn");

    static By confirmButton=By.cssSelector("#confirmbtn");

    static By hideButton=By.cssSelector("#hide-textbox");

    static By showButton=By.cssSelector("#show-textbox");

    static By hideShowBox=By.cssSelector("#displayed-text");

    static By tableAmount=By.cssSelector("div.totalAmount");

    static By frameText=By.xpath("[@class='col-md-6 text-left']//h3/span");




    @Step("Enter word in Suggestion Box")
    public void checkflow(String suggWord){
      find(autoCompleteBox).type(suggWord);
    }

    @Step("Viewing options present in Suggestion Box")
    public List<String> viewOptions(){
        return findAll(autoSuggestionList).stream().map(WebElementFacade::getText).collect(Collectors.toList());
    }

    @Step("Choose option in Suggestion Box")
    public void selectOption(String keyword) {
       find(chooseOption(keyword)).click();
    }

    @Step("Selection of RadioBox option")
    public void selectRadio(String field) {
        find(radioField(field)).click();
    }

    @Step("Drop Down Value Selection")
    public void selectDropdownBox(String text){
        find("#dropdown-class-example").selectByValue(text);
    }

    @Step("Checkbox Selection")
    public void checkBoxSelect(String choice) {
        find(checkBoxField(choice)).shouldBeVisible();
        find(checkBoxField(choice)).click();
        find(checkBoxField(choice)).shouldBeSelected();
        find(checkBoxField(choice)).click();
        find(checkBoxField(choice)).shouldNotBeSelected();
        find(checkBoxField(choice)).click();
    }

    @Step("Switching to new window")
    public String switchWindow(){
       $(windowSwitch).click();
        ArrayList<String> newTab = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(newTab.get(1));
        withTimeoutOf(Duration.ofSeconds(7)).waitForPresenceOf(windowText);
        String winText= find(windowText).getText();
        getDriver().switchTo().window(newTab.get(0));
        return winText;
    }



    @Step("Switching to new Tab")
    public String switchTab() {
        $(tabSwitch).click();
        ArrayList<String> newTab = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(newTab.get(1));
        String winTitle= getTitle();
        getDriver().switchTo().window(newTab.get(0));
        return winTitle;
    }

    @Step("Handling Alert Popup")
    public void alertPopup() {
        find(alertButton).click();
        getAlert().getText();
        getAlert().accept();
        //toAlert().andAccept();
        find(alertName).type("Srivishak");
        find(confirmButton).click();
        System.out.println(getAlert().getText());
        getAlert().dismiss();
    }

    @Step("Hide Element Functionality")
    public boolean hideElement()  {
        moveTo(tableAmount);
        //withAction().moveToElement(find(tableAmount)).build().perform();
       // Thread.sleep(5000);
        find(hideButton).click();
        return $(hideShowBox).isCurrentlyVisible();
    }
    @Step("Show Element Functionality")
    public boolean showElement() {
        find(showButton).click();
        return $(hideShowBox).isCurrentlyVisible();
    }
    @Step("Switching to frame functionality")
    public String openFrame() {
        getDriver().switchTo().frame("iframe-name");
        //moveTo(frameText);
        find(Link.withText("Mentorship")).click();
        withTimeoutOf(Duration.ofSeconds(5)).waitForPresenceOf(By.xpath("//h1"));
        String text1=find("//h1").getText();
        getDriver().switchTo().defaultContent();
        //withTimeoutOf()
        return text1;
    }

    public void getTableData() {
        WebElement resultsTable=getDriver().findElement(By.cssSelector("#product"));
        List<Map<Object, String>> tbData=rowsFrom(resultsTable);

        for(Map<Object,String> t:tbData){
            for(Map.Entry<Object,String> m:t.entrySet()){
                System.out.println(m.getKey()+":"+m.getValue());
            }
        }

            //System.out.println(tbData);

    }
}
