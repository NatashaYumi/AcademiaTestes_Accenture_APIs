package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = "stepDefinitions", 
		tags = "@Ct01",
		plugin = {"pretty","html:target/report-html.html", "json:target/report.json"}, 
		monochrome = true)
public class RunnerAPI {

}
