package com.company.search.application.consumer.entity.companyResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemsModel {

    private String company_status;
    private String address_snippet;
    private String date_of_creation;
    private MatchesModel matches;
    private MatchesModel matchesModel;
    private String description;
    private LinksModel links;
    private String company_number;
    private String title;
    private String snippet;
    private String company_type;
    private AddressModel address;
    private String kind;
    private String[] description_identifier;
    private String date_of_cessation;
}
