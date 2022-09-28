package com.eshipper.pages;

import com.eshipper.pageFactory.PlaywrightFactory;
import com.eshipper.tests.BaseTest;
import com.microsoft.playwright.Page;

import java.util.List;

public class QuickQuotePage extends BasePage {



    private String fromAddressText="text=Ship fromResetAddressCanadaCountry *Postal/Zip Code *StateState *City * Resident >> [placeholder=\"Address\"]";
    private String fromPostalId="[aria-label=\"translation-not-found\\[originPostalId\\]\"]";

    private String fromZipCode="[aria-label=\"Zip Code\"]";

    private String fromStateDropDown="#mat-select-4 >> text=State";
    private String fromStateValue="text=British Columbia";

    private String fromCity="text=CanadaCountry *Postal/Zip Code *British ColumbiaState *City * >> [placeholder=\"City\"]";
    private String fromCityValue="#mat-option-1939 >> text=Whistler";



    private String toAddressText="text=AddressCanadaCountry *Postal/Zip Code *StateState *City * Residential >> [placeholder=\"Address\"]";


    private String toStateDropDown="mat-select[role=\"listbox\"]:has-text(\"State\")";
    private String toStateValue="#mat-option-2531 >> text=British Columbia";

//    private String fromCity="text=CanadaCountry *Postal/Zip Code *British ColumbiaState *City * >> [placeholder=\"City\"]";
//    private String fromCityValue="#mat-option-1939 >> text=Whistler";

    private String toZipCode="[aria-label=\"Zip Code\"]";

    private String toCity="text=CanadaCountry *Postal/Zip Code *British ColumbiaState *City * >> [placeholder=\"City\"]";
    private String toCityValue="#mat-option-1939 >> text=Whistler";


    private String ShippingDateCal="text=Ship Date *Package TypePackage Type * >> [aria-label=\"Open calendar\"]";

    private String packageType="[aria-label=\"Package Type\"]";

    private String pacValue="text=Pak";

    private String packageWeight="[placeholder=\"Weight \\(lbs\\)\"]";

    private String getQuoteButton="text=Get A Quote";


    private String selectValiableList="mat-dialog-container[role=\"dialog\"] div:has-text(\"Canada PostExpedited5 Days\")";

    private String proceedButton="text=Proceed";

    private String fromCompany="text=Company *Address 1 *Address 2CanadaCountryPostal/Zip Code *British ColumbiaProvi >> [placeholder=\"Company\"]";

    private String toCompany="text=Company *Address 1 *Address 2CanadaCountryPostal/Zip Code *ManitobaProvinceCity  >> [placeholder=\"Company\"]";


    private String fromAddress="text=Address 1 *Address 2CanadaCountryPostal/Zip Code *British ColumbiaProvinceCity * >> [placeholder=\"Address 1\"]";

    private String toAddress="text=Address 1 *Address 2CanadaCountryPostal/Zip Code *ManitobaProvinceCity *Attentio >> [placeholder=\"Address 1\"]";
    private String fromAttention="text=Address 1 *Address 2CanadaCountryPostal/Zip Code *British ColumbiaProvinceCity * >> [placeholder=\"Attention\"]";

    private String toAttention="text=Address 1 *Address 2CanadaCountryPostal/Zip Code *ManitobaProvinceCity *Attentio >> [placeholder=\"Attention\"]";


    private String fromphone="text=Address 1 *Address 2CanadaCountryPostal/Zip Code *British ColumbiaProvinceCity * >> [placeholder=\"Phone\"]";

    private String toPhone="text=Address 1 *Address 2CanadaCountryPostal/Zip Code *ManitobaProvinceCity *Attentio >> [placeholder=\"Phone\"]";


    private String fromEmail="[aria-label=\"Email\"]";

    private String nextButton="text=Next";




    public String createNewQuote(){

        page.fill(fromAddressText,"fromAdd1");

        page.click(fromPostalId);
        page.fill(fromPostalId,"V0N 1B0");
        page.locator(fromPostalId).click();
        page.locator(fromPostalId).press("Tab");
//        page.click(fromStateDropDown);
//
//        page.click(fromStateValue);
//        page.click(fromCity);
//
//        page.click(fromCityValue);




        page.fill(toAddressText,"Address2");

        page.fill(toAddressText,"fromAdd2");

        page.click(toZipCode);
        page.fill(toZipCode,"R0H 0A0");

        page.locator(toZipCode).click();
        page.waitForTimeout(3000);
        page.locator(toZipCode).press("Tab");
        page.waitForTimeout(3000);

        page.click(ShippingDateCal);
        page.locator("text=30").click();


        page.click(packageType);

        page.click(pacValue);

        page.click(packageWeight);
        page.fill(packageWeight,"2");
        page.waitForTimeout(2000);
        page.keyboard().press("PageDown");

        page.click(getQuoteButton);
        page.waitForTimeout(4000);
        page.locator(selectValiableList).nth(3).click();
        page.click(proceedButton);

        page.waitForTimeout(4000);

        page.fill(fromCompany,"Company1");
        page.click(toCompany);

        page.fill(toCompany,"Company2");
        page.click(fromphone);

        page.fill(fromAddress,"Add1");
        page.click(toPhone);

        page.fill(toAddress,"ADD2");
        page.click(fromphone);

        page.waitForTimeout(1000);


        page.fill(fromphone,"8765");


        page.fill(fromAttention,"Attention1");
        page.click(toPhone);
        page.fill(toAttention,"attenstion2");

        page.click(toPhone);
        page.fill(toPhone,"3425");
        page.click(fromEmail);
        page.fill(fromEmail,"test@test.com");

        page.click(nextButton);

        page.onPopup(popup -> {
            popup.waitForLoadState();
            System.out.println(popup.title());
            page.close();
        });
      String confirmationMessage=  page.locator(".confirmed-msg").textContent();



    return confirmationMessage;

    }



    public String getnewQuoteTrackNumber(){
       String trackNumber= page.locator(".tracking-num").textContent();

       return trackNumber;
    }
    public String getnewQuoteOrderNumber(){
        String orderNumber= page.locator(".order-id").textContent();

        return orderNumber;
    }


}
