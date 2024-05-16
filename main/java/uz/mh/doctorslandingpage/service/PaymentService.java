package uz.mh.doctorslandingpage.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class PaymentService {
    public static void main(String[] args) {
        Stripe.apiKey = "rk_test_51KiZYXEWrvgV4siQelHaOSy3eWTLv9raq3pQWvNepVmRzPlhIepaAZw9jKT6DWJvQpkIraeNeQGHOexvuuHTozNK00BSlBW3qP";
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setCurrency("usd")
                .setAmount(1000L) // $10.00
                .addPaymentMethodType("card")
                .build();

        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            System.out.println(paymentIntent);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
