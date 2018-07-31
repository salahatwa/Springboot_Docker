package com.docker.container;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * @author atwa Jun 20, 2018
 */
public class TokenRunnable implements Runnable {

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		final String tokenValue = obtainAccessToken("my-trusted-client", "atwa", "salah");
		long stopTime = System.currentTimeMillis();
		long specificTime = stopTime - startTime;
		System.out.println(" Excution Time:[" + specificTime + "] milliseconds");
	}

	private String obtainAccessToken(String clientId, String username, String password) {
		final Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "password");
		// params.put("client_id", clientId);
		params.put("username", username);
		params.put("password", password);
		final Response response = RestAssured.given().auth().preemptive().basic(clientId, "secret").and().with()
				.params(params).when().post("http://localhost:8085/oauth/token");

		System.out.print(">>:"+Thread.currentThread().getName()+" ** " + response.asString() + " [");
		return response.jsonPath().getString("access_token");
	}

}
