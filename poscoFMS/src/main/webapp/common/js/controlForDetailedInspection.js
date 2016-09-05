/**
 * 
 */

// AJAX 요청 성공 시 실행
function refreshDetailPageToNewInspection(result){
	$(".check_list>table tbody").empty();
	if(result.girderId == null){
		$(".check_list h3").html("점검 ( 1977년도 준공, 공장준공 )");
		trTag = '<tr><td colspan="11">점검 이력이 없습니다.</td></tr>';
		$(".check_list>table").append(trTag);
		return;
	}else{
		$(".check_list h3").html(
				"점검 ( 교체일 : "+ result.girderReplacementDate +", 교체이유 : "+ result.replacementReason + ", 담당자 : " + result.personInCharge + " )" );
	}
	
	trTag = "";
	inspectionDatas = result.inspectionRecords;
	if(inspectionDatas && inspectionDatas.length == 0){
		trTag = '<tr><td colspan="11">점검이력이 없습니다.</td></tr>';
		$(".check_list>table").append(trTag);
	}else{
		for (inspectionRow in inspectionDatas){
			inspectionData = inspectionDatas[inspectionRow];
			trTag += "<tr>";
			for (categoryName in inspectionData){
				data = inspectionData[categoryName];
				if(data == 0){
					data = '-';
					trTag += "<td>"+ data;
				}else if(!isNaN(data) && categoryName != "inspectionType" && categoryName != "count"){
					trTag += '<td class="warn">'+ data;
				}else{
					trTag += "<td>"+ data;
				}
				trTag += "</td>";
			}
			trTag += "</tr>";
		}
			
		$(".check_list>table").append(trTag);
		$(".check_drawing li").removeClass("warn");
		
		// 점검 마커 표시
		$(".check_list>table tr").click(
		function (){
			$(".check_drawing li").removeClass("warn");
			var subTd = $(this).find("td");
			var markerId = 'marker' + subTd[1].innerHTML;
			$("#"+ markerId).addClass("warn");
		})
	}
}