package kr.posco.erp.poscofms.main.vo;

public class GirderErrorStatusVO {
	
	private String girderId;
	
	private int positionX;
	
	private int positionY;
	
	private boolean measurementPass;
	
	private boolean inspectionPass;
	
	
	public String getGirderId()
	{
		return this.girderId;
	}
	
	public void setGirderId(String id)
	{
		this.girderId = id;
	}

	public int getPositionX()
	{
		return this.positionX;
	}
	
	public void setPositionX(int x)
	{
		this.positionX = x;
	}
	
	public int getPositionY()
	{
		return this.positionY;
	}
	
	public void setPositionY(int y)
	{
		this.positionY = y;
	}
	
	public boolean getMeasurementPass()
	{
		return this.measurementPass;
	}
	
	public void setMeasurementPass(boolean bPass)
	{
		this.measurementPass = bPass;
	}
	
	public boolean getInspectionPass()
	{
		return this.inspectionPass;
	}
	
	public void setInspectionPass(boolean bPass)
	{
		this.inspectionPass = bPass;
	}
}
