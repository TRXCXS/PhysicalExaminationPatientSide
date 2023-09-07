package edu.scau.tijian.service;

import edu.scau.tijian.pojo.Hospital;

import java.util.List;

public interface HospitalService {
    List<Hospital> getHospitalsByState(Integer state);
}
