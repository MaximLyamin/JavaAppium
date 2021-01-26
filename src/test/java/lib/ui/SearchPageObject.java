package lib.ui;

import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_INPUT_ID,
        SEARCH_CANCEL_BUTTON,
        SEARCH_CLEAR_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
        SEARCH_RESULT_TITLE_ARTICLE,
        SEARCH_RESULT_AFTER_CANCEL_ID;

    public SearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndSubstring(String title, String substring) {
       return SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL.replace("{TITLE}", title).replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find search and click init element by " + SEARCH_INIT_ELEMENT,
                5);

        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking by " + SEARCH_INIT_ELEMENT,
                5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5);
    }

    public void clickClearSearch() {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    SEARCH_CANCEL_BUTTON,
                    "Cannot find and click search cancel button",
                    5);
        } else {
            this.waitForElementAndClick(
                    SEARCH_CLEAR_BUTTON,
                    "Cannot find and click search clear button",
                    5);
        }
    }

    public void clickCancelSearch() {
            this.waitForElementAndClick(
                    SEARCH_CANCEL_BUTTON,
                    "Cannot find and click search cancel button",
                    5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type into search input",
                5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring " + substring,
                5);
    }

    public void assertCompareSearchInputText(String expected_text) {
            this.assertElementHasText(SEARCH_INPUT_ID, expected_text, "We see unexpected title", 15);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15);

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty label by the request",
                15);
    }
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We found some results by request");
    }

    public void checkKeyWordInVisibleSearchResults(String search_line) {
        this.checkKeyWordInEachVisibleResult(
                SEARCH_RESULT_TITLE_ARTICLE,
                search_line,
                "Cannot see keyword " + search_line + " in search results",
                15);
    }

    public void assertThereIsSomeResultOfSearch() {
        int elements = this.getAmountOfFoundArticles();
        Assert.assertTrue("Cannot see less than 3 articles", elements > 2);
    }

    public void assertThereNoResultOfSearchAfterCancel() {
        this.waitForElementPresent(
                SEARCH_RESULT_AFTER_CANCEL_ID,
                "Search page does not clear",
                15);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String article_title_and_subscription_xpath = getResultSearchElementByTitleAndSubstring(title, description);
        this.waitForElementPresent(
                article_title_and_subscription_xpath,
                "Cannot find article with title " + title + " and description " + description + " by xpath " + article_title_and_subscription_xpath,
                15);
    }
}
