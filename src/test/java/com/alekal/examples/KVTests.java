package com.alekal.examples;

import com.alekal.examples.pages.MainPage;
import com.alekal.examples.pages.PostersResultPage;
import com.codeborne.selenide.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;

public class KVTests {

    private MainPage mainPage;


    @Before
    public void setUp() {
        Configuration.baseUrl = "http://kv.ee";
        open("/");
        Configuration.timeout = 30000; // Timeout 30 seconds
        mainPage = new MainPage();
    }

    @AfterClass
    public static void tearDown() { closeWebDriver(); }

    @Test
    public void testSectionNumber() {
        mainPage.sections.shouldHaveSize(7);
    }

    @Test
    public void testPostersOnMainPage() {
        mainPage.mainPagePosters.shouldHaveSize(11);
    }

    @Test
    public void testNumberOfPostersValuable() {
        this.searchPosters("tallinn").posters.shouldHaveSize(50);
    }

    @Test
    public void testNumberOfPostersNonValuable() {
        this.searchPosters("123").posters.shouldHaveSize(0).last().shouldBe(Condition.not(Condition.visible));
    }

    @Test
    public void testNormalPrice() {
        this.searchByPrice(1000, 10000).posters.last().shouldBe(Condition.visible);
    }

    @Test
    public void testNegativePrice() {
        this.searchByPrice(-2, -1).posters.shouldHaveSize(0).last().shouldBe(Condition.not(Condition.visible));
    }

    public PostersResultPage searchPosters(String query) {
        mainPage.searchInput.setValue(query).pressEnter();
        return page(PostersResultPage.class);
    }

    public PostersResultPage searchByPrice(Integer minPrice, Integer maxPrice) {
        mainPage.minPriceField.setValue(Integer.toString(minPrice));
        mainPage.maxPriceField.setValue(Integer.toString(maxPrice));
        mainPage.searchPriceField.click();
        return page(PostersResultPage.class);
    }
}
