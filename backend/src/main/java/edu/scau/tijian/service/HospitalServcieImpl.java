package edu.scau.tijian.service;

import edu.scau.tijian.mapper.HospitalMapper;
import edu.scau.tijian.pojo.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServcieImpl implements HospitalService {
    @Autowired
    private final HospitalMapper hospitalMapper;

    @Override
    public List<Hospital> getHospitalsByState(Integer state) {
        return hospitalMapper.getHospitalsByState(state);
    }
}
