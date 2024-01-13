package com.company.search.application.consumer.entity;


import com.company.search.application.consumer.entity.companyResponse.AddressModel;
import com.company.search.application.consumer.entity.companyResponse.LinksModel;
import com.company.search.application.consumer.entity.officerresponse.OfficerItemModel;
import lombok.Data;
import java.util.List;


@Data
public class Officers {
    private String name;
    private String officer_role;
    private String appointed_on;
    private AddressModel address;
    private LinksModel links;
    List<OfficerItemModel> items;
}
