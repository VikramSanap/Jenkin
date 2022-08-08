package com.iris22a.cucumbertests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources", glue = {"com.iris22a.stepdefination"})
public class RunnerTest extends AbstractTestNGCucumberTests {

}
