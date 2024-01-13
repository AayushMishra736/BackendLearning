package com.company.search.application.consumer.service;

import com.company.search.application.consumer.entity.CombinedDataModel;
import com.company.search.application.consumer.entity.CompanyItemModel;
import com.company.search.application.consumer.entity.Officers;
import com.company.search.application.consumer.entity.companyResponse.AddressModel;
import com.company.search.application.consumer.entity.companyResponse.CompanyResponseModel;
import com.company.search.application.consumer.entity.companyResponse.ItemsModel;
import com.company.search.application.consumer.entity.officerresponse.OfficerItemModel;
import com.company.search.application.consumer.entity.officerresponse.OfficerResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CombinedService {
    private static final Logger log = LoggerFactory.getLogger(CombinedService.class);

    public CombinedDataModel transformData(CompanyResponseModel companyResponse, OfficerResponseModel officerResponse) {
        log.info("Start converting data !!");
        CombinedDataModel finalResult = new CombinedDataModel();
        List<CompanyItemModel> companyInfoList = new ArrayList<>();
        for (ItemsModel companyItem : companyResponse.getItems()) {
            CompanyItemModel companyInfo = new CompanyItemModel();
            companyInfo.setCompany_number(companyItem.getCompany_number());
            companyInfo.setCompany_type(companyItem.getCompany_type());
            companyInfo.setTitle(companyItem.getTitle());
            companyInfo.setCompany_status(companyItem.getCompany_status());
            companyInfo.setDate_of_creation(companyItem.getDate_of_creation());

            AddressModel address = new AddressModel();
            address.setLocality(companyItem.getAddress().getLocality());
            address.setPostal_code(companyItem.getAddress().getPostal_code());
            address.setPremises(companyItem.getAddress().getPremises());
            address.setAddress_line_1(companyItem.getAddress().getAddress_line_1());
            address.setCountry(companyItem.getAddress().getCountry());
            companyInfo.setAddress(address);

            List<Officers> officers = new ArrayList<>();
            for (OfficerItemModel officerItem : officerResponse.getItems()) {
                Officers officer = new Officers();
                officer.setName(officerItem.getName());
                officer.setOfficer_role(officerItem.getOfficer_role());
                officer.setAppointed_on(officerItem.getAppointed_on());
                AddressModel officerAddress = new AddressModel();
                officer.setAddress(officerAddress);
                officers.add(officer);
            }
            companyInfo.setOfficers(officers);
            companyInfoList.add(companyInfo);
        }
        finalResult.setTotal_results(companyInfoList.size());
        finalResult.setItems(companyInfoList);
        log.info("Data conversion done !!");
        return finalResult;
    }
}