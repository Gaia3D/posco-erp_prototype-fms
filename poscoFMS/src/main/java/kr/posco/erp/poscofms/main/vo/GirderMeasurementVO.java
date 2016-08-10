package kr.posco.erp.poscofms.main.vo;

public class GirderMeasurementVO {
	
	private String girderId;
	
	private String measurementDate;
	
	private int horizontalDeformationAtStart;
	
	private int horizontalDeformationAtMid;
	
	private int horizontalDeformationAtLast;
	
	private int verticalDeformationAtStart;
	
	private int verticalDeformationAtMid;
	
	private int verticalDeformationAtLast;
	
	
	public String getGirderId()
	{
		return this.girderId;
	}
	
	public void setGirderId(String id)
	{
		this.girderId = id;
	}
	
	public String getMeasurementDate()
	{
		return this.measurementDate;
	}
	
	public void setMeasurementDate(String date)
	{
		this.measurementDate = date;
	}
	
	public int getHorizontalDeformationAtStart()
	{
		return this.horizontalDeformationAtStart;
	}
	
	public void setHorizontalDeformationAtStart(int deformation)
	{
		this.horizontalDeformationAtStart = deformation;
	}
	
	public int getHorizontalDeformationAtMid()
	{
		return this.horizontalDeformationAtMid;
	}
	
	public void setHorizontalDeformationAtMid(int deformation)
	{
		this.horizontalDeformationAtMid = deformation;
	}
	
	public int getHorizontalDeformationAtLast()
	{
		return this.horizontalDeformationAtLast;
	}
	
	public void setHorizontalDeformationAtLast(int deformation)
	{
		this.horizontalDeformationAtLast = deformation;
	}
	
	public int getVerticalDeformationAtStart()
	{
		return this.verticalDeformationAtStart;
	}
	
	public void setVerticalDeformationAtStart(int deformation)
	{
		this.verticalDeformationAtStart = deformation;
	}
	
	public int getVerticalDeformationAtMid()
	{
		return this.verticalDeformationAtMid;
	}
	
	public void setVerticalDeformationAtMid(int deformation)
	{
		this.verticalDeformationAtMid = deformation;
	}
	
	public int getVerticalDeformationAtLast()
	{
		return this.verticalDeformationAtLast;
	}
	
	public void setVerticalDeformationAtLast(int deformation)
	{
		this.verticalDeformationAtLast = deformation;
	}
}
