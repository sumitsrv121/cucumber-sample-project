package com.srv.sumit.cucumber_demo;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectDirectories;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/resources/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.srv.sumit.cucumber_demo.glue,com.srv.sumit.cucumber_demo.steps")
@ConfigurationParameter(key = Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
public class CucumberTestRunner {
}
