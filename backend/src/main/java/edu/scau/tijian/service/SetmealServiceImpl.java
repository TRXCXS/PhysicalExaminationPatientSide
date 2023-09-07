package edu.scau.tijian.service;

import edu.scau.tijian.mapper.SetmealMapper;
import edu.scau.tijian.pojo.Setmeal;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SetmealServiceImpl implements SetmealService {
    private final SetmealMapper setmealMapper;


    @Override
    public List<Setmeal> getSetmealByType(Integer type) {
        return setmealMapper.getSetmealByType(type);

    }
}
