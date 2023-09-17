package edu.scau.tijian.service;

import edu.scau.tijian.pojo.CiReport;
import java.util.List;

public interface CiReportService {
	List<CiReport> listCiReport(Integer orderId);
}
