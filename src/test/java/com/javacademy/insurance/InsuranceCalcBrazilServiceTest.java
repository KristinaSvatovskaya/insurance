package com.javacademy.insurance;

import com.javacademy.insurance.calc.InsuranceCalcBrazilService;
import com.javacademy.insurance.calc.InsuranceCalcJapanService;
import com.javacademy.insurance.contract.InsuranceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("brazil")
public class InsuranceCalcBrazilServiceTest {
    @Autowired
    private InsuranceCalcBrazilService insuranceCalcBrazilService;

    @Test
    void testCalculateInsuranceCostRobbery() {
        BigDecimal coverageAmount = new BigDecimal("50000");
        BigDecimal expectedInsuranceCost = new BigDecimal("2800.00");

        BigDecimal insuranceCost = insuranceCalcBrazilService.calculateInsuranceCost(coverageAmount, InsuranceType.ROBBERY);

        assertEquals(expectedInsuranceCost, insuranceCost);
    }

    @Test
    void testCalculateInsuranceCostMedical() {
        BigDecimal coverageAmount = new BigDecimal("200000");
        BigDecimal expectedInsuranceCost = new BigDecimal("6800.00");

        BigDecimal insuranceCost = insuranceCalcBrazilService.calculateInsuranceCost(coverageAmount, InsuranceType.MEDICAL);

        assertEquals(expectedInsuranceCost, insuranceCost);
    }

}
