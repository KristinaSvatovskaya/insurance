package com.javacademy.insurance.calc;

import com.javacademy.insurance.contract.InsuranceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("japan")
public class InsuranceCalcJapanService implements InsuranceCalcService {
    @Value("${insurance.japan.robberyCoefficient}")
    private BigDecimal robberyCoefficient;

    @Value("${insurance.japan.medicalCoefficient}")
    private BigDecimal medicalCoefficient;

    @Value("${insurance.japan.robberyBasePrice}")
    private BigDecimal robberyBasePrice;

    @Value("${insurance.japan.medicalBasePrice}")
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
