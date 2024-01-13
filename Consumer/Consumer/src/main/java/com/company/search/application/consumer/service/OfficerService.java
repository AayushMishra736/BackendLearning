package com.company.search.application.consumer.service;

import com.company.search.application.consumer.entity.Companies;
import com.company.search.application.consumer.entity.officerresponse.OfficerItemModel;
import com.company.search.application.consumer.entity.officerresponse.OfficerResponseModel;
import com.company.search.application.consumer.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficerService {
    private static final Logger log = LoggerFactory.getLogger(OfficerService.class);
    @Autowired
    private Utils utils;

    public OfficerResponseModel officerSearch(Companies requestBody, String apiKey, String companyNumber) throws IOException {
        log.info("Start searching officerss with given filters !!");
        OfficerResponseModel officerResponseModel = null;
        Response response = utils.clientConnectionMethod(apiKey, new URL("https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Officers?CompanyNumber=" + companyNumber));
        String reponseString = utils.getPrettifiedResponseData(response);
        if (reponseString == null) {
            log.error("No data found from URL !!");
            return (OfficerResponseModel) ResponseEntity.status(500);
        }
        officerResponseModel = extractOfficerDataFromResponse(reponseString);
        OfficerResponseModel filteredOfficers = applyFilters(officerResponseModel);
        officerResponseModel = filteredOfficers;
        log.info("Officers returned with given filters !!");
        return officerResponseModel;
    }

    public OfficerResponseModel applyFilters(OfficerResponseModel officerResponseModel) {
        log.info("Start filtering officer on the basis of resign !!");
        List<OfficerItemModel> filteredItems = officerResponseModel.getItems().stream()
                .filter(officerItem -> officerItem.getResigned_on() == null)
                .collect(Collectors.toList());
        OfficerResponseModel filteredOfficerResponseModel = new OfficerResponseModel();
        filteredOfficerResponseModel.setEtag(officerResponseModel.getEtag());
        filteredOfficerResponseModel.setLinks(officerResponseModel.getLinks());
        filteredOfficerResponseModel.setInactive_count(officerResponseModel.getInactive_count());
        filteredOfficerResponseModel.setKind(officerResponseModel.getKind());
        filteredOfficerResponseModel.setItems_per_page(officerResponseModel.getItems_per_page());
        filteredOfficerResponseModel.setItems(filteredItems);
        filteredOfficerResponseModel.setActive_count(officerResponseModel.getActive_count());
        filteredOfficerResponseModel.setTotal_results(officerResponseModel.getItems().size());
        filteredOfficerResponseModel.setResigned_count(0);
        log.info("Return filtered officer on the basis of resign !!");
        return filteredOfficerResponseModel;
    }

    public OfficerResponseModel extractOfficerDataFromResponse(String reponseString) throws JsonProcessingException {
        log.info("Start Extracting data form JSON!!");
        ObjectMapper objectMapper = new ObjectMapper();
        OfficerResponseModel officerResponseModel = objectMapper.readValue(reponseString, OfficerResponseModel.class);
        log.info("Returned Extracted data form JSON!!");
        return officerResponseModel;
    }
}
