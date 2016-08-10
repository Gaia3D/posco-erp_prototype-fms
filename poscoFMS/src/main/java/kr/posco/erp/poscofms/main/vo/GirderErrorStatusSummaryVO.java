package kr.posco.erp.poscofms.main.vo;

import java.util.List;

public class GirderErrorStatusSummaryVO {
	
	private int measurementErrorCount;
	
	private int inspectionErrorCount;
	
	private List<GirderErrorStatusVO> allGirderErrorStatus;
	
	
	public int getMeasurementErrorCount()
	{
		return this.measurementErrorCount;
	}
	
	public void setMeasurementErrorCount(int count)
	{
		this.measurementErrorCount = count;
	}

	public int getInspectionErrorCount()
	{
		return this.inspectionErrorCount;
	}
	
	public void setInspectionErrorCount(int count)
	{
		this.inspectionErrorCount = count;
	}
	
	public List<GirderErrorStatusVO> getAllGirderErrorStatus()
	{
		return this.allGirderErrorStatus;
	}
	
	public void setAllGirderErrorStatus(List<GirderErrorStatusVO> girderErrorStatus)
	{
		this.allGirderErrorStatus = girderErrorStatus;
	}
}
