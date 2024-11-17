package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features = "src/test/resources/features/ContactUsPageTestCase.feature",
    glue = "stepdefinition",               
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    tags = "@CityOffice"//"@Section"
    )
public class ContactUsPageRunner extends AbstractTestNGCucumberTests{
}
