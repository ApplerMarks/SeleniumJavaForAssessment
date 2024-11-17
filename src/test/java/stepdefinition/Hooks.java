package stepdefinition;

import core.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends Browser{
	
	@Before
	public void BeforeMethod() {
		OpenBrowser();
	}
	
	@After
	public void AfterMethod() {
		CloseBrowser();
	}
	
}
