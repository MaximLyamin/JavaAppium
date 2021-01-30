package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

@Epic("Tests for change app condition")
public class ChangeAppConditionTests extends CoreTestCase {

    private final static String search_line = "Java";
    private final static String substring = "bject-oriented programming language";

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "NFT")})
    @DisplayName("Compare article titles after rotate screen")
    @Description("We open 'Java (programming language)' article and rotate device screen and make sure the title is expected")
    @Step("Starting test testChangeOrientationOnSearchResults")
    @Severity(value = SeverityLevel.NORMAL)
    public void testChangeOrientationOnSearchResults() throws InterruptedException, IOException {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(substring);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "NFT")})
    @DisplayName("Compare article titles after background")
    @Description("We open 'Java (programming language)' article and background app and make sure the title is expected")
    @Step("Starting test testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckSearchArticleInBackground() {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(substring);
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult(substring);
    }
}
