package com.company.search.application.consumer.IntegrationTestCase;

import com.company.search.application.consumer.entity.Companies;
import com.company.search.application.consumer.entity.officerresponse.OfficerResponseModel;
import com.company.search.application.consumer.service.OfficerService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = OfficerServiceIntegrationTest.class)
public class OfficerServiceIntegrationTest {
    private static final String responseBody = "{\n" + "    \"etag\": \"ffc3423527354b17ca7f9259bd41ca1ca545abb0\",\n" + "    \"links\": {\n" + "        \"self\": \"/company/15130431/officers\"\n" + "    },\n" + "    \"kind\": \"officer-list\",\n" + "    \"items_per_page\": 35,\n" + "    \"items\": [\n" + "        {\n" + "            \"address\": {\n" + "                \"premises\": \"9 New Heart Road\",\n" + "                \"postal_code\": \"B70 0GD\",\n" + "                \"country\": \"England\",\n" + "                \"locality\": \"Birmingham\",\n" + "                \"region\": \"West Midlands\"\n" + "            },\n" + "            \"name\": \"MARTIN, Aaron\",\n" + "            \"appointed_on\": \"2023-09-11\",\n" + "            \"officer_role\": \"secretary\",\n" + "            \"links\": {\n" + "                \"officer\": {\n" + "                    \"appointments\": \"/officers/9ltz-wdkmbcQYF1CHRvx-I03wv4/appointments\"\n" + "                }\n" + "            }\n" + "        },\n" + "        {\n" + "            \"address\": {\n" + "                \"premises\": \"9 New Heart Road\",\n" + "                \"postal_code\": \"B70 0GD\",\n" + "                \"country\": \"England\",\n" + "                \"locality\": \"Birmingham\",\n" + "                \"region\": \"West Midlands\"\n" + "            },\n" + "            \"name\": \"MARTIN, Aaron\",\n" + "            \"appointed_on\": \"2023-09-11\",\n" + "            \"officer_role\": \"director\",\n" + "            \"links\": {\n" + "                \"officer\": {\n" + "                    \"appointments\": \"/officers/OFM8qT7iIdVumpljVjivKlc_uEo/appointments\"\n" + "                }\n" + "            },\n" + "            \"date_of_birth\": {\n" + "                \"month\": 12,\n" + "                \"year\": 1990\n" + "            },\n" + "            \"occupation\": \"Security Guard\",\n" + "            \"country_of_residence\": \"England\",\n" + "            \"nationality\": \"British\"\n" + "        }\n" + "    ],\n" + "    \"active_count\": 2,\n" + "    \"total_results\": 2\n" + "}";

    @MockBean
    private OfficerService officerService;
    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void givenCompanyNumberThenAssertResultForOfficerResult() throws IOException {
        Companies requestBody = new Companies();
        requestBody.setCompanyName("BBC LIMITED");
        requestBody.setCompanyNumber("12345656");

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/TruProxyAPI/rest/Companies/v1/Search?Query=active")).willReturn(WireMock.aResponse().withStatus(200).withHeader("x-api-key", "V1ms242SBw1V2cEaki7lW5d8naW1PwnU2csymY7P").withBody(responseBody)));

        OfficerResponseModel result = officerService.officerSearch(requestBody, "V1ms242SBw1V2cEaki7lW5d8naW1PwnU2csymY7P", "12345656");

        assert(result.getItems()).isEmpty();
    }
}
