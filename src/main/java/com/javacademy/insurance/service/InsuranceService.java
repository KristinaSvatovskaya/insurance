package com.javacademy.insurance.service;

import com.javacademy.insurance.contract.InsuranceContract;
import com.javacademy.insurance.contract.InsuranceType;

import java.math.BigDecimal;

public interface InsuranceService {
    InsuranceContract issueInsuranceProposal(BigDecimal coverageAmount, String clientName, InsuranceType insuranceType);

    InsuranceContract payForInsurance(String contractNumber) throws IllegalArgumentException;
}
