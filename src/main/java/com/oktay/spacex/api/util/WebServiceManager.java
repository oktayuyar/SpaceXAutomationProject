package com.oktay.spacex.api.util;

import com.oktay.spacex.api.constants.GeneralConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;

/*
 *  Created by oktayuyar on 16.04.2024
 */
public class WebServiceManager extends GeneralConstants {

    private String baseURL = spaceXUrl;
    private static WebServiceManager instance = null;
    private final RestAssured restRequest = new RestAssured();

    private WebServiceManager() {

        RestAssuredConfig config = restRequest.config()
                .httpClient(HttpClientConfig.httpClientConfig());

        restRequest.baseURI = baseURL;

        restRequest.requestSpecification =
                new RequestSpecBuilder().
                        build().accept(ContentType.JSON).
                        contentType(ContentType.JSON).
                        config(config);
    }

    public static WebServiceManager getInstance() {
        if (instance == null) {
            instance = new WebServiceManager();
        }
        return instance;
    }

    public RestAssured getRestRequest() {
        return restRequest;
    }

}



