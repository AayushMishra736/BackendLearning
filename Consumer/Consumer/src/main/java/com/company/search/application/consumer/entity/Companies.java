package com.company.search.application.consumer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class Companies {

    @JsonProperty("companyNumber")
    private String companyNumber;
    @JsonProperty("companyName")
    private String companyName;
    private List<Officers> officers;

}
