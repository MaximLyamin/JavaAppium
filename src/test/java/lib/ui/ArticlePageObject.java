package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            TITLE_XPATH,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON_XPATH,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY_ID,
            MY_LIST_NAME_INPUT_ID,
            MY_LIST_OK_BUTTON_XPATH,
            CLOSE_ARTICLE_BUTTON_XPATH;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    @Step("Get article title")
    public String getArticleTitle() {

        if (Platform.getInstance().isAndroid()) {
            return this.waitForElementAndGetAttribute(TITLE, "text", "Cannot find article title on page!", 15);
        } else if (Platform.getInstance().isIOS()) {
            return this.waitForElementAndGetAttribute(TITLE, "name", "Cannot find article title on page!", 15);
        } else {
            WebElement title_element = this.waitForTitleElement();
            screenshot(this.takeScreenshot("allure_title"));
            return title_element.getText();
        }
    }

    @Step("Making sure the compared titles are equal")
    public void assertCompareArticles(String expected_text) {
        if (Platform.getInstance().isIOS()) {
            this.assertElementHasText(TITLE_XPATH, expected_text, "We see unexpected title", 15);
        } else {
            this.assertElementHasText(TITLE, expected_text, "We see unexpected title", 15);
        }
    }

    @Step("Swiping to footer an article page")
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToElement(
                    FOOTER_ELEMENT,
                    "Cannot find end of article",
                    40);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find end of article",
                    40);
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find end of article",
                    40);
        }
    }

    @Step("Adding the article to my list first time")
    public void addArticleToMyListFirstTime(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON_XPATH,
                "Cannot find 'More option' button",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list' button",
                5);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY_ID,
                "Cannot find 'Got it' button",
                5);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT_ID,
                "Cannot find input to set name of articles folder",
                5);

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT_ID,
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON_XPATH,
                "Cannot press 'OK' button",
                5);
    }

    @Step("Adding the article to my saved articles")
    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);
    }

    @Step("Removing the article from saved if it has been added")
    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    5);
            this.waitForElementPresent(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before");
        }
    }

    @Step("Closing the article")
    public void closeArticle() {
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON_XPATH,
                    "Cannot close article, cannot find X button",
                    5);
        }
        System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
    }

    @Step("Making sure the open article has a title")
    public void assertTitleByIdIsPresentOnOpenArticle() {
        this.assertElementPresent(
                TITLE,
                "Cannot find article title on open page by id " + TITLE);
    }

    @Step("Adding the article to the created my list")
    public void addArticleToMyCreatedList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON_XPATH,
                "Cannot find 'More option' button",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list' button",
                5);

        this.waitForElementAndClick(
                "xpath://*[@text='" + name_of_folder + "']",
                "Cannot find created folder",
                5);
    }
}
