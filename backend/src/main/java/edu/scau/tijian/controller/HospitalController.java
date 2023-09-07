package edu.scau.tijian.controller;

import edu.scau.tijian.pojo.Hospital;
import edu.scau.tijian.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @RequestMapping("/getHospitalsByState")
    public List<Hospital> getHospitalByState(@RequestBody Hospital hospital){
        return hospitalService.getHospitalsByState(hospital.getState());
    }
}
