package kr.posco.erp.poscofms.core.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.posco.erp.poscofms.core.annotation.MybatisForPoscofms;
import kr.posco.erp.poscofms.main.vo.GirderErrorStatusVO;
import kr.posco.erp.poscofms.main.vo.GirderInspectionItemVO;
import kr.posco.erp.poscofms.main.vo.GirderInspectionVO;
import kr.posco.erp.poscofms.main.vo.GirderMeasurementVO;

@MybatisForPoscofms
public interface PoscoFmsDao {
	
	// for layout(index) page
	public ArrayList<String> selectTotalMeasurementDates();
	public ArrayList<GirderErrorStatusVO> selectAllGirderErrorStatus(String date);
	
	// for detailed measurement page
	public GirderMeasurementVO selectGirderMeasurement(@Param("date") String date, @Param("id") String id);
	
	// for detailed inspection page
	public GirderInspectionVO selectGirderReplacement(String id);
	public ArrayList<GirderInspectionItemVO> selectGirderInspectionRecords(@Param("date") String date, @Param("id") String id);
	
	// for time-series graph
	public ArrayList<Integer> selectGirderHorizontalDeformationHistory(@Param("id") String id, @Param("positionType") int positionType);
	public ArrayList<Integer> selectGirderVerticalDeformationHistory(@Param("id") String id, @Param("positionType") int positionType);
	
}
