package com.javacademy.insurance.contract;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Генератор номеров договоров
 */
public class ContractNumberGenerator {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    /**
     * Генерирует новый уникальный номер договора.
     * Формат номера: INS-YYYYMMDD-XXXX, где XXXX — уникальный счетчик.
     * @return Уникальный номер договора.
     */
    public static String generateContractNumber() {
        String datePart = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String counterPart = String.format("%04d", COUNTER.getAndIncrement());
        return "INS-" + datePart + "-" + counterPart;
    }
}
