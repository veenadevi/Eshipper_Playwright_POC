package com.eshipper.pages;

import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;

    public void initialize(final Page page) {
        this.page = page;
    }
}
