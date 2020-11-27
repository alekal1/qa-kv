package com.alekal.examples.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public static SelenideElement searchInput = $("#keyword");
    public static SelenideElement minPriceField = $("#price_min");
    public static SelenideElement maxPriceField = $("#price_max");
    public static SelenideElement searchButton = $(".btn-start-search");
    public static ElementsCollection getSections = $$(".iscroller-wrapper > ul > li");
    public static ElementsCollection getMainPagePosters = $$("ul[class='list inline-grid']:nth-child(4n) > li[class*='col-1-6']");
    public static SelenideElement minRoomField = $("#rooms_min");
    public static SelenideElement maxRoomField = $("#rooms_max");

    public static PostersResultPage searchPosters(String query) {
        searchInput.setValue(query).pressEnter();
        return page(PostersResultPage.class);
    }

    public static PostersResultPage searchByPrice(Integer minPrice, Integer maxPrice) {
        minPriceField.setValue(Integer.toString(minPrice));
        maxPriceField.setValue(Integer.toString(maxPrice));
        searchButton.click();
        return page(PostersResultPage.class);
    }

    public static PostersResultPage searchByRoomCount(Integer minRoomCount, Integer maxRoomCount) {
        minRoomField.setValue(Integer.toString(minRoomCount));
        maxRoomField.setValue(Integer.toString(maxRoomCount));
        searchButton.click();
        return page(PostersResultPage.class);
    }
}
