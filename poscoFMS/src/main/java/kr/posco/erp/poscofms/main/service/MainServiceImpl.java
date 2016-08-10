package kr.posco.erp.poscofms.main.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.posco.erp.poscofms.main.dao.PoscoFmsDao;
import kr.posco.erp.poscofms.main.vo.GirderErrorStatusVO;
import kr.posco.erp.poscofms.main.vo.GirderInspectionVO;
import kr.posco.erp.poscofms.main.vo.GirderMeasurementVO;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	PoscoFmsDao poscoFmsDao;

	@Override
	public ArrayList<GirderErrorStatusVO> getGirderErrorStatus(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GirderMeasurementVO getGirderMeasurement(String date, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GirderInspectionVO getGirderInspection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> getGirderHorizontalDeformationHistory(String id, int positionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> getGirderVerticalDeformationHistory(String id, int positionType) {
		// TODO Auto-generated method stub
		return null;
	}
}
