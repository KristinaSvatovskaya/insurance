package com.javacademy.insurance.service;

import com.javacademy.insurance.calc.InsuranceCalcService;
import com.javacademy.insurance.contract.ContractNumberGenerator;
import com.javacademy.insurance.contract.InsuranceContract;
import com.javacademy.insurance.contract.InsuranceType;
import com.javacademy.insurance.contract.archive.ContractArchive;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.javacademy.insurance.contract.ContractStatus.PAID;
import static com.javacademy.insurance.contract.ContractStatus.UNPAID;
import static com.javacademy.insurance.contract.Country.BRAZIL;
import static com.javacademy.insurance.contract.Currency.BRL;

@Component
@Profile("brazil")
public class InsuranceServiceBrazil implements InsuranceService {
    private final ContractArchive contractArchive;
    private final InsuranceCalcService insuranceCalcService;

//    @Value("${insurance.brazil.currency}")
//    private Currency currency;
//
//    @Value("${insurance.brazil.country}")
//    private Country country;

    public InsuranceServiceBrazil(ContractArchive contractArchive, InsuranceCalcService insuranceCalcService) {
        this.contractArchive = contractArchive;
        this.insuranceCalcService = insuranceCalcService;
    }

    @Override
    public InsuranceContract issueInsuranceProposal(BigDecimal coverageAmount, String clientName,
                                                    InsuranceType insuranceType) {
        String contractNumber = ContractNumberGenerator.generateContractNumber();
        BigDecimal insuranceCost = insuranceCalcService.calculateInsuranceCost(coverageAmount, insuranceType);
        InsuranceContract insuranceContract = new InsuranceContract(contractNumber, insuranceCost, coverageAmount,
                BRL, clientName, BRAZIL, insuranceType, UNPAID);
        contractArchive.addContract(insuranceContract);
        return insuranceContract;
    }

    @Override
    public InsuranceContract payForInsurance(String contractNumber) throws IllegalArgumentException {
        InsuranceContract contract = contractArchive.getContract(contractNumber);

        if (contract == null) {
            throw new IllegalArgumentException("Договор с таким номером не существует.");
        }
        contract.setContractStatus(PAID);
        return contract;
    }
}
