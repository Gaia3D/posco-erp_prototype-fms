package kr.posco.erp.poscofms.main.service;

import java.util.ArrayList;

import kr.posco.erp.poscofms.main.vo.GirderErrorStatusVO;
import kr.posco.erp.poscofms.main.vo.GirderInspectionVO;
import kr.posco.erp.poscofms.main.vo.GirderMeasurementVO;

public interface MainService {
	
	public ArrayList<GirderErrorStatusVO> getGirderErrorStatus(String date);
	
	public GirderMeasurementVO getGirderMeasurement(String date, String id);
	
	public GirderInspectionVO getGirderInspection(String id);
	
	public ArrayList<Integer> getGirderHorizontalDeformationHistory(String id, int positionType);
	
	public ArrayList<Integer> getGirderVerticalDeformationHistory(String id, int positionType);

}
