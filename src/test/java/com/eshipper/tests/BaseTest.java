package com.eshipper.tests;

import com.eshipper.pageFactory.PlaywrightFactory;
import com.eshipper.pages.BasePage;
import com.eshipper.pages.BasePageFactory;
import com.eshipper.pages.LoginPage;
import com.eshipper.utils.ExcelReader;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public abstract  class BaseTest
{
    ExcelReader excel = new ExcelReader();
    PlaywrightFactory pf;
    Page page;
    protected Properties prop;
    LoginPage loginPage;

    public abstract void initialize();

    protected <T extends BasePage> T createInstance(final Class<T> basePage) {
        return BasePageFactory.createInstance(page, basePage);
    }


    @BeforeClass
    public void doInitialisation() throws UnsupportedEncodingException {
        pf = new PlaywrightFactory();
        prop = pf.init_prop();
        page = pf.initBrowser(prop);
        initialize();



    }
    @AfterClass
    public void tearDown() throws InterruptedException {
        System.out.println("closing browser");
        Thread.sleep(4000);
       // page.context().close();
       // page.context().browser().close();
    }

}
