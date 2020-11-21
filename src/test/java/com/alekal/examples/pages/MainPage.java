package com.alekal.examples.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    public SelenideElement searchInput = $("#keyword");
    public SelenideElement minPriceField = $("#price_min");
    public SelenideElement maxPriceField = $("#price_max");
    public SelenideElement searchPriceField = $(".btn-start-search");
    public ElementsCollection sections = $$(".iscroller-wrapper > ul > li");
    public ElementsCollection mainPagePosters = $$("ul[class='list inline-grid']:nth-child(4n) > li[class*='col-1-6']");
}
