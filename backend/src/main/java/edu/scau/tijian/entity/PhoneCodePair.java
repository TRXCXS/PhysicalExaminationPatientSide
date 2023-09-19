package edu.scau.tijian.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@RedisHash(value = "PhoneCodePair", timeToLive = 3000L)
@Data
@Builder
public class PhoneCodePair implements Serializable {
    @Id
    private String phoneNumber;
    private Integer code;
}
