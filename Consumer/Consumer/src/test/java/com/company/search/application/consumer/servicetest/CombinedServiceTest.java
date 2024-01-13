package com.company.search.application.consumer.servicetest;
import com.company.search.application.consumer.entity.CombinedDataModel;
import com.company.search.application.consumer.entity.companyResponse.*;
import com.company.search.application.consumer.entity.officerresponse.OfficerItemModel;
import com.company.search.application.consumer.entity.officerresponse.OfficerResponseModel;
import com.company.search.application.consumer.service.CombinedService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class CombinedServiceTest {

    @Mock
    private CombinedService combinedService;

    @DisplayName("Junit test for sending the companyResponseModel and officerResponseModel to return combined result.")
    @Test
    public void givenCoampanyResponseModelAndOfficerResponseModelThenReturnCombinedResult() throws Exception {
        CompanyResponseModel companyResponseModel = CompanyResponseModel.builder()
                .page_number("1")
                .kind("company")
                .total_results("3")
                .items(Collections.singletonList(
                        ItemsModel.builder()
                                .company_status("active")
                                .address_snippet("123 Main St")
                                .date_of_creation("2023-01-15")
                                .description("Sample Description")
                                .links(LinksModel.builder().self("self").build())
                                .company_number("ABC123")
                                .title("Company ABC")
                                .company_type("Public")
                                .address(AddressModel.builder()
                                        .premises("Suite 101")
                                        .postal_code("12345")
                                        .country("USA")
                                        .locality("New York")
                                        .address_line_1("123 Elm St")
                                        .region("NY")
                                        .address_line_2("Apt 2")
                                        .build())
                                .build()
                ))
                .build();

        OfficerResponseModel officerResponseModel = OfficerResponseModel.builder()
                .etag("77f16590794fdbfe2dd6f64691422c90af0e2222")
                .inactive_count(0)
                .links(LinksModel.builder().self("/company/13160226/officers").build())
                .kind("officer-list")
                .items_per_page(35)
                .items(Collections.singletonList(
                        OfficerItemModel.builder()
                                .address(AddressModel.builder()
                                        .premises("85")
                                        .postal_code("W1W 7LT")
                                        .country("United Kingdom")
                                        .locality("London")
                                        .address_line_1("Great Portland Street, First Floor")
                                        .build())
                                .name("MIDDLETON, Pierre David")
                                .appointed_on("2021-01-26")
                                .officer_role("director")
                                .occupation("Director")
                                .country_of_residence("England")
                                .nationality("British")
                                .resigned_on(null)
                                .build()
                ))
                .active_count(1)
                .total_results(Integer.parseInt("1"))
                .resigned_count(0)
                .build();

        CombinedDataModel combinedDataModel = combinedService.transformData(companyResponseModel,officerResponseModel);

        assertNull(combinedDataModel);
    }

}
