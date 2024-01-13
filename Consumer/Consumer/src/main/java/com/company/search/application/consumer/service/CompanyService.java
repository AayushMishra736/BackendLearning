package com.company.search.application.consumer.service;

import com.company.search.application.consumer.entity.Companies;
import com.company.search.application.consumer.entity.companyResponse.CompanyResponseModel;
import com.company.search.application.consumer.entity.companyResponse.ItemsModel;
import com.company.search.application.consumer.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private static final Logger log = LoggerFactory.getLogger(CompanyService.class);
    @Autowired
    private Utils utils;

    public CompanyResponseModel companySearch(Companies requestBody, String apiKey, String parameter) throws IOException {
        log.info("Start searching company with given filters !!");
        CompanyResponseModel companyResponseModel = null;
        Response response;
        if (requestBody.getCompanyNumber() != null && parameter.equals("active")) {
            response = utils.clientConnectionMethod(apiKey, new URL("https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Search?Query=active"));
            String reponseString = utils.getPrettifiedResponseData(response);
            if (reponseString == null) {
                log.error("No data found from URL !!");
                throw new NullPointerException("No data found in the given url !!");
            }
            companyResponseModel = extractCompanyDataFromResponse(reponseString);
            CompanyResponseModel filteredCompanies = applyFilters(companyResponseModel, "active", requestBody.getCompanyNumber());
            companyResponseModel = filteredCompanies;
        } else if (requestBody.getCompanyNumber() != null) {
            response = utils.clientConnectionMethod(apiKey, new URL("https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Search?Query=dissolved"));
            String reponseString = utils.getPrettifiedResponseData(response);
            if (reponseString == null) {
                log.error("No data found from URL !!");
                throw new NullPointerException("No data found in the given url !!");
            }
            companyResponseModel = extractCompanyDataFromResponse(reponseString);
        }
        log.info("Company Data returned from given conditions !!");
        return companyResponseModel;
    }

    public CompanyResponseModel extractCompanyDataFromResponse(String response) throws JsonProcessingException {
        log.info("Converting Data from response to string !!");
        ObjectMapper objectMapper = new ObjectMapper();
        CompanyResponseModel companyResponseModel = objectMapper.readValue(response, CompanyResponseModel.class);
        log.info("Returned Data in the CompanyResponseModel format !!");
        return companyResponseModel;
    }

    //As of now this method is handling only case  i.e. active
    public CompanyResponseModel applyFilters(CompanyResponseModel companyData, String filterCriteria, String companyNumber) {
        log.info("Start filter for the data which have company status as active/not active!!");
        List<ItemsModel> filteredItems = new ArrayList<>(companyData.getItems());
        if ("active".equals(filterCriteria)) {
            filteredItems = filteredItems.stream().filter(item -> "active".equalsIgnoreCase(item.getCompany_status())).collect(Collectors.toList());
        }

        if (companyNumber != null) {
            filteredItems = filteredItems.stream().filter(item -> companyNumber.equals(item.getCompany_number())).collect(Collectors.toList());
        }
        CompanyResponseModel filteredResponse = new CompanyResponseModel();
        filteredResponse.setPage_number(companyData.getPage_number());
        filteredResponse.setKind(companyData.getKind());
        filteredResponse.setTotal_results(String.valueOf(filteredItems.size()));
        filteredResponse.setItems(filteredItems);
        log.info("Filtering completed. Total results: " + filteredItems.size());
        return filteredResponse;
    }
}
