package com.alekal.examples.pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;


public class PostersResultPage {

    public ElementsCollection getPosters = $$("tbody > tr[class*='object-item']");
    public static SelenideElement postersCountOnPage = $("#result_count");

    public PostersResultPage changePostersCountOnPage(Integer count) {
        postersCountOnPage.scrollTo();
        postersCountOnPage.selectOption(Integer.toString(count));
        return page(PostersResultPage.class);
    }
}
