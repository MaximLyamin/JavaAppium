package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    private final static String search_line = "Java";

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test search mechanism")
    @Description("We search 'Java' and compare substring")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);;

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test cancel search mechanism")
    @Description("We search 'Java' and cancel search results")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);;

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test amount of not empty search")
    @Description("We search 'Linkin Park Discography' and compare search result is not empty")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);;
        String search_line = "Linkin Park Discography";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0);
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test amount of empty search")
    @Description("We search 'zxvasdfqwer' and compare search result is empty")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);;
        String search_line = "zxvasdfqwer";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test input field has text 'Search'")
    @Description("We clicking on input field and compare text")
    @Step("Starting test testFieldInputHasText")
    @Severity(value = SeverityLevel.NORMAL)
    public void testFieldInputHasText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String expected_text = "Search";

        SearchPageObject.initSearchInput();
        SearchPageObject.assertCompareSearchInputText(expected_text);
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test check keyword in visible search result")
    @Description("We search an articles and compare visible search result has keyword in each result")
    @Step("Starting test testCheckKeyWordInSearchResults")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckKeyWordInSearchResults() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.checkKeyWordInVisibleSearchResults(search_line);
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test clear search result")
    @Description("We search an articles and clear search result")
    @Step("Starting test testCancelSearchResults")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCancelSearchResults() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.assertThereIsSomeResultOfSearch();
        SearchPageObject.clickClearSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Test search an article with title and description")
    @Description("Test search an article with title and description and compare in search results more than 3 articles")
    @Step("Starting test testSearchArticleWithTitleAndDescription")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchArticleWithTitleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String substring = "bject-oriented programming language";
        String title = "Java (programming language)";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForElementByTitleAndDescription(title, substring);
        int articles_in_search_result = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue("We found too few articles!", articles_in_search_result >= 3);
    }
}
