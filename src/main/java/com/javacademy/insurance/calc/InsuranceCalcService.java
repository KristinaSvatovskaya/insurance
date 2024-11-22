package com.javacademy.insurance.calc;

import com.javacademy.insurance.contract.InsuranceType;

import java.math.BigDecimal;

public interface InsuranceCalcService {
    BigDecimal calculateInsuranceCost(BigDecimal coverageAmount, InsuranceType insuranceType);
}
