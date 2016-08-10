package kr.posco.erp.poscofms.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.posco.erp.poscofms.core.annotation.MybatisForPoscofms;
import kr.posco.erp.poscofms.main.vo.GirderErrorStatusVO;
import kr.posco.erp.poscofms.main.vo.GirderInspectionItemVO;
import kr.posco.erp.poscofms.main.vo.GirderInspectionVO;
import kr.posco.erp.poscofms.main.vo.GirderMeasurementVO;

@MybatisForPoscofms
public interface PoscoFmsDao {
	
	// for layout(index) page
	public List<String> selectTotalMeasurementDates();
	public List<GirderErrorStatusVO> selectAllGirderErrorStatus(String date);
	
	// for detailed measurement page
	public GirderMeasurementVO selectGirderMeasurement(@Param("date") String date, @Param("id") String id);
	
	// for detailed inspection page
	public GirderInspectionVO selectGirderReplacement(String id);
	public List<GirderInspectionItemVO> selectGirderInspectionRecords(@Param("date") String date, @Param("id") String id);
	
	// for time-series graph
	public List<Integer> selectGirderHorizontalDeformationHistory(@Param("id") String id, @Param("positionType") int positionType);
	public List<Integer> selectGirderVerticalDeformationHistory(@Param("id") String id, @Param("positionType") int positionType);
	
}
