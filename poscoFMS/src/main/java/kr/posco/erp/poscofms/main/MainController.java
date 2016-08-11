package kr.posco.erp.poscofms.main;

import java.text.DateFormat;

import java.util.Date;
import java.util.List;
import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.posco.erp.poscofms.main.service.MainService;
import kr.posco.erp.poscofms.main.vo.GirderErrorStatusSummaryVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	MainService mainService;
	
	/**
	 * indexPage
	 * 메인 화면으로 진입한다.
	 * @param none
	 * @return indexPage 경로
	 */
	@RequestMapping(value = "/home.posco", method = RequestMethod.GET)
	public String indexPage(Locale locale, Model model) {
		logger.info("Welcome fms! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		// 측량 날짜를 받아와서 전
		List<String> measurementDates = mainService.getAllMeasurementDates();
		model.addAttribute("measurementDates", measurementDates );
		
		// 거더별 정보를 가져옴
		String sampleDate = measurementDates.get(0);
		GirderErrorStatusSummaryVO girderStatus = mainService.getGirderErrorStatus(sampleDate);
		model.addAttribute("measurementErrorCount", girderStatus.getMeasurementErrorCount());
		model.addAttribute("inspectionErrorCount", girderStatus.getInspectionErrorCount());
		model.addAttribute("allGirderStatus", girderStatus.getAllGirderErrorStatus());
		
		return "main/index";
	}
	
	/**
	 * detailPage
	 * 상세정보 화면으로 진입한다.
	 * @param none
	 * @return detailPage 경로
	 */
	@RequestMapping(value = "/detail.posco", method = RequestMethod.GET)
	public String detailPage(Locale locale, Model model) {
		logger.info("Welcome fms! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "main/detail";
	}
	
	/**
	 * reportPage
	 * 리포트 화면으로 진입한다.
	 * @param none
	 * @return reportPage 경로
	 */
	@RequestMapping(value = "/report.posco", method = RequestMethod.GET)
	public String reportPage(Locale locale, Model model) {
		logger.info("Welcome fms! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "main/report";
	}
	
	/**
	 * viewPage
	 * 권한이 있는 사용자에게 해당 페이지로 포워드해준다.
	 * @param nextSubPage 전체 페이지 프레임 안에서 content 영역만을 리프레시할 수 있도록 해당 부분의 페이지만 호출하는 경로를 포워딩해준다.
	 * @param nextPageName 부분 페이지가 아닌 전체 페이지를 로드하는 경우 이 항목에 값을 전달하여 해당 페이지로 포워드해준다.
	 * @return 목적에 맞는 해당 페이지 경로
	 */
	@RequestMapping(value = "/viewPage.posco")
	public String viewPage(Locale locale, Model model, String nextSubPageName, String nextPageName) {
		logger.debug("Welcome sdmc Main! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		

		if(nextPageName == null){
			model.addAttribute("nextSubPageName", nextSubPageName );
			nextPageName = "main/main";
		}
		
		model.addAttribute("serverTime", formattedDate );
		
		// TODO right now 0 : extract input parameters from client request
		//dataProcessService.processRequest(input);
		
		return nextPageName;
	}
	
}
