package com.qatechhub.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.qatechhub.pages.ContactUsPage;

import commonLibs.CommonDriver;


public class BaseTests {

	CommonDriver cmnDriver;

	String url = "https://test.qatechhub.com/form-elements";
	
	ContactUsPage contactUsPage;

	@Parameters("browserType")
	@BeforeMethod
	public void setup(String browserType) throws Exception {

		cmnDriver = new CommonDriver(browserType);

		cmnDriver.navigateToFirstUrl(url);
		
		contactUsPage = new ContactUsPage(cmnDriver.getDriver());

	}

	@AfterMethod
	public void cleanUp() throws Exception {
		cmnDriver.closeAllBrowsers();
	}

}
