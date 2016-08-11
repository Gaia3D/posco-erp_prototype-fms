package kr.posco.erp.poscofms.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.posco.erp.poscofms.main.dao.PoscoFmsDao;
import kr.posco.erp.poscofms.main.vo.GirderErrorStatusSummaryVO;
import kr.posco.erp.poscofms.main.vo.GirderErrorStatusVO;
import kr.posco.erp.poscofms.main.vo.GirderInspectionVO;
import kr.posco.erp.poscofms.main.vo.GirderMeasurementVO;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	PoscoFmsDao poscoFmsDao;
	
	private void processAllTests(GirderMeasurementVO input)
	{
		// Test for horizontal deformation tolerance
		testForHorizontalDeformationTolerance(input);

		// Test for span between two neighbor lines
		testForSpanBetweenTwoNeighborLines(input);
		
		// Test for reverse camber
		testForReverseCamber(input);
		
		// Test for tolerance of height difference between two neighbor girders
		testForToleranceOfHeightFromPreviousGirderEnd(input);
	}
	
	private void testForHorizontalDeformationTolerance(GirderMeasurementVO input)
	{
		double referenceValueAtStart = input.getGapLengthAtMid() * 1000.0 / 500.0;
		double referenceValueAtMid = input.getGapLengthAtLast() * 1000.0 / 500.0;
		double referenceValueAtLast = (input.getGapLengthAtLast() + input.getGapLengthAtMid()) * 1000.0 / 500.0;
		
		double comparedValueAtStart;
		double comparedValueAtMid;
		double comparedValueAtLast;
		
		int maskValue =
				(input.getHorizontalDeformationAtStart() != 9999 ? 1 : 0) +
				(input.getHorizontalDeformationAtMid() != 9999 ? 2 : 0) +
				(input.getHorizontalDeformationAtLast() != 9999 ? 4 : 0);
		
		switch(maskValue)
		{
		case 7: // All measurements are available
		{
			comparedValueAtStart = Math.abs(input.getHorizontalDeformationAtMid() - input.getHorizontalDeformationAtStart());
			comparedValueAtMid = Math.abs(input.getHorizontalDeformationAtLast() - input.getHorizontalDeformationAtMid());
			comparedValueAtLast = Math.abs(input.getHorizontalDeformationAtLast() - input.getHorizontalDeformationAtStart());
			input.setHorizontalDeformationCalculated(
					String.format("%.1f", comparedValueAtStart) + "," +
					String.format("%.1f", comparedValueAtMid) + "," +
					String.format("%.1f", comparedValueAtLast)
				);
			
			if(referenceValueAtStart < comparedValueAtStart ||
				referenceValueAtMid < comparedValueAtMid ||
				referenceValueAtLast < comparedValueAtLast)
				input.setHorizontalDeformationTestResult("failure");
			else
				input.setHorizontalDeformationTestResult("pass");
		}
		break;
		case 6:	// mid & last measurements are available
		{
			comparedValueAtMid = Math.abs(input.getHorizontalDeformationAtLast() - input.getHorizontalDeformationAtMid());
			input.setHorizontalDeformationCalculated("-," + String.format("%.1f", comparedValueAtMid) + ",-");
			if(referenceValueAtMid < comparedValueAtMid)
				input.setHorizontalDeformationTestResult("failure");
			else
				input.setHorizontalDeformationTestResult("unchecked");
		}
		break;
		case 5:	// start & last measurements are available
		{
			comparedValueAtLast = Math.abs(input.getHorizontalDeformationAtLast() - input.getHorizontalDeformationAtStart());
			input.setHorizontalDeformationCalculated("-,-," + String.format("%.1f", comparedValueAtLast));
			
			if(referenceValueAtLast < comparedValueAtLast)
				input.setHorizontalDeformationTestResult("failure");
			else
				input.setHorizontalDeformationTestResult("unchecked");
		}
		break;
		case 3: // start & mid measurements are available
		{
			comparedValueAtStart = Math.abs(input.getHorizontalDeformationAtMid() - input.getHorizontalDeformationAtStart());
			input.setHorizontalDeformationCalculated(String.format("%.1f", comparedValueAtStart) + ",-,-");
			
			if(referenceValueAtStart < comparedValueAtStart)
				input.setHorizontalDeformationTestResult("failure");
			else
				input.setHorizontalDeformationTestResult("unchecked");
		}
		break;
		case 0: // At least two of three measurements are empty 
		case 1:
		case 2:
		case 4:
		{
			input.setHorizontalDeformationCalculated("-,-,-");
			input.setHorizontalDeformationTestResult("unchecked");
		}
		break;
		}
	}
	
	private void testForSpanBetweenTwoNeighborLines(GirderMeasurementVO input)
	{
		double referenceValue = 21.7;
		
		double comparedValueAtStart;
		double comparedValueAtMid;
		double comparedValueAtLast;
		
		int maskValueForThis =
				(input.getHorizontalDeformationAtStart() != 9999 ? 1 : 0) +
				(input.getHorizontalDeformationAtMid() != 9999 ? 2 : 0) +
				(input.getHorizontalDeformationAtLast() != 9999 ? 4 : 0);
		
		int maskValueForCounter =
				(input.getHorizontalDeformationAtCounterGirderStart() != 9999 ? 1 : 0) +
				(input.getHorizontalDeformationAtCounterGirderMid() != 9999 ? 2 : 0) +
				(input.getHorizontalDeformationAtCounterGirderLast() != 9999 ? 4 : 0);
		
		int maskResult = maskValueForThis & maskValueForCounter;

		switch(maskResult)
		{
		case 7:
		{
			comparedValueAtStart = Math.abs(input.getHorizontalDeformationAtStart() + input.getHorizontalDeformationAtCounterGirderStart());
			comparedValueAtMid = Math.abs(input.getHorizontalDeformationAtMid() + input.getHorizontalDeformationAtCounterGirderMid());
			comparedValueAtLast = Math.abs(input.getHorizontalDeformationAtLast() + input.getHorizontalDeformationAtCounterGirderLast());
			input.setSpanCalculated(
					String.format("%.2f", comparedValueAtStart) + "," +
					String.format("%.2f", comparedValueAtMid) + "," +
					String.format("%.2f", comparedValueAtLast)
				);
			if(referenceValue < comparedValueAtStart ||
				referenceValue < comparedValueAtMid ||
				referenceValue < comparedValueAtLast)
				input.setSpanTestResult("failure");
			else
				input.setSpanTestResult("pass");
		}
		break;
		case 6:
		{
			comparedValueAtMid = Math.abs(input.getHorizontalDeformationAtMid() + input.getHorizontalDeformationAtCounterGirderMid());
			comparedValueAtLast = Math.abs(input.getHorizontalDeformationAtLast() + input.getHorizontalDeformationAtCounterGirderLast());
			input.setSpanCalculated("-," +
					String.format("%.2f", comparedValueAtMid) + "," +
					String.format("%.2f", comparedValueAtLast)
				);
			if(referenceValue < comparedValueAtMid ||
				referenceValue < comparedValueAtLast)
				input.setSpanTestResult("failure");
			else
				input.setSpanTestResult("unchecked");
		}
		break;
		case 5:
		{
			comparedValueAtStart = Math.abs(input.getHorizontalDeformationAtStart() + input.getHorizontalDeformationAtCounterGirderStart());
			comparedValueAtLast = Math.abs(input.getHorizontalDeformationAtLast() + input.getHorizontalDeformationAtCounterGirderLast());
			input.setSpanCalculated(
					String.format("%.2f", comparedValueAtStart) + ",-," +
					String.format("%.2f", comparedValueAtLast)
				);
			if(referenceValue < comparedValueAtStart ||
				referenceValue < comparedValueAtLast)
				input.setSpanTestResult("failure");
			else
				input.setSpanTestResult("unchecked");
		}
		break;
		case 3:
		{
			comparedValueAtStart = Math.abs(input.getHorizontalDeformationAtStart() + input.getHorizontalDeformationAtCounterGirderStart());
			comparedValueAtMid = Math.abs(input.getHorizontalDeformationAtMid() + input.getHorizontalDeformationAtCounterGirderMid());
			input.setSpanCalculated(
					String.format("%.2f", comparedValueAtStart) + "," +
					String.format("%.2f", comparedValueAtMid) + ",-"
				);
			if(referenceValue < comparedValueAtStart ||
				referenceValue < comparedValueAtMid)
				input.setSpanTestResult("failure");
			else
				input.setSpanTestResult("unchecked");
		}
		break;
		case 4:
		{
			comparedValueAtLast = Math.abs(input.getHorizontalDeformationAtLast() + input.getHorizontalDeformationAtCounterGirderLast());
			input.setSpanCalculated("-.-," + String.format("%.2f", comparedValueAtLast));
			if(referenceValue < comparedValueAtLast)
				input.setSpanTestResult("failure");
			else
				input.setSpanTestResult("unchecked");
		}
		break;
		case 2:
		{
			comparedValueAtMid = Math.abs(input.getHorizontalDeformationAtMid() + input.getHorizontalDeformationAtCounterGirderMid());
			input.setSpanCalculated("-," + String.format("%.2f", comparedValueAtMid) + ",-");
			if(referenceValue < comparedValueAtMid)
				input.setSpanTestResult("failure");
			else
				input.setSpanTestResult("unchecked");
		}
		break;
		case 1:
		{
			comparedValueAtStart = Math.abs(input.getHorizontalDeformationAtStart() + input.getHorizontalDeformationAtCounterGirderStart());
			input.setSpanCalculated(String.format("%.2f", comparedValueAtStart) + ",-,-");
			if(referenceValue < comparedValueAtStart)
				input.setSpanTestResult("failure");
			else
				input.setSpanTestResult("unchecked");
		}
		break;
		case 0:
		{
			input.setSpanCalculated("-,-,-");
			input.setSpanTestResult("unchecked");
		}
		break;
		}
	}
	
	private void testForReverseCamber(GirderMeasurementVO input)
	{
		if(input.getVerticalDeformationAtStart() == 9999 ||
			input.getVerticalDeformationAtMid() == 9999 ||
			input.getVerticalDeformationAtLast() == 9999)
		{
			input.setReverseCamberCalculated("-");
			input.setReverseCamberTestResult("unchecked");
		}
		else
		{
			double referenceValue = input.getVerticalDeformationAtMid();
			double comparedValue = (input.getVerticalDeformationAtStart() + input.getVerticalDeformationAtLast()) / 2.0;
			
			input.setReverseCamberCalculated(String.format("%.1f", comparedValue));
			
			if(referenceValue < comparedValue)
				input.setReverseCamberTestResult("failure");
			else
				input.setReverseCamberTestResult("pass");
		}
	}
	
	private void testForToleranceOfHeightFromPreviousGirderEnd(GirderMeasurementVO input)
	{
		if(input.getVerticalDeformationAtStart() == 9999 ||
			input.getVerticalDeformationAtPreviousGirderLast() == 9999)
		{
			input.setHeightDifferenceBetweenThisAndPreviousGirder("-");
			input.setHeightDifferenceTestResult("unchecked");
		}
		else
		{
			int referenceValue = 5;
			int comparedValue = input.getVerticalDeformationAtStart() - input.getVerticalDeformationAtPreviousGirderLast();
			
			input.setHeightDifferenceBetweenThisAndPreviousGirder(String.format("%d", comparedValue));
			
			if(referenceValue <= comparedValue)
				input.setHeightDifferenceTestResult("failure");
			else
				input.setHeightDifferenceTestResult("pass");
		}
	}
	
	@Override
	public List<String> getAllMeasurementDates() {

		return poscoFmsDao.selectTotalMeasurementDates();
	}

	@Override
	public GirderErrorStatusSummaryVO getGirderErrorStatus(String date) {

		GirderErrorStatusSummaryVO vo = new GirderErrorStatusSummaryVO();
		
		vo.setAllGirderErrorStatus(poscoFmsDao.selectAllGirderErrorStatus(date));
		
		if(vo.getAllGirderErrorStatus() == null)
			return null;
		
		int measurementErrorCount = 0;
		int inspectionErrorCount = 0;
		for(GirderErrorStatusVO girderErrorStatus : vo.getAllGirderErrorStatus())
		{
			if(!girderErrorStatus.getMeasurementPass())
				measurementErrorCount++;
			
			if(!girderErrorStatus.getInspectionPass())
				inspectionErrorCount++;
		}
		
		vo.setMeasurementErrorCount(measurementErrorCount);
		vo.setInspectionErrorCount(inspectionErrorCount);
		
		return vo;
	}

	@Override
	public GirderMeasurementVO getGirderMeasurement(String date, String id) {
		
		GirderMeasurementVO vo = poscoFmsDao.selectGirderMeasurement(date, id);
		
		this.processAllTests(vo);

		return vo;
	}

	@Override
	public GirderInspectionVO getGirderInspection(String id) {
		
		GirderInspectionVO vo = poscoFmsDao.selectGirderReplacement(id);
		
		if(vo == null || vo.getGirderReplacementDate() == null || vo.getGirderReplacementDate().isEmpty())
			return null;
		
		vo.setInspectionRecords(poscoFmsDao.selectGirderInspectionRecords(vo.getGirderReplacementDate(), id));
		
		return vo;
	}

	@Override
	public List<Integer> getGirderHorizontalDeformationHistory(String id, int positionType) {

		return poscoFmsDao.selectGirderHorizontalDeformationHistory(id, positionType);
	}

	@Override
	public List<Integer> getGirderVerticalDeformationHistory(String id, int positionType) {

		return poscoFmsDao.selectGirderVerticalDeformationHistory(id, positionType);
	}	
}
