package com.alekal.examples;

import com.alekal.examples.pages.MainPage;
import com.codeborne.selenide.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.*;

public class KVTests {

    private static final int WAIT_TIME = 30000;

    private final int MIN_PRICE_POS = 1000;
    private final int MIN_PRICE_NEG = -2;
    private final int MAX_PRICE_POS = 10000;
    private final int MAX_PRICE_NEG = -1;

    private final int ROOM_COUNT_4 = 4;
    private final int ROOM_COUNT_1 = 1;
    private final int ROOM_COUNT_ZERO = 0;

    private final int TALLINN_POSTERS_COUNT_100 = 100;
    private final int TALLINN_POSTERS_COUNT_50 = 50;
    private final int TALLINN_POSTERS_COUNT_25 = 25;
    private final int SECTION_NUMBER = 7;



    @BeforeClass
    public static void setUp() {
        Configuration.baseUrl = "http://kv.ee";
        Configuration.timeout = WAIT_TIME; // Timeout 30 seconds for selenide element
    }

    @AfterClass
    public static void tearDown() {
        closeWebDriver();
    }

    @Before
    public void redirect() {
        open("/");
    }

    @Test
    public void verifySectionNumberOnMainPage() {
        MainPage.getSections.shouldHaveSize(SECTION_NUMBER);
    }

    @Test
    public void verifyPostersCountOnMainPage() {
        MainPage.getMainPagePosters.shouldHaveSize(11);
    }

    @Test
    public void verifyPostersCountForTallinn50() {
        MainPage.searchPosters("tallinn")
                .getPosters
                .shouldHaveSize(TALLINN_POSTERS_COUNT_50);
    }

    @Test
    public void verifyPostersCountFor123() {
        MainPage.searchPosters("123")
                .getPosters
                .shouldBe(empty);
    }

    @Test
    public void testNormalPrices() {
        MainPage.searchByPrice(MIN_PRICE_POS, MAX_PRICE_POS)
                .getPosters
                .last()
                .shouldBe(visible);
    }

    @Test
    public void testNegativePrices() {
        MainPage.searchByPrice(MIN_PRICE_NEG, MAX_PRICE_NEG)
                .getPosters
                .shouldBe(empty);
    }

    @Test
    public void testNormalRoomCount() {
        MainPage.searchByRoomCount(ROOM_COUNT_1, ROOM_COUNT_4)
                .getPosters
                .last()
                .shouldBe(visible);
    }

    @Test
    public void testZeroRoomCount() {
        /*
         * This test will fail, because KV shows houses' posters even when room count is 0.
         */
        MainPage.searchByRoomCount(ROOM_COUNT_ZERO, ROOM_COUNT_ZERO)
                .getPosters.last()
                .shouldBe(not(visible));
    }

    @Test
    public void testPosterOnPage25() {
        MainPage.searchPosters("tallinn")
                .changePostersCountOnPage(TALLINN_POSTERS_COUNT_25)
                .getPosters
                .shouldHaveSize(TALLINN_POSTERS_COUNT_25);
    }

    @Test
    public void testPostersOnPage100() {
        MainPage.searchPosters("tallinn")
                .changePostersCountOnPage(TALLINN_POSTERS_COUNT_100)
                .getPosters
                .shouldHaveSize(TALLINN_POSTERS_COUNT_100);
    }

}
