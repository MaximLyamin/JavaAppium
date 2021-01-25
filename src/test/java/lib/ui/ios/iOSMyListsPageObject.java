package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_XPATH_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        CLOSE_BUTTON_POPUP_WINDOW = "xpath://XCUIElementTypeButton[@name='Close']";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
