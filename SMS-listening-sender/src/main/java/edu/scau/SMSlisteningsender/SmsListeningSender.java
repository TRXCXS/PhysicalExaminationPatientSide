package edu.scau.SMSlisteningsender;

import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RabbitListener(queues = {"SMS-queue"})
@Component
public class SmsListeningSender {
    @Autowired
    private AsyncClient asyncClient;
    private static Logger LOGGER = LoggerFactory.getLogger(SmsListeningSender.class);

    @RabbitHandler
    public void sendSms(String message) throws ExecutionException, InterruptedException {
        System.out.println("接收到: " + message);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        String[] parts = message.split("\\|");
        String phoneNumber = parts[0];
        String code = parts[1];
        System.out.println(parts[0]);
        System.out.println(parts[1]);
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("阿里云短信测试")
                .templateCode("SMS_154950909")
                .phoneNumbers(phoneNumber)
                .templateParam("{\"code\":\"" + code + "\"}")
                .build();

        CompletableFuture<SendSmsResponse> response = asyncClient.sendSms(sendSmsRequest);
        SendSmsResponse resp = response.get();
        LOGGER.info(new Gson().toJson(resp));
    }
}
