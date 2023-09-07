package edu.scau.tijian.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Setmeal {
    private Integer smId;
    private String name;
    private Integer type;
    private Integer price;

    private List<Setmealdetailed> setmealdetailedList;
}
