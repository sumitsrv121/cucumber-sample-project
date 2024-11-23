package com.srv.sumit.cucumber_demo.glue;


import com.srv.sumit.cucumber_demo.CucumberDemoApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CucumberDemoApplication.class)
public class CucumberSpringConfiguration {
}
