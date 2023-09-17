package edu.scau.tijian.service;

import edu.scau.tijian.mapper.OverallResultMapper;
import edu.scau.tijian.pojo.OverallResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverallResultServiceImpl implements OverallResultService{

	@Autowired
	private OverallResultMapper overallResultMapper;
	
	@Override
	public List<OverallResult> listOverallResultByOrderId(Integer orderId) {
		return overallResultMapper.listOverallResultByOrderId(orderId);
	}
}
