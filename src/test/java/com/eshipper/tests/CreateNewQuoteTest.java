package com.eshipper.tests;

import com.eshipper.pages.CustomerDashboardPage;
import com.eshipper.pages.LoginPage;
import com.eshipper.pages.QuickQuotePage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.internal.TestResult;

import java.util.HashMap;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CreateNewQuoteTest extends BaseTest{

    private LoginPage loginPage;
    private CustomerDashboardPage  dashboardPage;

    private QuickQuotePage newQuotePage;

    @Override
    public void initialize() {
        loginPage = createInstance(LoginPage.class);
    }
    @Test(dataProvider="UserLoginCredentials")
    public void doLogin(String userName,String password){
    String loginpageTitle=loginPage.getLoginPageTitle();
        System.out.println("loginpageTitle "+loginpageTitle);
        System.out.println(""+userName +"" +password);

        loginPage.doLogin(userName,password);


    }

    @Test(dependsOnMethods ="doLogin" )
    public void doCheckDashBoardPage(){
        dashboardPage= createInstance(CustomerDashboardPage.class);
        assertThat(page).hasURL("dashboard");

    }

    @Test(dependsOnMethods ="doLogin" )
    public void doCheckLogoutLink(){
        dashboardPage= createInstance(CustomerDashboardPage.class);
        Assert.assertTrue(dashboardPage.isLogOutPresent());

    }

    @Test(dependsOnMethods ="doLogin" )
    public void doCreateQuote(){
        Assert.assertTrue(dashboardPage.isLogOutPresent());

        dashboardPage.clickNewQuote();
        assertThat(page).hasURL("https://dev.eshipper.com/customer/shipping/quick-quote");
        newQuotePage=createInstance(QuickQuotePage.class);
       String confirmation= newQuotePage.createNewQuote();

       String newQuoteTrack=newQuotePage.getnewQuoteTrackNumber();

        String newQuoteOrder=newQuotePage.getnewQuoteOrderNumber();


        System.out.println("newQuoteTrack "+newQuoteTrack);

        System.out.println("newQuoteOrder "+newQuoteOrder);

        System.out.println("confirmation "+confirmation);


    }


    @DataProvider(name = "UserLoginCredentials")
    public  Object[][] accountCreation() {
        // Totals rows count
        int rows = excel.getRowCount("LoginCredentials");


        // Total Columns
        int column = excel.getColumnCount("LoginCredentials");

        int actRows = rows - 1;
        //Created an object of array to store data
        Object[][] data = new Object[1][2];

        for (int i = 0; i < actRows; i++) {
            Map<String, String> hashMap = new HashMap<>();
           // for (int j = 1; j < column; j++) {
            int x=i+2;

                if(excel.getCellData("LoginCredentials",0, x).equalsIgnoreCase("User")){

                    data[0][0]=excel.getCellData("LoginCredentials", 1, x);
                    data[0][1]=excel.getCellData("LoginCredentials", 2, x);

                }



        }
        System.out.println("data size"+data.length +data[0][1]);
        return data;
    }
}
