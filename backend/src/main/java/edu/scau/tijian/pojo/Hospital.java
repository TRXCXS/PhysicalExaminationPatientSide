package edu.scau.tijian.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hospital {
    private Integer hpId;
    private String name;
    private String picture;
    private String telephone;
    private String address;
    private String businessHours;
    private String deadline;
    private String rule;
    private Integer state;
}
