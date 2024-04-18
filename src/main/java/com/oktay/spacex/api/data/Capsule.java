package com.oktay.spacex.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 *  Created by oktayuyar on 17.04.2024
 */
public class Capsule {
    @JsonProperty("capsule_serial")
    String capsuleSerial;
    @JsonProperty("capsule_id")
    String capsuleID;
    @JsonProperty("status")
    String status;
    @JsonProperty("original_launch")
    String originalLaunch;
    @JsonProperty("original_launch_unix")
    Integer originalLaunchUnix;
    @JsonProperty("missions")
    Mission[] missions;
    @JsonProperty("landings")
    Integer landings;
    @JsonProperty("type")
    String type;
    @JsonProperty("details")
    String details;
    @JsonProperty("reuse_count")
    String reuse_count;


    public String getCapsuleSerial() {
        return capsuleSerial;
    }

    public String getCapsuleID() {
        return capsuleID;
    }

    public String getStatus() {
        return status;
    }

    public String getOriginalLaunch() {
        return originalLaunch;
    }

    public Integer getOriginalLaunchUnix() {
        return originalLaunchUnix;
    }

    public Mission[] getMissions() {
        return missions;
    }

    public Integer getLandings() {
        return landings;
    }

    public String getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }

    public String getReuse_count() {
        return reuse_count;
    }
}
