/**
 * 
 */

// AJAX 요청 성공 시 실행
function refreshDetailPageToNewInspection(result){
	if(result.girderId == null){
		$(".check_list h3").html($(".check_list h3").html() + " ( 교체이력 및 준공이력이 없습니다. )");
		trTag = '<tr><td colspan="11">해당 거더의 준공 및 교체 이력이 없습니다.</td></tr>';
		$(".check_list>table").append(trTag);
		return;
	}else{
		$(".check_list h3").html($(".check_list h3").html() + 
				" ( 교체일 : "+ result.girderReplacementDate +", 교체이유 : "+ result.replacementReason + ", 담당자 : " + result.personInCharge + " )" );
	}
	
	var trTag = ""
	inspectionDatas = result.inspectionRecords;
	if(inspectionDatas.length == 0){
		trTag = '<tr><td colspan="11">점검이력이 없습니다.</td></tr>';
		$(".check_list>table").append(trTag);
	}else{
		for (inspectionRow in inspectionDatas){
			inspectionData = inspectionDatas[inspectionRow]
			trTag += "<tr>"
			for (categoryName in inspectionData){
				data = inspectionData[categoryName]
				if(data == 0){
					data = '-'
					trTag += "<td>"+ data
				}else if(!isNaN(data) && categoryName != "inspectionType" && categoryName != "count"){
					trTag += '<td class="warn">'+ data
				}else{
					trTag += "<td>"+ data
				}
				trTag += "</td>"
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