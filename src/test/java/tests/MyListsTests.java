package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private final static String search_line = "Java";
    private final static String name_of_folder = "Learning programming";
    private final static String first_article_title = "Java (programming language)";
    private final static String substring_first_article = "bject-oriented programming language";
    private final static String second_article_title = "JavaScript";
    private final static String substring_second_article = "High-level programming language";
    private final static String
            login = "Matrosoff_spb",
            password = "QazWsx123";

    @Test
    public void testSaveFirstArticleToMyList() throws Exception {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);;
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(substring_first_article);
        ArticlePageObject.assertCompareArticles(first_article_title);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyListFirstTime(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals(
                    "We are not on the same page after login",
                    first_article_title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            MyListsPageObject.clickOnCloseButtonOnPopupWindow();
        }
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyListThanOneDelete() throws Exception {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);;
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(substring_first_article);
        ArticlePageObject.assertCompareArticles(first_article_title);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyListFirstTime(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals(
                    "We are not on the same page after login",
                    first_article_title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(substring_second_article);
        ArticlePageObject.assertCompareArticles(second_article_title);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyListFirstTime(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            MyListsPageObject.clickOnCloseButtonOnPopupWindow();
        }
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.waitForArticleToAppearByTitle(second_article_title);
        String title_in_list = MyListsPageObject.getArticleByTitleXpathInSavedList(second_article_title);
        MyListsPageObject.openArticleByTitleInSavedList(second_article_title);
        String title_in_open_article = MyListsPageObject.getArticleByTitleXpathInSavedList(second_article_title);

        Assert.assertEquals(
                "Article have been changed after opening",
                title_in_open_article,
                title_in_list);
    }
}

