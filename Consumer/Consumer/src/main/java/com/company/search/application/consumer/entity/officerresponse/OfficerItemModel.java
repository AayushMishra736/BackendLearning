package com.company.search.application.consumer.entity.officerresponse;

import com.company.search.application.consumer.entity.companyResponse.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficerItemModel {
    private AddressModel address;
    private String name;
    private String appointed_on;
    private String officer_role;
    private Links links;
    private DateOfBirth date_of_birth;
    private String occupation;
    private String country_of_residence;
    private String nationality;
    private String resigned_on;
}
