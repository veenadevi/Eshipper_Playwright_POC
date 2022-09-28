package com.eshipper.pages;

import com.microsoft.playwright.Page;

public class CustomerDashboardPage extends BasePage{



    private String logOutLink="a[role=\"button\"]:has-text(\"Log Out\")";


    private String shippingNavBarLink="mat-expansion-panel-header[role=\"button\"]:has-text(\"Shipping\")";

    private String newQuoteString="a[role=\"button\"]:has-text(\"Quick Quote\")";

//    public CustomerDashboardPage(Page page){
//        this.page=page;
//
//    }

    public boolean isLogOutPresent(){
        System.out.println("dashboard title "+page.title());
        System.out.println(page.locator(logOutLink).isVisible());

        return page.locator(logOutLink).isVisible();

    }
    public void clickNewQuote(){
        page.click(shippingNavBarLink);
        page.click(newQuoteString);
        page.waitForTimeout(3000);
    }


}
