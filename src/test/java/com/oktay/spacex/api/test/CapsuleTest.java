package com.oktay.spacex.api.test;

import com.oktay.spacex.api.listeners.MyTestListener;
import com.oktay.spacex.api.service.CapsuleService;
import com.oktay.spacex.api.util.ReadWriteValues;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

/*
 *  Created by oktayuyar on 16.04.2024
 */
@Listeners({ MyTestListener.class })
@Epic("Regression Tests")
@Feature("Capsule Tests")
public class CapsuleTest {

    public CapsuleService capsuleService= new CapsuleService();
    public ReadWriteValues readWriteValues = new ReadWriteValues();

    @Test(priority=0)
    @Story("Check Matched Capsule Serials")
    public void checkMatchedCapsuleSerials(){
        List<String> capsuleSerials= capsuleService.getCapsules();
        List<String> upcomingCapsuleSerials= capsuleService.getUpcomingCapsules();
        capsuleService.checkMatchedCapsuleSerials(capsuleSerials,upcomingCapsuleSerials);
    }

    @Test(priority=1)
    @Story("Check Original Launch with Common ID")
    public void xcheckOriginalLaunchWithCommonID(){
        capsuleService.checkOriginalLaunchWithCommonID(readWriteValues.getProperty("CommonID0"));
    }

    @Test(priority=2)
    @Story("Check Name of The Selected Fligt")
    public void checkNameOfTheSelectedFlight(){
        capsuleService.checkNameOfTheSelectedFlight(readWriteValues.getProperty("flightID"),readWriteValues.getProperty("flightName"));
    }
}
