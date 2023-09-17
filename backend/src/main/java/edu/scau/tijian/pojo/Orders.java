package edu.scau.tijian.pojo;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.Host;

@Getter
@Setter
public class Orders {
    private Integer orderId;
    private String orderDate;
    private String userId;
    private Integer hpId;
    private Integer smId;
    private Integer state;

    private Setmeal setmeal;
    private Hospital hospital;
}
