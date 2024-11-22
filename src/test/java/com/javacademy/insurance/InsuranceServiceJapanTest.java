package com.javacademy.insurance;

import com.javacademy.insurance.calc.InsuranceCalcJapanService;
import com.javacademy.insurance.contract.ContractNumberGenerator;
import com.javacademy.insurance.contract.InsuranceContract;
import com.javacademy.insurance.contract.InsuranceType;
import com.javacademy.insurance.contract.archive.ContractArchive;
import com.javacademy.insurance.service.InsuranceServiceJapan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;

import static com.javacademy.insurance.contract.ContractStatus.PAID;
import static com.javacademy.insurance.contract.ContractStatus.UNPAID;
import static com.javacademy.insurance.contract.Country.JAPAN;
import static com.javacademy.insurance.contract.Currency.JPY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("japan")
public class InsuranceServiceJapanTest {
    @MockitoBean
    private InsuranceCalcJapanService insuranceCalcJapanService;

    @MockitoBean
    private ContractArchive contractArchive;

    @Autowired
    private InsuranceServiceJapan insuranceServiceJapan;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        insuranceServiceJapan = new InsuranceServiceJapan(contractArchive, insuranceCalcJapanService);
    }

    @Test
    void testIssueInsuranceProposalRobbery() {
        try (var mock = mockStatic(ContractNumberGenerator.class)){
            String clientName = "Иванов Иван Иванович";
            BigDecimal coverageAmount = BigDecimal.valueOf(1_000_000);
            InsuranceType insuranceType = InsuranceType.ROBBERY;
            mock.when(ContractNumberGenerator::generateContractNumber).thenReturn("001");
            when(insuranceCalcJapanService.calculateInsuranceCost(coverageAmount, insuranceType)).thenReturn(BigDecimal.valueOf(20_000));

            InsuranceContract insuranceContract = insuranceServiceJapan.issueInsuranceProposal(coverageAmount, clientName, insuranceType);

            assertEquals("001", insuranceContract.getContractNumber());
            assertEquals(BigDecimal.valueOf(20000), insuranceContract.getInsuranceCost());
            assertEquals(coverageAmount, insuranceContract.getCoverageAmount());
            assertEquals(JPY, insuranceContract.getCurrency());
            assertEquals(clientName, insuranceContract.getClientName());
            assertEquals(JAPAN, insuranceContract.getCountry());
            assertEquals(insuranceType, insuranceContract.getInsuranceType());
            assertEquals(UNPAID, insuranceContract.getContractStatus());
        }
    }

    @Test
    void testIssueInsuranceProposalMedical() {
        try (var mock = mockStatic(ContractNumberGenerator.class)){
            String clientName = "Иванов Иван Иванович";
            BigDecimal coverageAmount = BigDecimal.valueOf(10_000_000);
            InsuranceType insuranceType = InsuranceType.MEDICAL;


            mock.when(ContractNumberGenerator::generateContractNumber).thenReturn("001");
            when(insuranceCalcJapanService.calculateInsuranceCost(coverageAmount, insuranceType)).thenReturn(BigDecimal.valueOf(162_000));

            InsuranceContract insuranceContract = insuranceServiceJapan.issueInsuranceProposal(coverageAmount, clientName, insuranceType);

            assertEquals("001", insuranceContract.getContractNumber());
            assertEquals(BigDecimal.valueOf(162000), insuranceContract.getInsuranceCost());
            assertEquals(coverageAmount, insuranceContract.getCoverageAmount());
            assertEquals(JPY, insuranceContract.getCurrency());
            assertEquals(clientName, insuranceContract.getClientName());
            assertEquals(JAPAN, insuranceContract.getCountry());
            assertEquals(insuranceType, insuranceContract.getInsuranceType());
            assertEquals(UNPAID, insuranceContract.getContractStatus());
        }
    }

    @Test
    void testPayForInsurance() {
        try (var mock = mockStatic(ContractNumberGenerator.class)) {
            String contractNumber = "001";
            String clientName = "Иванов Иван Иванович";
            BigDecimal coverageAmount = BigDecimal.valueOf(10_000_000);
            InsuranceType insuranceType = InsuranceType.MEDICAL;

            mock.when(ContractNumberGenerator::generateContractNumber).thenReturn("001");
            when(insuranceCalcJapanService.calculateInsuranceCost(coverageAmount, insuranceType)).thenReturn(BigDecimal.valueOf(162_000));

            InsuranceContract mockContract = new InsuranceContract(contractNumber, BigDecimal.valueOf(162_000), coverageAmount,
                    JPY, clientName, JAPAN, insuranceType, UNPAID);

            when(contractArchive.getContract("001")).thenReturn(mockContract);

            InsuranceContract paidContract = insuranceServiceJapan.payForInsurance("001");
            assertEquals(contractNumber, paidContract.getContractNumber());
            assertEquals(BigDecimal.valueOf(162000), paidContract.getInsuranceCost());
            assertEquals(coverageAmount, paidContract.getCoverageAmount());
            assertEquals(JPY, paidContract.getCurrency());
            assertEquals(clientName, paidContract.getClientName());
            assertEquals(JAPAN, paidContract.getCountry());
            assertEquals(insuranceType, paidContract.getInsuranceType());
            assertEquals(PAID, paidContract.getContractStatus());

        }
    }
}
