package kr.posco.erp.poscofms.main.service;

import java.util.List;

import kr.posco.erp.poscofms.main.vo.GirderErrorStatusSummaryVO;
import kr.posco.erp.poscofms.main.vo.GirderErrorStatusVO;
import kr.posco.erp.poscofms.main.vo.GirderInspectionVO;
import kr.posco.erp.poscofms.main.vo.GirderMeasurementVO;

public interface MainService {
	
	public List<String> getAllMeasurementDates();
	
	public GirderErrorStatusSummaryVO getGirderErrorStatus(String date);
	
	public GirderMeasurementVO getGirderMeasurement(String date, String id);
	
	public GirderInspectionVO getGirderInspection(String id);
	
	public List<Integer> getGirderHorizontalDeformationHistory(String id, int positionType);
	
	public List<Integer> getGirderVerticalDeformationHistory(String id, int positionType);
	
	public List<GirderErrorStatusVO> getGirderInfo();

}
