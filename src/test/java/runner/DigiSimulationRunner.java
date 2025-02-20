package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions
(
		
		features = {"C:\\Users\\Pc\\eclipse-workspace\\DigisimulationAPIsRestAssured\\src\\test\\resources"},
		glue = {"steps"},
		plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
		tags="@Smoke",
		publish = true
		
	
)
public class DigiSimulationRunner extends AbstractTestNGCucumberTests {
	

	
} 
