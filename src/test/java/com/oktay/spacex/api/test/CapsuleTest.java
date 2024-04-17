package com.oktay.spacex.api.test;

import com.oktay.spacex.api.listeners.MyTestListener;
import com.oktay.spacex.api.service.CapsuleService;
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

    @Test(priority=0)
    @Story("Check Matched Capsule Serials")
    public void checkMatchedCapsuleSerials(){
        List<String> capsuleSerials= capsuleService.getCapsules();
        List<String> upcomingCapsuleSerials= capsuleService.getUpcomingCapsules();
        capsuleService.checkMatchedCapsuleSerials(capsuleSerials,upcomingCapsuleSerials);
    }

}
