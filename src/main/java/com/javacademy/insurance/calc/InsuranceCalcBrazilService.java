package com.javacademy.insurance.calc;

import com.javacademy.insurance.contract.InsuranceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("brazil")
public class InsuranceCalcBrazilService implements InsuranceCalcService {
    @Value("${insurance.brazil.robberyCoefficient}")
    private BigDecimal robberyCoefficient;

    @Value("${insurance.brazil.medicalCoefficient}")
    private BigDecimal medicalCoefficient;

    @Value("${insurance.brazil.robberyBasePrice}")
    private BigDecimal robberyBasePrice;

    @Value("${insurance.brazil.medicalBasePrice}")
    private BigDecimal medicalBasePrice;

    @Override
    public BigDecimal calculateInsuranceCost(BigDecimal coverageAmount, InsuranceType insuranceType) {
        switch (insuranceType) {
            case MEDICAL:
                return coverageAmount.multiply(medicalCoefficient).add(medicalBasePrice);
            case ROBBERY:
                return coverageAmount.multiply(robberyCoefficient).add(robberyBasePrice);
            default:
                throw new IllegalArgumentException("Неизвестный тип страховки: " + insuranceType);
        }
    }
}
