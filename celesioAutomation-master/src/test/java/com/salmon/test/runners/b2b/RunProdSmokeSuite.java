package com.salmon.test.runners.b2b;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "target/test-classes", tags = {"@b2b", "@prodsmoketest"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/runprodsmoke",
        "json:target/cucumber-report/runprodsmoke/cucumber.json",
        "rerun:target/cucumber-report/runprodsmoke/rerun.txt"},
        glue = "com.salmon.test")
public class RunProdSmokeSuite extends AbstractTestNGCucumberTests {
}
