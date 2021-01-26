package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
        static {
            SEARCH_INIT_ELEMENT = "css:button#searchIcon";
            SEARCH_INPUT = "css:form>input[type='search']";
            SEARCH_INPUT_ID = "css:form>input[type='search']";
            SEARCH_CANCEL_BUTTON = "css:button.cancel";
            SEARCH_CLEAR_BUTTON = "id:Clear text";
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(),'{SUBSTRING}')]";
            SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]/following-sibling::XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
            SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
            SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
            SEARCH_RESULT_TITLE_ARTICLE = "xpath://XCUIElementTypeStaticText[contains(@name,'Java')]";
            SEARCH_RESULT_AFTER_CANCEL_ID = "id:org.wikipedia:id/search_empty_message";
        }

        public MWSearchPageObject(RemoteWebDriver driver) {
            super(driver);
        }
}
