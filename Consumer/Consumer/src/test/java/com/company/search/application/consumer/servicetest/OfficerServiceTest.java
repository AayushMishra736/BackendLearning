package com.company.search.application.consumer.servicetest;

import com.company.search.application.consumer.entity.Companies;
import com.company.search.application.consumer.entity.officerresponse.OfficerResponseModel;
import com.company.search.application.consumer.service.OfficerService;
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
public class OfficerServiceTest {
    @Mock
    private Utils utils;
    @InjectMocks
    private OfficerService officerService;
    private static final String responseBody = "{\n" + "    \"etag\": \"ffc3423527354b17ca7f9259bd41ca1ca545abb0\",\n" + "    \"links\": {\n" + "        \"self\": \"/company/15130431/officers\"\n" + "    },\n" + "    \"kind\": \"officer-list\",\n" + "    \"items_per_page\": 35,\n" + "    \"items\": [\n" + "        {\n" + "            \"address\": {\n" + "                \"premises\": \"9 New Heart Road\",\n" + "                \"postal_code\": \"B70 0GD\",\n" + "                \"country\": \"England\",\n" + "                \"locality\": \"Birmingham\",\n" + "                \"region\": \"West Midlands\"\n" + "            },\n" + "            \"name\": \"MARTIN, Aaron\",\n" + "            \"appointed_on\": \"2023-09-11\",\n" + "            \"officer_role\": \"secretary\",\n" + "            \"links\": {\n" + "                \"officer\": {\n" + "                    \"appointments\": \"/officers/9ltz-wdkmbcQYF1CHRvx-I03wv4/appointments\"\n" + "                }\n" + "            }\n" + "        },\n" + "        {\n" + "            \"address\": {\n" + "                \"premises\": \"9 New Heart Road\",\n" + "                \"postal_code\": \"B70 0GD\",\n" + "                \"country\": \"England\",\n" + "                \"locality\": \"Birmingham\",\n" + "                \"region\": \"West Midlands\"\n" + "            },\n" + "            \"name\": \"MARTIN, Aaron\",\n" + "            \"appointed_on\": \"2023-09-11\",\n" + "            \"officer_role\": \"director\",\n" + "            \"links\": {\n" + "                \"officer\": {\n" + "                    \"appointments\": \"/officers/OFM8qT7iIdVumpljVjivKlc_uEo/appointments\"\n" + "                }\n" + "            },\n" + "            \"date_of_birth\": {\n" + "                \"month\": 12,\n" + "                \"year\": 1990\n" + "            },\n" + "            \"occupation\": \"Security Guard\",\n" + "            \"country_of_residence\": \"England\",\n" + "            \"nationality\": \"British\"\n" + "        }\n" + "    ],\n" + "    \"active_count\": 2,\n" + "    \"total_results\": 2\n" + "}";


    @DisplayName("Junit test for post request with company number and return officer class.")
    @Test
    public void givenRequestbodyApikeyAndCompanynumberThenReturnOfficerResponseModel() throws Exception {
        OfficerResponseModel officerResponseModel;
        Companies requestBody = new Companies();
        requestBody.setCompanyName("BBC LIMITED");
        requestBody.setCompanyNumber("15130431");

        String apiKey = "V1ms242SBw1V2cEaki7lW5d8naW1PwnU2csymY7P";
        Request request = new Request.Builder().url("https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Officers?CompanyNumber=15130431").header("x-api-key", apiKey).post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(requestBody))).build();

        Response mockResponse = new Response.Builder().message("OK").code(200).protocol(Protocol.HTTP_1_1).request(request).build();
        when(utils.clientConnectionMethod(apiKey, new URL("https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Officers?CompanyNumber=15130431"))).thenReturn(mockResponse);

        when(utils.getPrettifiedResponseData(any(Response.class))).thenReturn(responseBody);

        officerResponseModel = officerService.officerSearch(requestBody, apiKey, "15130431");

        assertNotNull(officerResponseModel);
    }

    @DisplayName("Junit test for sending the response string and return officer response model.")
    @Test
    public void givenResponseInStringThenReturnCompanyResponseModel() throws Exception {
        OfficerResponseModel officerResponseModel = officerService.extractOfficerDataFromResponse(responseBody);
        assertNotNull(officerResponseModel);
    }

    //As of now i am passing direct string otherwise first test case is the correct way to test this method.
    @DisplayName("Junit test for sending the OfficerResponseModel and filtering whether officer resigned or not.")
    @Test
    public void givenCompanyResponseModelFilterCriteriaAndCompanyNumberThenReturnCompanyResponseModel() throws Exception {
        OfficerResponseModel officerResponseModel = officerService.extractOfficerDataFromResponse(responseBody);
        OfficerResponseModel officerResponseModel1 = officerService.applyFilters(officerResponseModel);
        assertNotNull(officerResponseModel1);
    }


}
