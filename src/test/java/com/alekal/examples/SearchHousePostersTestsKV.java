package com.alekal.examples;

import com.alekal.examples.pages.MainPage;
import com.codeborne.selenide.*;

import org.junit.Ignore;
import org.junit.jupiter.api.*;


import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchHousePostersTestsKV {

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



    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "http://kv.ee"; // URL to make tests for
        Configuration.browser = "chrome"; // Chrome browser
        Configuration.timeout = WAIT_TIME; // Timeout 30 seconds for selenide element
    }

    @AfterAll
    public static void tearDown() {
        closeWebDriver(); // Since my machine does not close browser after test, needed to close it after each test
    }

    @BeforeEach
    public void redirect() {
        open("/");
    }

    @Test
    @Order(1)
    public void testVerifyPostersCountForTallinn50() {
        MainPage.searchPosters("tallinn")
                .getPosters
                .shouldHaveSize(TALLINN_POSTERS_COUNT_50);
    }

    @Test
    public void testVerifyPostersCountFor123() {
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

    /*
     * This test will fail, because KV shows houses' posters even when room count is 0.
     */
    @Test
    @Disabled("Ignored because KV shows wrong poster number")
    public void testZeroRoomCount() {
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
