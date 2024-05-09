package com.oktay.spacex.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oktay.spacex.api.constants.GeneralConstants;
import com.oktay.spacex.api.data.Capsule;
import com.oktay.spacex.api.data.Mission;
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
public class CapsuleService extends GeneralConstants {


    private final RestAssured request;
    private JsonPath jsonPathEvaluator;
    List<Mission> missions;
    ObjectMapper objectMapper = new ObjectMapper();
    List<String> capsuleSerials;
    List<String> upcomingCapsuleSerials;
    public ReadWriteValues readWriteValues = new ReadWriteValues();
    final static Logger logger = Logger.getLogger(CapsuleService.class);

    public CapsuleService() {
        request = WebServiceManager.getInstance().getRestRequest();
    }

    public List<String> getCapsules() {
        try {
            Response response = request.get(spaceXCapsulesURL);
            Assert.assertEquals(response.getStatusCode(), 200);

            jsonPathEvaluator = response.jsonPath();
            capsuleSerials = jsonPathEvaluator.getList("capsule_serial");
            logger.info("Capsule Serial Items::" + capsuleSerials);

            for (int i = 0; i < capsuleSerials.size(); i++) {
                readWriteValues.setProperty("Capsule" + i, capsuleSerials.get(i));
            }
            return capsuleSerials;
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
        return capsuleSerials;
    }

    public List<String> getUpcomingCapsules() {
        try {
            Response response = request.get(spaceXUpcomingCapsulesURL);
            Assert.assertEquals(response.getStatusCode(), 200);

            jsonPathEvaluator = response.jsonPath();
            upcomingCapsuleSerials = jsonPathEvaluator.getList("capsule_serial");
            logger.info("Upcoming Capsule Serial Items::" + upcomingCapsuleSerials);

            for (int i = 0; i < upcomingCapsuleSerials.size(); i++) {
                readWriteValues.setProperty("upcomingCapsule" + i, upcomingCapsuleSerials.get(i));
            }
            return upcomingCapsuleSerials;

        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
        return upcomingCapsuleSerials;
    }

    public void checkMatchedCapsuleSerials(List<String> capsuleSerials, List<String> upcomingCapsuleSerials) {
        try {
            ArrayList<String> matchedItems = new ArrayList<>(capsuleSerials);
            matchedItems.retainAll(upcomingCapsuleSerials);
            Assert.assertNotNull(matchedItems,"Matched Capsule Serial not found!");
            logger.info("Matched Capsule Serial Items::" + matchedItems);
            for (int i = 0; i < matchedItems.size(); i++) {
                readWriteValues.setProperty("CommonID" + i, matchedItems.get(i));
            }
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    public void checkOriginalLaunchWithCommonID(String commonID) {
        try {
            Response response = request.get(spaceXCapsulesURL + commonID);
            Assert.assertEquals(response.getStatusCode(), 200);

            jsonPathEvaluator = response.jsonPath();

            Assert.assertEquals(readWriteValues.getProperty("CommonID0"), jsonPathEvaluator.get("capsule_serial"));
            Assert.assertNull(jsonPathEvaluator.get("original_launch"), "Original launch parameter is not null!");

        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    public void checkNameOfTheSelectedFlight(String flightID, String flightName) {
        try {
            Response response = request.get(spaceXCapsulesURL);
            Assert.assertEquals(response.getStatusCode(), 200);

            String jsonResponse = response.getBody().asString();
            Capsule[] capsules = objectMapper.readValue(jsonResponse, Capsule[].class);

            for (Capsule capcule : capsules) {
                if (capcule.getMissions() != null) {
                    for (Mission mission: capcule.getMissions()) {
                        if (mission.getFlight() == 10) {
                            Assert.assertEquals(mission.getName(),readWriteValues.getProperty("flightName"),"Flight name is not matched!");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
