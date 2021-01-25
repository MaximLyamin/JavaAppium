package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT_ID = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Cancel']";
        SEARCH_CLEAR_BUTTON = "id:Clear text";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]/following-sibling::XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT_XPATH = "xpath://XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_ELEMENT_XPATH = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_TITLE_ARTICLE = "xpath://XCUIElementTypeStaticText[contains(@name,'Java')]";
        SEARCH_RESULT_AFTER_CANCEL_ID = "id:org.wikipedia:id/search_empty_message";
    }

    public iOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
