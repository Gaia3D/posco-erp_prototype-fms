package kr.posco.erp.poscofms.main.vo;

public class GirderMeasurementVO {
	
	// basic information
	private String girderId;
	
	public String getGirderId()
	{
		return this.girderId;
	}
	
	public void setGirderId(String id)
	{
		this.girderId = id;
	}
	
	private String measurementDate;
	
	public String getMeasurementDate()
	{
		return this.measurementDate;
	}
	
	public void setMeasurementDate(String date)
	{
		this.measurementDate = date;
	}
	
	
	// information about horizontal deformation
	private int horizontalDeformationAtStart;
	
	public int getHorizontalDeformationAtStart()
	{
		return this.horizontalDeformationAtStart;
	}
	
	public void setHorizontalDeformationAtStart(int deformation)
	{
		this.horizontalDeformationAtStart = deformation;
	}
	
	private int horizontalDeformationAtMid;
	
	public int getHorizontalDeformationAtMid()
	{
		return this.horizontalDeformationAtMid;
	}
	
	public void setHorizontalDeformationAtMid(int deformation)
	{
		this.horizontalDeformationAtMid = deformation;
	}
	
	private int horizontalDeformationAtLast;
	
	public int getHorizontalDeformationAtLast()
	{
		return this.horizontalDeformationAtLast;
	}
	
	public void setHorizontalDeformationAtLast(int deformation)
	{
		this.horizontalDeformationAtLast = deformation;
	}
	
	private int horizontalDeformationAtCounterGirderStart;
	
	public int getHorizontalDeformationAtCounterGirderStart()
	{
		return this.horizontalDeformationAtCounterGirderStart;
	}
	
	public void setHorizontalDeformationAtCounterGirderStart(int deformation)
	{
		this.horizontalDeformationAtCounterGirderStart = deformation;
	}
	
	private int horizontalDeformationAtCounterGirderMid;
	
	public int getHorizontalDeformationAtCounterGirderMid()
	{
		return this.horizontalDeformationAtCounterGirderMid;
	}
	
	public void setHorizontalDeformationAtCounterGirderMid(int deformation)
	{
		this.horizontalDeformationAtCounterGirderMid = deformation;
	}
	
	private int horizontalDeformationAtCounterGirderLast;
	
	public int getHorizontalDeformationAtCounterGirderLast()
	{
		return this.horizontalDeformationAtCounterGirderLast;
	}
	
	public void setHorizontalDeformationAtCounterGirderLast(int deformation)
	{
		this.horizontalDeformationAtCounterGirderLast = deformation;
	}
	
	private double gapLengthAtStart;
	
	public double getGapLengthAtStart()
	{
		return this.gapLengthAtStart;
	}
	
	public void setGapLengthAtStart(double length)
	{
		this.gapLengthAtStart = length;
	}
	
	private double gapLengthAtMid;
	
	public double getGapLengthAtMid()
	{
		return this.gapLengthAtMid;
	}
	
	public void setGapLengthAtMid(double length)
	{
		this.gapLengthAtMid = length;
	}
	
	private double gapLengthAtLast;
	
	public double getGapLengthAtLast()
	{
		return this.gapLengthAtLast;
	}
	
	public void setGapLengthAtLast(double length)
	{
		this.gapLengthAtLast = length;
	}
	
	
	// information about vertical deformation
	private int verticalDeformationAtStart;
	
	public int getVerticalDeformationAtStart()
	{
		return this.verticalDeformationAtStart;
	}
	
	public void setVerticalDeformationAtStart(int deformation)
	{
		this.verticalDeformationAtStart = deformation;
	}
	
	private int verticalDeformationAtMid;
	
	public int getVerticalDeformationAtMid()
	{
		return this.verticalDeformationAtMid;
	}
	
	public void setVerticalDeformationAtMid(int deformation)
	{
		this.verticalDeformationAtMid = deformation;
	}
	
	private int verticalDeformationAtLast;
	
	public int getVerticalDeformationAtLast()
	{
		return this.verticalDeformationAtLast;
	}
	
	public void setVerticalDeformationAtLast(int deformation)
	{
		this.verticalDeformationAtLast = deformation;
	}
	
	private int verticalDeformationAtPreviousGirderLast;
	
	public int getVerticalDeformationAtPreviousGirderLast()
	{
		return this.verticalDeformationAtPreviousGirderLast;
	}
	
	public void setVerticalDeformationAtPreviousGirderLast(int deformation)
	{
		this.verticalDeformationAtPreviousGirderLast = deformation;
	}
	

	// calculation & test result
	private String horizontalDeformationCalculated;
	
	public String getHorizontalDeformationCalculated()
	{
		return this.horizontalDeformationCalculated;
	}
	
	public void setHorizontalDeformationCalculated(String calculated)
	{
		this.horizontalDeformationCalculated = calculated;
	}
	
	private String horizontalDeformationTestResult;

	public String getHorizontalDeformationTestResult()
	{
		return this.horizontalDeformationTestResult;
	}
	
	public void setHorizontalDeformationTestResult(String result)
	{
		this.horizontalDeformationTestResult = result;
	}

	private String spanCalculated;
	
	public String getSpanCalculated()
	{
		return this.spanCalculated;
	}
	
	public void setSpanCalculated(String calculated)
	{
		this.spanCalculated = calculated;
	}
	
	private String spanTestResult;
	
	public String getSpanTestResult()
	{
		return this.spanTestResult;
	}
	
	public void setSpanTestResult(String result)
	{
		this.spanTestResult = result;
	}
	
	private String reverseCamberCalculated;
	
	public String getReverseCamberCalculated()
	{
		return this.reverseCamberCalculated;
	}
	
	public void setReverseCamberCalculated(String calculated)
	{
		this.reverseCamberCalculated = calculated;
	}
	
	private String reverseCamberTestResult;
	
	public String getReverseCamberTestResult()
	{
		return this.reverseCamberTestResult;
	}
	
	public void setReverseCamberTestResult(String result)
	{
		this.reverseCamberTestResult = result;
	}
	
	private String heightDifferenceBetweenThisAndPreviousGirder;
	
	public String getHeightDifferenceBetweenThisAndPreviousGirder()
	{
		return this.heightDifferenceBetweenThisAndPreviousGirder;
	}
	
	public void setHeightDifferenceBetweenThisAndPreviousGirder(String difference)
	{
		this.heightDifferenceBetweenThisAndPreviousGirder = difference;
	}

	private String heightDifferenceTestResult;
	
	public String getHeightDifferenceTestResult()
	{
		return this.heightDifferenceTestResult;
	}
	
	public void setHeightDifferenceTestResult(String result)
	{
		this.heightDifferenceTestResult = result;
	}
	
}
