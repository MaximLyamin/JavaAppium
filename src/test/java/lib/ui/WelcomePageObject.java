package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
            NEXT_LINK = "id:Next",
            SKIP_BUTTON = "id:Skip",
            GET_STARTED_BUTTON = "id:Get started";

    @Step("Waiting link 'Learn More'")
    public void waitForLearnMoreLink() {
        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link",
                10);
    }

    @Step("Clicking 'Next' button")
    public void clickNextButton() {
        this.waitForElementAndClick(
                NEXT_LINK,
                "Cannot find and click 'Next' button",
                5);
    }

    @Step("Waiting page with text 'New Way to Explore'")
    public void waitForNewWayToExploreText() {
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'New ways to explore' link",
                10);
    }

    @Step("Waiting page with text 'Add or edit preferred language'")
    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(
                STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,
                "Cannot find 'Add or edit preferred languages' link",
                10);
    }

    @Step("Waiting page with text 'For learn more about data collected'")
    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find 'Learn more about data collected' link",
                10);
    }

    @Step("Clicking 'Get started' button")
    public void clickGetStartedButton() {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find and click 'Get started' button",
                5);
    }

    @Step("Clicking 'Skip' button")
    public void clickSkip() {
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find and click 'skip button'", 5);
    }
}
