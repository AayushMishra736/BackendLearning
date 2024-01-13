package com.company.search.application.consumer.entity.companyResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchesModel {
    private String[] title;
    private String[] snippet;
}
