package com.javacademy.insurance.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Страховой договор
 */
@Getter
@Setter
@AllArgsConstructor
public class InsuranceContract {
    private String contractNumber;
    private BigDecimal insuranceCost;
    private BigDecimal coverageAmount;
    private Currency currency;
    private String clientName;
    private Country country;
    private InsuranceType insuranceType;
    private ContractStatus contractStatus;

}
