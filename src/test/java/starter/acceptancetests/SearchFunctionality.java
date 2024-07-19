package starter.acceptancetests;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import starter.actions.navigation.HomePageActions;
import starter.actions.navigation.ResultsPageActions;
import starter.actions.navigation.pracePageActions;

import java.rmi.Remote;
import java.util.List;



@ExtendWith(SerenityJUnit5Extension.class)
public class SearchFunctionality extends PageObject {

    String keyword="Chennai Hotels";

    @Managed
    RemoteWebDriver driver;

    @Steps
    HomePageActions homePage;

    @Steps
    pracePageActions pracPage;

    ResultsPageActions resultsPage;

    @Test
    public void Searchfn(){

        homePage.check("https://www.google.com");

        homePage.searchKey(keyword);

        resultsPage.viewOptions();

        List<String> listData=resultsPage.getData();

        System.out.println(listData);

        //Serenity.reportThat("Field Count Verification",()-> Assertions.assertEquals(11, listData.size()));

    }

    @Test
    public void seleprac() throws InterruptedException{

        openUrl("https://rahulshettyacademy.com/AutomationPractice/");

        pracPage.checkflow("Au");

        List<String> optionsList=pracPage.viewOptions();

        System.out.println(optionsList);

        if(optionsList.contains("Saudi Arabia")){
            pracPage.selectOption("Saudi Arabia");
        }

        pracPage.selectRadio("radio2");

        pracPage.selectDropdownBox("option3");

        pracPage.checkBoxSelect("2");

        System.out.println(pracPage.switchWindow());

        System.out.println(pracPage.switchTab());

        pracPage.alertPopup();

        Serenity.reportThat("Hide the element in UI",()->pracPage.hideElement());

        Serenity.reportThat("Show the element in UI",()->pracPage.showElement());

        Serenity.reportThat("Verify the result of frame switch",()->Assertions.assertEquals("MENTORSHIP",pracPage.openFrame()));

        pracPage.getTableData();

    }

}
