package pageobject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Browser;
import core.Element;

public class ContactUsPage extends Browser {

	WebDriver driver;
	//Cookie container
	private Element cookieContainer = new Element("Cookie container", By.cssSelector("#onetrust-banner-sdk"));
	private Element cookieAcceptAllBtn = new Element("Cookie accept all", By.cssSelector("#onetrust-accept-btn-handler"));
	private Element cookiePreferenceBtn = new Element("Cookie accept all", By.cssSelector("#onetrust-pc-btn-handler"));
	//Anchor links desktop
	private Element anchorOurLocation = new Element("Our location anchor", By.cssSelector("#anchor-arrow-left + ol a[href*='our-locations']"));
	private Element anchorGetInTouch = new Element("Our location anchor", By.cssSelector("#anchor-arrow-left + ol a[href*='get-in-touch']"));
	private Element anchorMediaPack = new Element("Our location anchor", By.cssSelector("#anchor-arrow-left + ol a[href*='media-pack']"));
	
	//Section
	private Element sectionOurLocation = new Element("Our location section", By.cssSelector(".cmp-location"));
	private Element sectionGetInTouch = new Element("Get in touch section", By.xpath("//h2[text()='Get in touch']//parent::div"));
	private Element sectionMediaPack = new Element("Media pack section", By.xpath(" //h2[text()='Our media pack']//parent::div"));
	
	
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void NavigateToContactUsPage() {
		NavigateToUrl("https://datacom.com/nz/en/contact-us");
	}
	
	public void CloseCookieContainer() {
		if(cookieContainer.IsElementDisplayed()) {
			cookieAcceptAllBtn.Click();
			Delay(3);
		}
	}
	
	public void ClickOurLocationAnchor() {
		if(anchorOurLocation.IsElementDisplayed()) {
			anchorOurLocation.Click();
		}
	}
	
	public void ClickGetInTouchAnchor() {
		if(anchorGetInTouch.IsElementDisplayed()) {
			anchorGetInTouch.Click();
		}
	}
	
	public void ClickMediaPackAnchor() {
		if(anchorMediaPack.IsElementDisplayed()) {
			anchorMediaPack.Click();
		}
	}
	
	public boolean IsOurLocationInCurrentView() {
		return sectionOurLocation.IsElementPresentInTheCurrentView();
	}
	
	public boolean IsGetInTouchInCurrentView() {
		return sectionGetInTouch.IsElementPresentInTheCurrentView();
	}
	
	public boolean IsMediaPackInCurrentView() {
		return sectionMediaPack.IsElementPresentInTheCurrentView();
	}
	
	public void ClickOurLocationsCountry(String country) {
		Element element = new Element(country + " country", By.xpath("//li[normalize-space(text()) = '"+ country +"']"));
		if(element.IsElementDisplayed()) {
			element.Click();
			Delay(2);
		}
	}
	
	public void ClickOurLocationsCity(String city) {
		Element element = new Element(city + " city office", By.xpath("//div[normalize-space(text()) = '"+ city +"']"));
		if(element.IsElementDisplayed() && !element.GetElementAttribute("class").contains("open")) {
			element.Click();
			Delay(2);
		} else {
			System.out.println(city + " city office panel is already open");
		}
	}
	
	
	public String GetCityOfficeAddress(String city) {
		Element officeAddress = new Element(city + " city office address", By.xpath("//div[normalize-space(text()) = '" + city + "']/following-sibling::div//p[contains(@class,'cmp-location__location__address')]"));
		if(officeAddress.IsElementDisplayed()) {
			System.out.println(city + " address is " + officeAddress.GetText());
			return officeAddress.GetText();
		}
		return null;
	}
	
	public Boolean IsCityOfficeDirectionAvailable(String city) {
		Element officeDirection = new Element(city + " city office direction", By.xpath("//div[normalize-space(text()) = '" + city + "']/following-sibling::div//p[contains(@class,'cmp-location__location__direction')]"));
		if(officeDirection.IsElementDisplayed() && officeDirection.IsElementEnabled()) {
			return true;
		}
		return false;
	}
	
	public Boolean IsCityOfficePhoneNumberAvailable(String city) {
		Element officePhoneNumber = new Element(city + " city office phone number", By.xpath("//div[normalize-space(text()) = '" + city + "']/following-sibling::div//p[contains(@class,'cmp-location__location__phone')]"));
		if(officePhoneNumber.IsElementDisplayed() && officePhoneNumber.IsElementEnabled()) {
			return true;
		}
		return false;
	}
	
	public String GetCityOfficeEmailAddress(String city) {
		Element officeEmailAddress = new Element(city + " city office email address", By.xpath("//div[normalize-space(text()) = '" + city + "']/following-sibling::div//p[contains(@class,'cmp-location__location__email')]/a"));
		if(officeEmailAddress.IsElementDisplayed()) {
			System.out.println(city + " address is " + officeEmailAddress.GetText());
			return officeEmailAddress.GetText();
		}
		return null;
	}
}
