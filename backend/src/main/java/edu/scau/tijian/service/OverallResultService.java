package edu.scau.tijian.service;

import edu.scau.tijian.pojo.OverallResult;
import java.util.List;


public interface OverallResultService {

	public List<OverallResult> listOverallResultByOrderId(Integer orderId);
}
