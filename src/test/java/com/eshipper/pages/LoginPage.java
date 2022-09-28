package com.eshipper.pages;

import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

public class LoginPage extends BasePage {



    private String userNameText="[placeholder=\"Username \\*\"]";

    private String passwordText="[placeholder=\"Password \\*\"]";
    private String loginButton="text=Login";







    public String getLoginPageTitle(){
        return page.title();
    }

    public String doLogin(String appUserName, String appPassword) {
        System.out.println("App creds: " + appUserName + ":" + appPassword);
        page.fill(userNameText, appUserName);
        page.fill(passwordText, appPassword);
        System.out.println("login button"+page.locator(loginButton).first().isEnabled());
        page.waitForTimeout(3000);
        page.locator(loginButton).first().click();

        page.waitForTimeout(3000);
        System.out.println("clicked login button");

        return null;

    }


}
