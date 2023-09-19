package edu.scau.tijian.service;

import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import edu.scau.tijian.entity.PhoneCodePair;
import edu.scau.tijian.repository.PhoneCodePairRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AliyunSmsService {
    @Autowired
    private AsyncClient asyncClient;
    @Autowired
    private PhoneCodePairRepository phoneCodePairRepository;
    private static Logger LOGGER = LoggerFactory.getLogger(AliyunSmsService.class);

    public String sendAndSaveSmsCode(String phoneNumber) throws ExecutionException, InterruptedException {
        // Generate a 6-digit random number
        Integer code = 100000 + new Random(new Random().nextInt()).nextInt(900000);
        phoneCodePairRepository.save(PhoneCodePair.builder()
                        .phoneNumber(phoneNumber)
                        .code(code)
                        .build());

        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("阿里云短信测试")
                .templateCode("SMS_154950909")
                .phoneNumbers(phoneNumber)
                .templateParam("{\"code\":\"" + code + "\"}")
                .build();

        CompletableFuture<SendSmsResponse> response = asyncClient.sendSms(sendSmsRequest);
        SendSmsResponse resp = response.get();
        LOGGER.info(new Gson().toJson(resp));
        return resp.getBody().getMessage();
    }
}
