package com.company.search.application.consumer.IntegrationTestCase;

import com.company.search.application.consumer.entity.Companies;
import com.company.search.application.consumer.entity.companyResponse.CompanyResponseModel;
import com.company.search.application.consumer.service.CompanyService;
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
@ContextConfiguration(classes = CompanyServiceIntegrationTest.class)
public class CompanyServiceIntegrationTest {
    private static final String responseBody = "{\n" + "    \"total_results\": 1,\n" + "    \"items\": [\n" + "        {\n" + "            \"company_number\": \"13160226\",\n" + "            \"company_type\": \"ltd\",\n" + "            \"title\": \"ACTIVE ACCOUNTS PAYROLL LTD\",\n" + "            \"company_status\": \"active\",\n" + "            \"date_of_creation\": \"2021-01-26\",\n" + "            \"address\": {\n" + "                \"premises\": \"13160226\",\n" + "                \"postal_code\": \"CF14 8LH\",\n" + "                \"country\": null,\n" + "                \"locality\": \"Cardiff\",\n" + "                \"address_line_1\": \": COMPANIES HOUSE DEFAULT ADDRESS\",\n" + "                \"region\": null,\n" + "                \"address_line_2\": null\n" + "            },\n" + "            \"officers\": [\n" + "                {\n" + "                    \"name\": \"MIDDLETON, Pierre David\",\n" + "                    \"officer_role\": \"director\",\n" + "                    \"appointed_on\": \"2021-01-26\",\n" + "                    \"address\": {\n" + "                        \"premises\": null,\n" + "                        \"postal_code\": null,\n" + "                        \"country\": null,\n" + "                        \"locality\": null,\n" + "                        \"address_line_1\": null,\n" + "                        \"region\": null,\n" + "                        \"address_line_2\": null\n" + "                    },\n" + "                    \"links\": null,\n" + "                    \"items\": null\n" + "                }\n" + "            ]\n" + "        }\n" + "    ]\n" + "}";

    @MockBean
    private CompanyService companyService;
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
    public void givenCompanyNameAndNumberThenAssertResult() throws IOException {
        Companies requestBody = new Companies();
        requestBody.setCompanyName("BBC LIMITED");
        requestBody.setCompanyNumber("13160226");

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/TruProxyAPI/rest/Companies/v1/Search?Query=active")).
                willReturn(WireMock.aResponse().withStatus(200).
                        withHeader("x-api-key", "V1ms242SBw1V2cEaki7lW5d8naW1PwnU2csymY7P").withBody(responseBody)));

        CompanyResponseModel result = companyService.companySearch(requestBody, "V1ms242SBw1V2cEaki7lW5d8naW1PwnU2csymY7P", "active");

        assert(result.getTotal_results()).equals("1");
     }
}
