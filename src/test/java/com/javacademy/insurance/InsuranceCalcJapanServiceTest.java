package com.javacademy.insurance;

import com.javacademy.insurance.calc.InsuranceCalcJapanService;
import com.javacademy.insurance.contract.InsuranceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("japan")
public class InsuranceCalcJapanServiceTest {
    @Autowired
    private InsuranceCalcJapanService insuranceCalcJapanService;

    @Test
    void testCalculateInsuranceCostRobbery() {
        BigDecimal coverageAmount = new BigDecimal("1000000");
        BigDecimal expectedInsuranceCost = new BigDecimal("20000.00");

        BigDecimal insuranceCost = insuranceCalcJapanService.calculateInsuranceCost(coverageAmount, InsuranceType.ROBBERY);

        assertEquals(expectedInsuranceCost, insuranceCost);
    }

    @Test
    void testCalculateInsuranceCostMedical() {
        BigDecimal coverageAmount = new BigDecimal("10000000");
        BigDecimal expectedInsuranceCost = new BigDecimal("162000.000");

        BigDecimal insuranceCost = insuranceCalcJapanService.calculateInsuranceCost(coverageAmount, InsuranceType.MEDICAL);

        assertEquals(expectedInsuranceCost, insuranceCost);
    }
}
