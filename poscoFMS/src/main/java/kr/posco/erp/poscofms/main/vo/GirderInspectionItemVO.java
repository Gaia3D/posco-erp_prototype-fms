package kr.posco.erp.poscofms.main.vo;

public class GirderInspectionItemVO {
	
	private String inspectionDate;
	
	private int inspectionType;
	
	private double crackCrg;
	
	private double crackBg;
	
	private double crackColumn;
	
	private int boltFractures;
	
	private int boltsLoosen;
	
	private int unrecoverableBoltFractures;
	
	private int unrecoverableBoltsLoosen;

	
	public String getInspectionDate()
	{
		return this.inspectionDate;
	}
	
	public void setInspectionDate(String date)
	{
		this.inspectionDate = date;
	}
	
	public int getInspectionType()
	{
		return this.inspectionType;
	}
	
	public void setInspectionType(int type)
	{
		this.inspectionType = type;
	}
	
	public double getCrackCrg()
	{
		return this.crackCrg;
	}
	
	public void setCrackCrg(double crackSize)
	{
		this.crackCrg = crackSize;
	}
	
	public double getCrackBg()
	{
		return this.crackBg;
	}
	
	public void setCrackBg(double crackSize)
	{
		this.crackBg = crackSize;
	}
	
	public double getCrackColumn()
	{
		return this.crackColumn;
	}
	
	public void setCrackColumn(double crackSize)
	{
		this.crackColumn = crackSize;
	}
	
	public int getBoltFractures()
	{
		return this.boltFractures;
	}
	
	public void setBoltFractures(int count)
	{
		this.boltFractures = count;
	}
	
	public int getBoltsLoosen()
	{
		return this.boltsLoosen;
	}
	
	public void setBoltsLoosen(int count)
	{
		this.boltsLoosen = count;
	}
	
	public int getUnrecoverableBoltFractures()
	{
		return this.unrecoverableBoltFractures;
	}
	
	public void setUnrecoverableBoltFractures(int count)
	{
		this.unrecoverableBoltFractures = count;
	}
	
	public int getUnrecoverableBoltsLoosen()
	{
		return this.unrecoverableBoltsLoosen;
	}
	
	public void setUnrecoverableBoltsLoosen(int count)
	{
		this.unrecoverableBoltsLoosen = count;
	}
}
