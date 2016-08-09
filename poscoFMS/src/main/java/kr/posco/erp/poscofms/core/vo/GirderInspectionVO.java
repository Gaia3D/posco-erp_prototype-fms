package kr.posco.erp.poscofms.core.vo;

import java.util.ArrayList;

public class GirderInspectionVO {
	
	private String girderId;
	
	private String girderReplacementDate;
	
	private String personInCharge;
	
	private String replacementReason;
	
	private ArrayList<GirderInspectionItemVO> inspectionRecords;
	
	
	public String getGirderId()
	{
		return this.girderId;
	}
	
	public void setGirderId(String id)
	{
		this.girderId = id;
	}
	
	public String getGirderReplacementDate()
	{
		return this.girderReplacementDate;
	}
	
	public void setGirderReplacementDate(String date)
	{
		this.girderReplacementDate = date;
	}
	
	public String getPersonInCharge()
	{
		return this.personInCharge;
	}
	
	public void setPersonInCharge(String name)
	{
		this.personInCharge = name;
	}
	
	public String getReplacementReason()
	{
		return this.replacementReason;
	}
	
	public void setReplacementReason(String reason)
	{
		this.replacementReason = reason;
	}
	
	public ArrayList<GirderInspectionItemVO> getInspectionRecords()
	{
		return this.inspectionRecords;
	}
	
	public void setInspectionRecords(ArrayList<GirderInspectionItemVO> records)
	{
		this.inspectionRecords = records;
	}
}
