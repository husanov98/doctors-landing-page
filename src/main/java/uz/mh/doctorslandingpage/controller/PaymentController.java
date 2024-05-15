package uz.mh.doctorslandingpage.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.mh.doctorslandingpage.dto.ChargeRequest;

@RestController
public class PaymentController {

    @Value("${stripe.secret.key}")
    private String secretKey;
    @PostConstruct

    public void  init() {
        Stripe.apiKey = secretKey;
    }

    @PostMapping("/charge")
    public ResponseEntity<String> createCharge(@RequestBody ChargeRequest chargeRequest) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setCurrency("usd")
                    .setAmount(chargeRequest.getAmount()) // Amount in cents
                    .addPaymentMethodType("card")
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return ResponseEntity.ok(paymentIntent.toJson());
        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed");
        }
    }
}
