package com.javacademy.insurance.contract.archive;

import com.javacademy.insurance.contract.InsuranceContract;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Архив
 */
@Component
public class ContractArchive {
    private Map<String, InsuranceContract> contracts = new HashMap<>();

    public void addContract(InsuranceContract contract) {
        contracts.put(contract.getContractNumber(), contract);
    }

    public InsuranceContract getContract(String contractNumber) {
        return contracts.get(contractNumber);
    }


}
