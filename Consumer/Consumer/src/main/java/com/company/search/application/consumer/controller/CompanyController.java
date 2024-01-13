package com.company.search.application.consumer.controller;

import com.company.search.application.consumer.entity.CombinedDataModel;
import com.company.search.application.consumer.entity.Companies;
import com.company.search.application.consumer.entity.companyResponse.CompanyResponseModel;
import com.company.search.application.consumer.entity.officerresponse.OfficerResponseModel;
import com.company.search.application.consumer.service.CombinedService;
import com.company.search.application.consumer.service.CompanyService;
import com.company.search.application.consumer.service.OfficerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("TruProxyAPI/rest/Companies/v1")
public class CompanyController {
    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    private CompanyService companyService;
    @Autowired
    private OfficerService officerService;
    @Autowired
    private CombinedService combinedService;

    //https://localhost:8090/TruProxyAPI/rest/Companies/v1/Search?Query=active
    //We can access all acitve companies by this url
    @PostMapping(value = "/Search", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CompanyResponseModel> companySearch(
            @RequestBody Companies requestBody,
            @RequestParam("Query") String active,
            @RequestHeader("x-api-key") String apiKey) {
        try {
            log.info("Start searching the required details by company search method !!");
            CompanyResponseModel response = companyService.companySearch(requestBody, apiKey, active);
            log.info("Finished searching the required details by company search method !!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error ocuured while searching !!");
            e.printStackTrace();
            return ResponseEntity.status(500).body(new CompanyResponseModel());
        }
    }

    //https://localhost:8090/TruProxyAPI/rest/Companies/v1/Officers?CompanyNumber={number}
    //Search officers on the basis of company number
    @PostMapping(value = "/Officers", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OfficerResponseModel> officersSearchWithDetails(
            @RequestBody Companies requestBody,
            @RequestParam(name = "CompanyNumber", required = false) String companyNumber,
            @RequestHeader("x-api-key") String apiKey) {
        String selectedCompanyNumber = null;
        try {
            if (!companyNumber.isEmpty() && requestBody.getCompanyNumber().isEmpty()) {
                selectedCompanyNumber = companyNumber;
            } else if (companyNumber.isEmpty() && !requestBody.getCompanyNumber().isEmpty()) {
                selectedCompanyNumber = requestBody.getCompanyNumber();
            } else if (!companyNumber.isEmpty() && !requestBody.getCompanyNumber().isEmpty()) {
                selectedCompanyNumber = requestBody.getCompanyNumber();
            }
            log.info("Start searching the required details by officer search method !!");
            OfficerResponseModel response = officerService.officerSearch(requestBody, apiKey, selectedCompanyNumber);
            log.info("Finished searching the required details by officer search method !!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while searching !!", e);
            return ResponseEntity.status(500).body(new OfficerResponseModel());
        }
    }

    //https://localhots:8090/TruProxyAPI/rest/Companies/v1/Searchcombined?Query=active
    //Search result combined of officers & companies on the basis of company number and flag true
    @PostMapping(value = "/Searchcombined", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CombinedDataModel> officersAndCompanySearchWithDetails(
            @RequestBody Companies requestBody,
            @RequestParam("Query") String active,
            @RequestHeader("x-api-key") String apiKey) {
        String selectedCompanyNumber = null;
        if(requestBody.getCompanyNumber().isEmpty() || apiKey.isEmpty()){
            log.error("Both value mandatory !!");
            return ResponseEntity.status(500).body(new CombinedDataModel());
        }
        try {
            if (!requestBody.getCompanyNumber().isEmpty()) {
                selectedCompanyNumber = requestBody.getCompanyNumber();
            }
            log.info("Start searching the required details by officer & company search method !!");
            CompanyResponseModel companyResponse = companyService.companySearch(requestBody, apiKey, active);
            OfficerResponseModel officeResponse = officerService.officerSearch(requestBody, apiKey, selectedCompanyNumber);
            log.info("Finished searching the required details by officer & company search method !!");
            CombinedDataModel response = combinedService.transformData(companyResponse, officeResponse);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while searching !!", e);
            return ResponseEntity.status(500).body(new CombinedDataModel());
        }
    }
}