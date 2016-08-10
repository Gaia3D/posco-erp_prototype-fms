package kr.posco.erp.poscofms.main.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.posco.erp.poscofms.main.dao.PoscoFmsDao;
import kr.posco.erp.poscofms.main.vo.GirderErrorStatusSummaryVO;
import kr.posco.erp.poscofms.main.vo.GirderErrorStatusVO;
import kr.posco.erp.poscofms.main.vo.GirderInspectionVO;
import kr.posco.erp.poscofms.main.vo.GirderMeasurementVO;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	PoscoFmsDao poscoFmsDao;

	@Override
	public GirderErrorStatusSummaryVO getGirderErrorStatus(String date) {

		GirderErrorStatusSummaryVO vo = new GirderErrorStatusSummaryVO();
		
		vo.setAllGirderErrorStatus(poscoFmsDao.selectAllGirderErrorStatus(date));
		
		if(vo.getAllGirderErrorStatus() == null)
			return null;
		
		int measurementErrorCount = 0;
		int inspectionErrorCount = 0;
		for(GirderErrorStatusVO girderErrorStatus : vo.getAllGirderErrorStatus())
		{
			if(!girderErrorStatus.getMeasurementPass())
				measurementErrorCount++;
			
			if(!girderErrorStatus.getInspectionPass())
				inspectionErrorCount++;
		}
		
		vo.setMeasurementErrorCount(measurementErrorCount);
		vo.setInspectionErrorCount(inspectionErrorCount);
		
		return vo;
	}

	@Override
	public GirderMeasurementVO getGirderMeasurement(String date, String id) {

		return poscoFmsDao.selectGirderMeasurement(date, id);
	}

	@Override
	public GirderInspectionVO getGirderInspection(String id) {
		
		GirderInspectionVO vo = poscoFmsDao.selectGirderReplacement(id);
		
		if(vo == null || vo.getGirderReplacementDate() == null || vo.getGirderReplacementDate().isEmpty())
			return null;
		
		vo.setInspectionRecords(poscoFmsDao.selectGirderInspectionRecords(vo.getGirderReplacementDate(), id));
		
		return vo;
	}

	@Override
	public ArrayList<Integer> getGirderHorizontalDeformationHistory(String id, int positionType) {

		return poscoFmsDao.selectGirderHorizontalDeformationHistory(id, positionType);
	}

	@Override
	public ArrayList<Integer> getGirderVerticalDeformationHistory(String id, int positionType) {

		return poscoFmsDao.selectGirderVerticalDeformationHistory(id, positionType);
	}
}
