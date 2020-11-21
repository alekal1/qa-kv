package com.alekal.examples.pages;


import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;


public class PostersResultPage {

    public ElementsCollection posters = $$("tbody > tr[class*='object-item']");
}
