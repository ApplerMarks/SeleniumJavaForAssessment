package stepdefinition;

import org.testng.Assert;

import core.Browser;
import io.cucumber.java.en.*;

public class ContactUsPage extends Browser{
	
	private pageobject.ContactUsPage contactUs = new pageobject.ContactUsPage(_driver);

	@Given("the user navigates to Datacom contact us page")
	public void the_user_navigates_to_datacom_contact_us_page() {
		contactUs = new pageobject.ContactUsPage(_driver);
	    contactUs.NavigateToContactUsPage();
	    contactUs.CloseCookieContainer();
	}

	@When("the user click the {string} link")
	public void the_user_click_the_link(String section) {
	    switch (section.toLowerCase()) {
		case "our locations":
			contactUs.ClickOurLocationAnchor();
			break;
		case "get in touch":
			contactUs.ClickGetInTouchAnchor();
			break;
		case "media pack":
			contactUs.ClickMediaPackAnchor();
			break;
		default:
			break;
		}
	    Delay(2);
	}
	
	@When("the user click the Our location anchor tag")
	public void the_user_click_the_our_location_anchor_tag() {
		contactUs.ClickOurLocationAnchor();
	    Delay(2);
	}
	
	@And("clicks the {string} and also clicks the {string}")
	public void clicks_the_and_also_clicks_the(String country, String city) {
		contactUs.ClickOurLocationsCountry(country);
		contactUs.ClickOurLocationsCity(city);
	}
	
	@Then("the {string} office details will show")
	public void the_office_details_will_show(String city) {
		//Verify city office address if not empty or is present
		Assert.assertTrue(!contactUs.GetCityOfficeAddress(city).isEmpty());
		//Verify if city office direction link is present
		Assert.assertTrue(contactUs.IsCityOfficeDirectionAvailable(city));
		//Verify if city office phone number available
		Assert.assertTrue(contactUs.IsCityOfficePhoneNumberAvailable(city));
		//Verify city office email address contains @datacom
		Assert.assertTrue(contactUs.GetCityOfficeEmailAddress(city).contains("datacom"));
		
	}

	@Then("the page will scroll down to that {string}")
	public void the_page_will_scroll_down_to_that(String section) {
	    switch (section.toLowerCase()) {
	    case "our locations":
			Assert.assertTrue(contactUs.IsOurLocationInCurrentView());
			break;
	    case "get in touch":
	    	Assert.assertTrue(contactUs.IsGetInTouchInCurrentView());
			break;
		case "media pack":
			Assert.assertTrue(contactUs.IsMediaPackInCurrentView());
			break;
		default:
			break;
		}
	}
	
	
}
