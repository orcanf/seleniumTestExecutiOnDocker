package com.qatechhub.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibs.CommonElement;

public class ContactUsPage {

	@FindBy(id = "wpforms-49-field_1")
	private WebElement firstName;

	@FindBy(id = "wpforms-49-field_1-last")
	private WebElement lastName;

	@FindBy(id = "wpforms-49-field_2")
	private WebElement emailId;

	@FindBy(id = "wpforms-49-field_4")
	private WebElement phoneNumber;

	@FindBy(xpath = "//input[@value='Male']")
	private WebElement maleRadioButton;

	@FindBy(xpath = "//input[@value='Female']")
	private WebElement femaleRadioButton;

	@FindBy(id = "wpforms-49-field_5")
	private WebElement courseInterested;

	@FindBy(id = "wpforms-confirmation-49")
	private WebElement successMessage;

	@FindBy(id = "wpforms-submit-49")
	private WebElement submitButton;

	CommonElement elementControl;

	public ContactUsPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

		elementControl = new CommonElement();

	}

	public void fillupContactForm(ContactDetails contactDetails) throws Exception {

		elementControl.setText(firstName, contactDetails.getFirstName());

		elementControl.setText(lastName, contactDetails.getLastName());

		elementControl.setText(emailId, contactDetails.getEmailId());

		elementControl.setText(phoneNumber, contactDetails.getPhoneNumber());

		if (contactDetails.getGender().equalsIgnoreCase("male")) {
			elementControl.clickElement(maleRadioButton);
		} else {
			elementControl.clickElement(femaleRadioButton);
		}

		elementControl.selectViaVisibleText(courseInterested, contactDetails.getCourseInterestedIn());
		
		elementControl.clickElement(submitButton);

	}

	public String getSuccessMessage() throws Exception {
		return elementControl.getText(successMessage).trim();
	}

}
