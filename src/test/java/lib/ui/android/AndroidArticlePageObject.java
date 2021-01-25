package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT_ID = "id:org.wikipedia:id/page_external_link";
        OPTIONS_BUTTON_XPATH = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON_XPATH = "xpath://android.widget.LinearLayout[3]";
        ADD_TO_MY_LIST_OVERLAY_ID = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT_ID = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON_XPATH = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON_XPATH = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
