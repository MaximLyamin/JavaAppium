package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_XPATH_TPL,
            ARTICLE_BY_TITLE_XPATH_TPL,
            REMOVE_FROM_SAVED_BUTTON,
            CLOSE_BUTTON_POPUP_WINDOW;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_XPATH_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_XPATH_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.swipeElementToLeft(
                    article_title_xpath,
                    "Cannot find saved article by title " + article_title);
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
                    this.waitForElementAndClick(
                            remove_locator,
                            "Cannot click button to remove article from saved",
                            10);
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_title_xpath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementPresent(
                article_title_xpath,
                "Cannot find saved article by title " + article_title,
                5);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementNotPresent(
                article_title_xpath,
                "Saved article still present with title " + article_title,
                5);
    }

    public void openArticleByTitleInSavedList(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementAndClick(
                article_title_xpath,
                "Cannot find saved article by title " + article_title,
                5);
    }

    public String getArticleByTitleXpathInSavedList(String article_title) {
        return getSavedArticleXpathByTitle(article_title);
    }

    public void clickOnCloseButtonOnPopupWindow()
    {
        this.waitForElementAndClick(CLOSE_BUTTON_POPUP_WINDOW,
                "Cannot find and click x button on sync popup",
                5);
    }
}
