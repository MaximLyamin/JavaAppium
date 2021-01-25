package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
        SEARCH_INPUT_ID = "id:org.wikipedia:id/search_src_text";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL = "xpath://*[@text='{TITLE}']/following-sibling::*[@text='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENT_XPATH = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT_XPATH = "xpath://*[@text='No results found']";
        SEARCH_RESULT_TITLE_ARTICLE = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_RESULT_AFTER_CANCEL_ID = "id:org.wikipedia:id/search_empty_message";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
