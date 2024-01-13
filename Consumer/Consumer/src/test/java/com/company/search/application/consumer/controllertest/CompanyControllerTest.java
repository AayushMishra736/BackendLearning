package com.company.search.application.consumer.controllertest;

import com.company.search.application.consumer.controller.CompanyController;
import com.company.search.application.consumer.entity.Companies;
import com.company.search.application.consumer.service.CombinedService;
import com.company.search.application.consumer.service.CompanyService;
import com.company.search.application.consumer.service.OfficerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CompanyService companyService;
    @MockBean
    private OfficerService officerService;
    @MockBean
    private CombinedService combinedService;

    @DisplayName("Junit test for post request with flag as active and return company class.")
    @Test
    public void givenFlagAsActiveThenReturnCompanyAsResponse() throws Exception {
        Companies requestBody = new Companies();
        requestBody.setCompanyNumber("13001862");
        requestBody.setCompanyName("BBC LIMITED");

        ResultActions response = mockMvc.perform(post("/TruProxyAPI/rest/Companies/v1/Search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .param("Query", "active")
                .header("x-api-key", "V1ms242SBw1V2cEaki7lW5d8naW1PwnU2csymY7P"));

        response.andExpect(status().isOk());
    }

    @DisplayName("Junit test for post request with company number and return officer class.")
    @Test
    public void givenCompanyNumberThenReturnOfficerAsResponse() throws Exception {
        Companies requestBody = new Companies();
        requestBody.setCompanyNumber("13001862");
        requestBody.setCompanyName("BBC LIMITED");

        String companyNumber = "13001862";

        ResultActions response = mockMvc.perform(post("/TruProxyAPI/rest/Companies/v1/Officers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .param("CompanyNumber", companyNumber)
                .header("x-api-key", "V1ms242SBw1V2cEaki7lW5d8naW1PwnU2csymY7P"));

        response.andExpect(status().isOk());
    }

    @DisplayName("Junit test for post request on the basis of company number and flag true and get combined result.")
    @Test
    public void givenCompanyNumberAndFlagAsActiveThenReturnCombinedResponse() throws Exception {
        Companies requestBody = new Companies();
        requestBody.setCompanyNumber("13001862");
        requestBody.setCompanyName("BBC LIMITED");

        String companyNumber = "13001862";

        ResultActions response = mockMvc.perform(post("/TruProxyAPI/rest/Companies/v1/Searchcombined")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .param("Query", "active")
                .header("x-api-key", "V1ms242SBw1V2cEaki7lW5d8naW1PwnU2csymY7P"));

        response.andExpect(status().isOk());
    }

}
