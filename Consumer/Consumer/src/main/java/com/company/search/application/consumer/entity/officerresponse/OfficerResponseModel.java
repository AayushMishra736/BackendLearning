package com.company.search.application.consumer.entity.officerresponse;

import com.company.search.application.consumer.entity.companyResponse.LinksModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficerResponseModel {
    private String etag;
    private int inactive_count;
    private LinksModel links;
    private String kind;
    private int items_per_page;
    private List<OfficerItemModel> items;
    private int active_count;
    private int total_results;
    private int resigned_count;
}
