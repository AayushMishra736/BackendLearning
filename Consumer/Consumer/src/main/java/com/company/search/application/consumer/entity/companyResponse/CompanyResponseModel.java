package com.company.search.application.consumer.entity.companyResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseModel {
    private String page_number;
    private String kind;
    private String total_results;
    private List<ItemsModel> items;

}
