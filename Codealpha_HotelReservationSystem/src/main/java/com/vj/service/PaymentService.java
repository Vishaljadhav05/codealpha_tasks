package com.vj.service;

import com.vj.dto.PaymentRequest;
import com.vj.model.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

/** Simulated payment gateway. CARD payments fail if the card number is missing or ends with 0. */
@Service
public class PaymentService {

    public Payment process(BigDecimal amount, PaymentRequest req) {
        Payment p = new Payment();
        p.setAmount(amount);
        p.setMethod(req.method());
        p.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        boolean declined = req.method() == PaymentMethod.CARD
                && (req.cardNumber() == null || req.cardNumber().isBlank() || req.cardNumber().endsWith("0"));
        p.setStatus(declined ? PaymentStatus.FAILED : PaymentStatus.SUCCESS);
        return p;
    }
}
