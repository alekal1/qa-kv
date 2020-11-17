package com.alekal.examples;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class KVTest {

    private final Integer WAIT_TIME = 3000;
    private final ElementsCollection postersOfHouse =  $$("tbody > tr").filterBy(Condition.cssClass("object-item"));
    private final SelenideElement searchInput = $("#keyword");

    @Before
    public void setUp() {
        Configuration.baseUrl = "http://kv.ee/";
        open("");
    }

    @AfterClass
    public static void tearDown() { closeWebDriver(); }

    @Test
    public void testSectionNumber() {
        ElementsCollection sections = $$(".iscroller-wrapper > ul > li");

        sections.shouldHaveSize(7);
    }

    @Test
    public void testNumberOfPostersValuable() throws InterruptedException {
        searchInput.setValue("tallinn");
        searchInput.pressEnter();


        postersOfHouse.last().waitUntil(Condition.visible, WAIT_TIME);
        postersOfHouse.shouldHaveSize(50);
    }

    @Test
    public void testNumberOfPostersNonValuable() throws InterruptedException {
        searchInput.setValue("123");
        searchInput.pressEnter();

        postersOfHouse.last().shouldBe(Condition.not(Condition.visible));
        postersOfHouse.shouldHaveSize(0);
    }

    @Test
    public void testNegativePrice() {
        SelenideElement minPrice = $("#price_min");
        SelenideElement maxPrice = $("#price_max");
        SelenideElement search = $(".btn-start-search");

        minPrice.setValue("-2");
        maxPrice.setValue("-1");
        search.click();

        postersOfHouse.shouldHaveSize(0);
    }
}
