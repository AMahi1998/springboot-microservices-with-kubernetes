package com.techie.microservices.notification.service;

import com.techie.microservices.notification.order.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent){
        log.info("Got Message from order-placed topic: {}", orderPlacedEvent);
        MimeMessagePreparator messagePreparator=
                mimeMessage -> {
                    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
                    messageHelper.setFrom("springshop@email.com");
                    messageHelper.setTo(orderPlacedEvent.getEmail());
                    messageHelper.setSubject("Your Order with OrderNumber %s is placed successfully" + orderPlacedEvent.getOrderNumber());
                    messageHelper.setText("""
                            Hi,
                            Your Order with OrderNumber %s is placed successfully.
                            
                            Best Regards,
                            Spring Shop
                            """.formatted(orderPlacedEvent.getOrderNumber()), /*isHtml*/false);
                };
        try{
            javaMailSender.send(messagePreparator);
            log.info("Email sent successfully to {}", orderPlacedEvent.getEmail());
        }catch (Exception e){
            log.error("Error while sending email to {}: {}", orderPlacedEvent.getEmail(), e.getMessage());
            throw new RuntimeException("Exception occurred when sending mail to springshop@email.com",e);

        }


    }
}
