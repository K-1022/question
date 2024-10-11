package com.example.nagoyameshi.service;

 import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.form.UserRegisterForm;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionRetrieveParams;

import jakarta.servlet.http.HttpServletRequest;
 
 @Service
public class StripeService {
	 @Value("${stripe.api-key}")
     private String stripeApiKey;
	 
     private final SalesService salesService;
     
     public StripeService(SalesService salesService) {
         this.salesService = salesService;
     }  
	 
     // セッションを作成し、Stripeに必要な情報を返す
     public String createStripeSession(String Paid, UserRegisterForm userRegisterForm, HttpServletRequest httpServletRequest) {
         Stripe.apiKey = stripeApiKey;
         String requestUrl = new String(httpServletRequest.getRequestURL());
         SessionCreateParams params =
             SessionCreateParams.builder()
                 .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                 .addLineItem(
                     SessionCreateParams.LineItem.builder()
                         .setPriceData(
                             SessionCreateParams.LineItem.PriceData.builder()   
                                 .setProductData(
                                     SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                         .setName(Paid)
                                         .build())
                                 .setUnitAmount(300L)
                                 .setCurrency("jpy")                                
                                 .build())
                         .setQuantity(1L)
                         .build())
                 .setMode(SessionCreateParams.Mode.PAYMENT)
                 .setSuccessUrl(requestUrl.replaceAll("/user", "") + "/users?reserved")
                 .setCancelUrl(requestUrl.replace("/user", ""))
                 .setPaymentIntentData(
                     SessionCreateParams.PaymentIntentData.builder()
                         .putMetadata("userId", userRegisterForm.getId().toString())
                         .putMetadata("name", userRegisterForm.getName())
                         .putMetadata("address", userRegisterForm.getAddress())
                         .putMetadata("postalCode", userRegisterForm.getPostalCode())
                         .putMetadata("email", userRegisterForm.getEmail())
                         .putMetadata("phoneNumber", userRegisterForm.getPhoneNumber())
                         .putMetadata("furigana", userRegisterForm.getFurigana())
                         .putMetadata("password", userRegisterForm.getPassword())
                         .putMetadata("consent", userRegisterForm.getConsent())
                         .build())
                 .build();
         try {
             Session session = Session.create(params);
             return session.getId();
         } catch (StripeException e) {
             e.printStackTrace();
             return "";
         }
     } 
     
     public void processSessionCompleted(Event event) {
         Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject();
         optionalStripeObject.ifPresentOrElse(stripeObject -> {
             Session session = (Session)stripeObject;
             SessionRetrieveParams params = SessionRetrieveParams.builder().addExpand("payment_intent").build();
 
             try {
                 session = Session.retrieve(session.getId(), params, null);
                 Map<String, String> paymentIntentObject = session.getPaymentIntentObject().getMetadata();
                 salesService.create(paymentIntentObject);
             } catch (StripeException e) {
                 e.printStackTrace();
             }
             System.out.println("会員の登録処理が成功しました。");
             System.out.println("Stripe API Version: " + event.getApiVersion());
             System.out.println("stripe-java Version: " + Stripe.VERSION);
         },
         () -> {
             System.out.println("会員の登録処理が失敗しました。");
             System.out.println("Stripe API Version: " + event.getApiVersion());
             System.out.println("stripe-java Version: " + Stripe.VERSION);
         });
     }
}
     