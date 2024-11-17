package core;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
	public static WebDriver _driver;
	private Duration timeOut = Duration.ofSeconds(120);
	private WebElement element;
	public JavascriptExecutor jsExecutor = (JavascriptExecutor) _driver;
	
	protected void OpenBrowser() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--start-maximized");
		//options.addArguments("--disable-popup-blocking");
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		options.setImplicitWaitTimeout(timeOut);
		options.addArguments("--headless");
		_driver = new ChromeDriver(options);
		_driver.manage().timeouts().pageLoadTimeout(timeOut);
	}
	
	protected void CloseBrowser() {
		_driver.quit();
	}
	
	public void Delay(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		}catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void NavigateToUrl(String url) {
		_driver.navigate().to(url);
		WaitPageToLoad();
		System.out.println("Session navigates to URL - " + url);
	}
	
	public void WaitPageToLoad(){
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(_driver)
				.withTimeout(Duration.ofMinutes(2))
				.pollingEvery(Duration.ofSeconds(1))
				.withMessage("Page is not loaded")
				.ignoring(Exception.class);
		wait.until(x -> {
					String readyState = jsExecutor.executeScript("return document.readyState").toString();
					return readyState.equalsIgnoreCase("complete");}); 
	}
	
	public WebElement FindElement(By by) {
		try {
			element = new WebDriverWait(_driver, timeOut)
					.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		}catch (NoSuchElementException e) {
			System.err.println(e.getMessage());
			element = null;
		}
		return element;
	}
	
}
