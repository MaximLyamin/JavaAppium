package lib.ui;

import io.qameta.allure.Step;
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

    @Step("Initializing the search field")
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

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5);
    }

    @Step("Waiting for search cancel button to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5);
    }

    @Step("Clicking button to clear search result")
    public void clickClearSearch() {
        if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(
                    SEARCH_CLEAR_BUTTON,
                    "Cannot find and click search cancel button",
                    5);
        } else {
            this.waitForElementAndClick(
                    SEARCH_CANCEL_BUTTON,
                    "Cannot find and click search clear button",
                    5);
        }
    }

    @Step("Clicking button to cancel search result")
    public void clickCancelSearch() {
            this.waitForElementAndClick(
                    SEARCH_CANCEL_BUTTON,
                    "Cannot find and click search cancel button",
                    5);
    }

    @Step("Typing '{search_line}' to the search line")
    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type into search input",
                5);
    }

    @Step("Waiting for '{substring}'")
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with substring " + substring);
    }

    @Step("Waiting for search result and select an article by '{substring}' in article title")
    public void clickByArticleWithSubstring(String substring) throws InterruptedException {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring " + substring,
                5);
        Thread.sleep(1000);
    }

    @Step("Making sure there is Search field has '{expected_text}'")
    public void assertCompareSearchInputText(String expected_text) {
            this.assertElementHasText(SEARCH_INPUT_ID, expected_text, "We see unexpected title", 15);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15);

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty label by the request",
                15);
    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We found some results by request");
    }

    @Step("Making sure there is '{search_line}' in visible search results")
    public void checkKeyWordInVisibleSearchResults(String search_line) {
        this.checkKeyWordInEachVisibleResult(
                SEARCH_RESULT_TITLE_ARTICLE,
                search_line,
                "Cannot see keyword " + search_line + " in search results",
                15);
    }

    @Step("Making sure there is some result for the search")
    public void assertThereIsSomeResultOfSearch() {
        int elements = this.getAmountOfFoundArticles();
        Assert.assertTrue("Cannot see less than 3 articles", elements > 2);
    }

    @Step("Making sure there are no results for the search after cancel")
    public void assertThereNoResultOfSearchAfterCancel() {
        this.waitForElementPresent(
                SEARCH_RESULT_AFTER_CANCEL_ID,
                "Search page does not clear",
                15);
    }

    @Step("Waiting for search result and select an article by '{title}' and '{description}'")
    public void waitForElementByTitleAndDescription(String title, String description) {
        String article_title_and_subscription_xpath = getResultSearchElementByTitleAndSubstring(title, description);
        this.waitForElementPresent(
                article_title_and_subscription_xpath,
                "Cannot find article with title " + title + " and description " + description + " by xpath " + article_title_and_subscription_xpath,
                15);
    }
}
