package cucumber.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void beforeScenario() {
        System.out.println("Configuration is Set");
    }

    @After
    public void afterScenario() {
        System.out.println("Configuration is Destroyed");
    }
}
