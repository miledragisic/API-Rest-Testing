package com.bookit.step_definitions;

import com.bookit.utilities.Driver;
import com.bookit.utilities.Environment;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before("@ui")
    public void setUp() {
        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();
    }

    @Before("@ws")
    public void setUpWs(){
        RestAssured.baseURI = Environment.BASE_URI;
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        System.out.println("I am reporting the results");
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        System.out.println("Closing driver");
        Driver.closeDriver();
    }

}