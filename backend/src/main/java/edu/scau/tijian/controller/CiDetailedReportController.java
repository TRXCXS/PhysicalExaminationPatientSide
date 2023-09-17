package edu.scau.tijian.controller;

import edu.scau.tijian.pojo.CiReport;
import edu.scau.tijian.pojo.Orders;
import edu.scau.tijian.service.CiReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/cidetailedReport")
public class CiDetailedReportController {

	@Autowired
	private CiReportService ciReportService;
	
	@RequestMapping("/listCidetailedReportByOrderId")
	public List<CiReport> listCiReport(@RequestBody Orders orders) {
		return ciReportService.listCiReport(orders.getOrderId());
	}
}
