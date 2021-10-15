package testRunner;

import org.junit.runner.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
	(
	features = ".//Features/Login.feature",
	glue = "stepDefinitions",
	dryRun = false,
	monochrome = true,
	tags = "@test",
	plugin = {"pretty",
			"html:test-output/index.html"}
	)

public class TestRun {

}
