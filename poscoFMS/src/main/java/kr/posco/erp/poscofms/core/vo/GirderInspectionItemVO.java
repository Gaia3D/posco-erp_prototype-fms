package kr.posco.erp.poscofms.core.vo;

public class GirderInspectionItemVO {
	
	private int inspectionType;
	
	private float crackCrg;
	
	private float crackBg;
	
	private float crackColumn;
	
	private int boltFractures;
	
	private int boltsLoosen;
	
	private int unrecoverableBoltFractures;
	
	private int unrecoverableBoltsLoosen;

	
	public int getInspectionType()
	{
		return this.inspectionType;
	}
	
	public void setInspectionType(int type)
	{
		this.inspectionType = type;
	}
	
	public float getCrackCrg()
	{
		return this.crackCrg;
	}
	
	public void setCrackCrg(float crackSize)
	{
		this.crackCrg = crackSize;
	}
	
	public float getCrackBg()
	{
		return this.crackBg;
	}
	
	public void setCrackBg(float crackSize)
	{
		this.crackBg = crackSize;
	}
	
	public float getCrackColumn()
	{
		return this.crackColumn;
	}
	
	public void setCrackColumn(float crackSize)
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
