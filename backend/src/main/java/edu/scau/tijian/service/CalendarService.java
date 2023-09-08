package edu.scau.tijian.service;



import edu.scau.tijian.dto.CalendarRequestDto;
import edu.scau.tijian.dto.CalendarResponseDto;

import java.util.List;

public interface CalendarService {

	//生成预约日历
	public List<CalendarResponseDto> listAppointmentCalendar(CalendarRequestDto calendarRequestDto);
}
