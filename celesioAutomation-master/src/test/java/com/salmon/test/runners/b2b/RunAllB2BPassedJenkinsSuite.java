package com.salmon.test.runners.b2b;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "target/test-classes", tags = {"@b2b","~@b2b_blocked","~@b2b_wip", "~@b2b_failed", "~@devsmoketestplp"}, 
monochrome = true, plugin = { "pretty","html:target/cucumber-report/runallb2bpassedjenkins",
                "json:target/cucumber-report/runallb2bpassedjenkins/cucumber.json",
                "rerun:target/cucumber-report/runallb2bpassedjenkins/rerun.txt" }, glue = "com.salmon.test")
public class RunAllB2BPassedJenkinsSuite extends AbstractTestNGCucumberTests{

}
