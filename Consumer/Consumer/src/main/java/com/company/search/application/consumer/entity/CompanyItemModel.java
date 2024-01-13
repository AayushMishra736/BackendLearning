package com.company.search.application.consumer.entity;

import com.company.search.application.consumer.entity.companyResponse.AddressModel;
import lombok.Data;

import java.util.List;

@Data
public class CompanyItemModel {
    private String company_number;
    private String company_type;
    private String title;
    private String company_status;
    private String date_of_creation;
    private AddressModel address;
    private List<Officers> officers;
}
