package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        //FOLDER_BY_NAME_XPATH_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_XPATH_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(),'{TITLE}')]/../../*[contains(@class, 'watched')]";
        //"/html/body/div[1]/div/main/div[3]/ul/li/a[2]"
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
