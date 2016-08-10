package kr.posco.erp.poscofms.main.vo;

import java.util.ArrayList;

public class GirderErrorStatusSummaryVO {
	
	private int measurementErrorCount;
	
	private int inspectionErrorCount;
	
	private ArrayList<GirderErrorStatusVO> allGirderErrorStatus;
	
	
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
	
	public ArrayList<GirderErrorStatusVO> getAllGirderErrorStatus()
	{
		return this.allGirderErrorStatus;
	}
	
	public void setAllGirderErrorStatus(ArrayList<GirderErrorStatusVO> girderErrorStatus)
	{
		this.allGirderErrorStatus = girderErrorStatus;
	}
}
