package com.oktay.spacex.api.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 *  Created by oktayuyar on 17.04.2024
 */
public class Mission {
    @JsonProperty("flight")
    public int flight;
    @JsonProperty("name")
    public String name;

    public int getFlight() {
        return flight;
    }

    public String getName() {
        return name;
    }
}
