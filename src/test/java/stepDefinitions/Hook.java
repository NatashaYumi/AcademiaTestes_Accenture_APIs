package stepDefinitions;


import static io.restassured.RestAssured.given;

import io.cucumber.java.Before;
import utils.DSL;

public class Hook extends DSL {

	@Before
	public void initTest() {
		DSL.request = given().header("Accept", "application/json")
				.header("Content-Type", "application/json");

	}

}
