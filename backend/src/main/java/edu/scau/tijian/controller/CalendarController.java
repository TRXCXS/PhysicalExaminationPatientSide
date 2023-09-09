package edu.scau.tijian.controller;


import edu.scau.tijian.dto.CalendarRequestDto;
import edu.scau.tijian.dto.CalendarResponseDto;
import edu.scau.tijian.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

	@Autowired
	private CalendarService calendarService;
	
	//生成预约日历
	@RequestMapping("/getCalendareByYearByMonthByHpId")
	public List<CalendarResponseDto> listAppointmentCalendar(@RequestBody CalendarRequestDto calendarRequestDto) {
		return calendarService.listAppointmentCalendar(calendarRequestDto);
	}
}
