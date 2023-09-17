package edu.scau.tijian.service;

import edu.scau.tijian.mapper.CiDetailedReportMapper;
import edu.scau.tijian.mapper.CiReportMapper;
import edu.scau.tijian.pojo.CiDetailedReport;
import edu.scau.tijian.pojo.CiReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CiReportServiceImpl implements CiReportService{

	@Autowired
	private CiReportMapper ciReportMapper;
	@Autowired
	private CiDetailedReportMapper ciDetailedReportMapper;
	
	@Override
	public List<CiReport> listCiReport(Integer orderId) {
		//先查询CiReport。获取报告检查项
		List<CiReport> crList = ciReportMapper.listCiReport(orderId);
		
		//遍历所有检查项，在依次查询检查项明细
		for(CiReport cr : crList) {
			CiDetailedReport param = new CiDetailedReport();
			param.setOrderId(orderId);
			param.setCiId(cr.getCiId());
			List<CiDetailedReport> list = ciDetailedReportMapper.listCiDetailedReportByOrderIdByCiId(param);
			cr.setDetailedreportList(list);
		}
		return crList;
	}
}
