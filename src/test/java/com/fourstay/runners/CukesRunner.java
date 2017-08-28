package com.fourstay.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/cucmber-reports", "json:target/cucumber.json"}, 
				features = "src/test/resources/features", 
				glue = "com/fourstay/step_definitions",
				tags = "@Regression", dryRun = false)
public class CukesRunner {

}
