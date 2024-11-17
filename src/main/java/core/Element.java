package core;

import org.openqa.selenium.*;

public class Element extends Browser {

	private By by;
	private WebElement element;
	private String name;
	
	
	public Element(String name, By by) {
		this.by = by;
		this.name = name;
	}
	
	public void Click() {
		try {
			element = FindElement(by);
			element.click();
			System.out.println(name + " is clicked");
			
		}catch(Exception e) {
			element = null;
			System.out.println(e.getMessage());
		}
	}
	
	public void SetText(String text) {
		try {
			element = FindElement(by);
			element.clear();
			element.sendKeys(text);
			System.out.println(name + " field is populated with " + text);
		} catch (Exception e) {
			element = null;
			System.out.println(e.getMessage());
		}
	}
	
	public String GetText() {
		element = FindElement(by);
		return element == null ? null : element.getText();
	}
	
	public boolean IsElementDisplayed() {
		element = FindElement(by);
		return element == null ? false : element.isDisplayed();
	}
	
	public boolean IsElementEnabled() {
		element = FindElement(by);
		return element == null ? false : element.isEnabled();
	}
	
	public String GetElementAttribute(String attribute) {
		element = FindElement(by);
		return element.getAttribute(attribute);
	}
	
	public boolean IsElementPresentInTheCurrentView() {
		element = FindElement(by);
		/*
		 * String script = "var rect = arguments[0].getBoundingClientRect();" +
		 * "return (" + "rect.top >= 0 && " + "rect.left >= 0 && " +
		 * "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && "
		 * + "rect.right <= (window.innerWidth || document.documentElement.clientWidth)"
		 * + ");";
		 */
		String script = 
	            "var elem = arguments[0],                 " +
	            "    rect = elem.getBoundingClientRect(), " +
	            "    windowHeight = (window.innerHeight || document.documentElement.clientHeight), " +
	            "    windowWidth = (window.innerWidth || document.documentElement.clientWidth); " +
	            "return (                                 " +
	            "    rect.top < windowHeight &&           " + // Element's top is above viewport's bottom
	            "    rect.bottom > 0 &&                   " + // Element's bottom is below viewport's top
	            "    rect.left < windowWidth &&           " + // Element's left is before viewport's right
	            "    rect.right > 0                       " + // Element's right is after viewport's left
	            ");";
			boolean condtion = (Boolean) jsExecutor.executeScript(script, element);
	        return condtion;
	}
}
