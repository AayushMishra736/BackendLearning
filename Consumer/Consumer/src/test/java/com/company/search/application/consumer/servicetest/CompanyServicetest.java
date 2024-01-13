package com.company.search.application.consumer.servicetest;

import com.company.search.application.consumer.entity.Companies;
import com.company.search.application.consumer.entity.companyResponse.CompanyResponseModel;
import com.company.search.application.consumer.service.CompanyService;
import com.company.search.application.consumer.utility.Utils;
import okhttp3.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServicetest {

    @Mock
    private Utils utils;
    @InjectMocks
    private CompanyService companyService;
    private static final String responseBody = "{\n" + "    \"page_number\": \"1\",\n" + "    \"kind\": \"search#companies\",\n" + "    \"total_results\": \"1\",\n" + "    \"items\": [\n" + "        {\n" + "            \"company_status\": \"active\",\n" + "            \"address_snippet\": \"9 New Heart Road, Birmingham, West Midlands, England, B70 0GD\",\n" + "            \"date_of_creation\": \"2023-09-11\",\n" + "            \"matchesModel\": null,\n" + "            \"description\": \"15130431 - Incorporated on 11 September 2023\",\n" + "            \"links\": {\n" + "                \"self\": \"/company/15130431\"\n" + "            },\n" + "            \"company_number\": \"15130431\",\n" + "            \"title\": \"ACTIVE ACE SECURITY LIMITED\",\n" + "            \"company_type\": \"ltd\",\n" + "            \"address\": {\n" + "                \"premises\": \"9\",\n" + "                \"postal_code\": \"B70 0GD\",\n" + "                \"country\": \"England\",\n" + "                \"locality\": \"Birmingham\",\n" + "                \"address_line_1\": \"New Heart Road\",\n" + "                \"region\": \"West Midlands\",\n" + "                \"address_line_2\": null\n" + "            },\n" + "            \"kind\": \"searchresults#company\",\n" + "            \"description_identifier\": [\n" + "                \"incorporated-on\"\n" + "            ],\n" + "            \"date_of_cessation\": null\n" + "        }\n" + "    ]\n" + "}";


    @DisplayName("Junit test for post request with flag as active and return company class.")
    @Test
    public void givenRequestbodyApikeyAndParameterThenReturnCompanyResponseModel() throws Exception {
        CompanyResponseModel companyResponseModel = null;
        Companies requestBody = new Companies();
        requestBody.setCompanyName("BBC LIMITED");
        requestBody.setCompanyNumber("15130431");

        String apiKey = "V1ms242SBw1V2cEaki7lW5d8naW1PwnU2csymY7P";
        String parameter = "active";

        Request request = new Request.Builder().url("https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Search?Query=active").header("x-api-key", "V1ms242SBw1V2cEaki7lW5d8naW1PwnU2csymY7P").post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(requestBody))).build();

        Response mockResponse = new Response.Builder().message("OK").code(200).protocol(Protocol.HTTP_1_1).request(request).build();

        when(utils.clientConnectionMethod(apiKey, new URL("https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Search?Query=active"))).thenReturn(mockResponse);

        when(utils.getPrettifiedResponseData(any(Response.class))).thenReturn(responseBody);

        companyResponseModel = companyService.companySearch(requestBody, apiKey, parameter);

        assertNotNull(companyResponseModel);
    }

    @DisplayName("Junit test for sending the response string and return company response model.")
    @Test
    public void givenResponseInStringThenReturnCompanyResponseModel() throws Exception {
        CompanyResponseModel companyResponseModel = companyService.extractCompanyDataFromResponse(responseBody);
        assertNotNull(companyResponseModel);
    }

    //As of now i am passing direct string otherwise first test case is the correct way to test this method.
    @DisplayName("Junit test for sending the CompanyResponseModel,filter criteria and company number to return filtered data.")
    @Test
    public void givenCompanyResponseModelFilterCriteriaAndCompanyNumberThenReturnCompanyResponseModel() throws Exception {
        CompanyResponseModel companyResponseModel = companyService.extractCompanyDataFromResponse(responseBody);
        String filter = "active";
        String companyNumber = "15130431";

        CompanyResponseModel companyResponseModel1 = companyService.applyFilters(companyResponseModel, filter, companyNumber);
        assertNotNull(companyResponseModel1);
    }


}
