package com.oktay.spacex.api.service;

import com.oktay.spacex.api.util.ReadWriteValues;
import com.oktay.spacex.api.util.WebServiceManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/*
 *  Created by oktayuyar on 16.04.2024
 */
public class CapsuleService {

    private final RestAssured request;
    private JsonPath jsonPathEvaluator;
    List<String> capsuleSerials;
    List<String> upcomingCapsuleSerials;
    public ReadWriteValues readWriteValues = new ReadWriteValues();
    final static Logger logger = Logger.getLogger(CapsuleService.class);

    public CapsuleService() {
        request = WebServiceManager.getInstance().getRestRequest();
    }

    public List<String> getCapsules() {
        try {
            Response response = request.get("/v3/capsules");
            Assert.assertEquals(response.getStatusCode(), 200);

            jsonPathEvaluator = response.jsonPath();
            capsuleSerials = jsonPathEvaluator.getList("capsule_serial");

            for (int i = 0; i < capsuleSerials.size(); i++) {
                readWriteValues.setProperty("Capsule" + i, capsuleSerials.get(i));
            }
            return capsuleSerials;
        } catch (Exception ex) {
            Assert.fail("The capsule list is null!");
        }
        return capsuleSerials;
    }

    public List<String> getUpcomingCapsules() {
        try {
            Response response = request.get("/v3/capsules/upcoming");
            Assert.assertEquals(response.getStatusCode(), 200);

            jsonPathEvaluator = response.jsonPath();
            upcomingCapsuleSerials = jsonPathEvaluator.getList("capsule_serial");

            for (int i = 0; i < upcomingCapsuleSerials.size(); i++) {
                readWriteValues.setProperty("upcomingCapsule" + i, upcomingCapsuleSerials.get(i));
            }
            return upcomingCapsuleSerials;

        } catch (Exception ex) {
            Assert.fail("The capsule list is null!");
        }
        return upcomingCapsuleSerials;
    }

    public void checkMatchedCapsuleSerials(List<String> capsuleSerials, List<String> upcomingCapsuleSerials) {
        ArrayList<String> matchedItems = new ArrayList<>(capsuleSerials);
        matchedItems.retainAll(upcomingCapsuleSerials);
        logger.info("Matched Capsule Serial Items::" + matchedItems);
        for (int i = 0; i < matchedItems.size(); i++){
            readWriteValues.setProperty("matchedCapsule" + i,matchedItems.get(i));
        }
    }
}