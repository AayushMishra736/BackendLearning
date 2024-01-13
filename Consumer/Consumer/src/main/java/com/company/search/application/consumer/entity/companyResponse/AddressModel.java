package com.company.search.application.consumer.entity.companyResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
    private String premises;
    private String postal_code;
    private String country;
    private String locality;
    private String address_line_1;
    private String region;
    private String address_line_2;
}
