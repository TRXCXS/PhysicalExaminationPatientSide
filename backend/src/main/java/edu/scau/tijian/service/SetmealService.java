package edu.scau.tijian.service;

import edu.scau.tijian.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    List<Setmeal> getSetmealByType(Integer type);
}
