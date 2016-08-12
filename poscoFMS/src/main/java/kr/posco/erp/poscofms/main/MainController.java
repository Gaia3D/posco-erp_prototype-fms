package kr.posco.erp.poscofms.main;

import java.text.DateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.posco.erp.poscofms.main.service.MainService;
import kr.posco.erp.poscofms.main.vo.GirderErrorStatusSummaryVO;
import kr.posco.erp.poscofms.main.vo.GirderInspectionVO;
import kr.posco.erp.poscofms.main.vo.GirderMeasurementVO;
import kr.posco.erp.poscofms.main.vo.GirderErrorStatusVO;


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
	public String indexPage(Locale locale, Model model, String selDate) {
		logger.info("Welcome fms! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		// 측량 날짜를 받아와서 전
		List<String> measurementDates = mainService.getAllMeasurementDates();
		model.addAttribute("measurementDates", measurementDates );
		
		// 측량 날짜 설정
		String selMeasurementDate = measurementDates.get(0);
		if(selDate != null){
			selMeasurementDate = selDate;
		} 
		model.addAttribute("selMeasurementDate",selMeasurementDate);
		
		// 거더별 정보를 가져옴
		GirderErrorStatusSummaryVO girderStatus = mainService.getGirderErrorStatus(selMeasurementDate);
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
	public String detailPage(Locale locale, Model model, String girderId, String selDate, String kind) {
		logger.info("Welcome fms! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		// 측량 날짜를 받아와서 전
		List<String> measurementDates = mainService.getAllMeasurementDates();
		model.addAttribute("measurementDates", measurementDates );
		
		// 측량 날짜 설정
		String selMeasurementDate = measurementDates.get(0);
		if(selDate != null){
			selMeasurementDate = selDate;
		} 
		model.addAttribute("selMeasurementDate",selMeasurementDate);

		// girder id setting
		String selectedGirder = "A1";
		if(girderId != null){
			 selectedGirder = girderId;
		};
		model.addAttribute("selectedGirderId", selectedGirder );
		
		// girder info by id 
		List<GirderErrorStatusVO> girderInfoList = mainService.getGirderInfo();
		model.addAttribute("girderInfoList", girderInfoList );
		
		// info type
		String pageType = "measurement";
		if(kind != null)
			pageType = kind;
		model.addAttribute("kind", pageType);

		// measurement & inspection info of this girder
		GirderMeasurementVO voM = mainService.getGirderMeasurement(selDate, selectedGirder);
		model.addAttribute("measurementInfo", voM);
		GirderInspectionVO voI = mainService.getGirderInspection(selectedGirder);
		model.addAttribute("inspectionInfo", voI);

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
	
	@RequestMapping(value = "/girderMeasurement.posco", method = RequestMethod.GET)
	public @ResponseBody Object getGirderMeasurement(String date, String girderId)
	{
		GirderMeasurementVO vo = mainService.getGirderMeasurement(date, girderId);
		
		if(vo == null)
			vo = new GirderMeasurementVO();
		
		return vo;
	}

	@RequestMapping(value = "/getGirderInspectList.posco", method = RequestMethod.GET)
	public @ResponseBody Object getGirderInspectList(String girderId)
	{
		GirderInspectionVO girderInspection = mainService.getGirderInspection(girderId);
		
		if (girderInspection == null){
			girderInspection = new GirderInspectionVO();
		};
		
		return girderInspection;
	}
	
	@RequestMapping(value = "/getGirderDetailedData.posco", method = RequestMethod.GET)
	public @ResponseBody Object getGirderDetailedData(String date, String girderId)
	{
		GirderMeasurementVO voM = mainService.getGirderMeasurement(date, girderId);
		if(voM == null)
			voM = new GirderMeasurementVO();
		
		GirderInspectionVO voI = mainService.getGirderInspection(girderId);
		if (voI == null)
			voI = new GirderInspectionVO();
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("measurement", voM);
		result.put("inspection", voI);
		
		return result;
	}
	
	@RequestMapping(value = "/HorizontalDeformationHistory.posco", method = RequestMethod.GET)
	public @ResponseBody Object getHorizontalDeformationHistory(String girderId, int positionType)
	{
		List<Integer> history = mainService.getGirderHorizontalDeformationHistory(girderId, positionType);
		List<String> dates = mainService.getAllMeasurementDates();
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("dates", dates);
		result.put("history", history);
		
		return result;
	}
	
	@RequestMapping(value = "/VerticalDeformationHistory.posco", method = RequestMethod.GET)
	public @ResponseBody Object getVerticalDeformationHistory(String girderId, int positionType)
	{
		List<Integer> history = mainService.getGirderVerticalDeformationHistory(girderId, positionType);
		List<String> dates = mainService.getAllMeasurementDates();
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("dates", dates);
		result.put("history", history);
		
		return result;
	}
}
