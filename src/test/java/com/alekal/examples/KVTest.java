package com.alekal.examples;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class KVTest {

    private final Integer WAIT = 1;
    private final List<SelenideElement> postersOfHouse =  $$("tbody > tr").filterBy(Condition.cssClass("object-item"));

    @Before
    public void setUp() {
        open("http://kv.ee/");
    }

    @AfterClass
    public static void tearDown() { closeWebDriver(); }

    @Test
    public void testSectionNumber() {
        List<SelenideElement> sections = $$(".iscroller-wrapper > ul > li");

        assertThat(sections, hasSize(7));
    }

    @Test
    public void testNumberOfPostersValuable() throws InterruptedException {
        SelenideElement searchInput = $("#keyword").setValue("tallinn");
        searchInput.pressEnter();

        TimeUnit.SECONDS.sleep(WAIT); // Because HTML DOM cannot load properly need to wait some time

        assertThat(postersOfHouse, hasSize(50));
    }

    @Test
    public void testNumberOfPostersNonValuable() throws InterruptedException {
        SelenideElement searchInput = $("#keyword").setValue("123");
        searchInput.pressEnter();
        TimeUnit.SECONDS.sleep(WAIT); // Because HTML DOM cannot load properly need to wait some time

        assertThat(postersOfHouse, hasSize(0));
    }

    @Test
    public void testNegativePrice() {
        SelenideElement minPrice = $("#price_min");
        SelenideElement maxPrice = $("#price_max");
        SelenideElement search = $(".btn-start-search");

        minPrice.setValue("-2");
        maxPrice.setValue("-1");
        search.click();

        assertThat(postersOfHouse, hasSize(0));
    }
}
