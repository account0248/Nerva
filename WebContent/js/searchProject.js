
var a;
$(function(){
  $("#text_box").on('input',function() {
     a = $(this).val();
     console.log(a);
  });
});

var searchProject =function(){
	document.getElementById('searchProject').click();
}

$('#searchProject').click(
		function temp() {

			pjtDeleteError = document.getElementById("pjtDeleteError");
            pjtDeleteError.innerHTML = "";


			var projectId = $('#projectId').val();

			var projectName = $('#projectName').val();

			var resposibleName = $('#resposibleName').val();

			var contratorName = $('#companyName').val();

			var inductryName = $('#inductryName').val();

			var totalTeamsProject = $('#totalTeamsProject').val();

			var totalEmpProject = $('#totalEmpProject').val();

			var request = {
				projectId : projectId,

				projectName : projectName,

				resposibleName : resposibleName,

				contratorName : contratorName,

				clientName : a,// clientName,

				inductryName : inductryName,

				totalTeamsProject : totalTeamsProject,

				totalEmpProject : totalEmpProject

			};

			// console.log(janru);

			$.ajax({
				type : "POST",
				url : "./SearchProjectAction",
				data : request,
				async : true, // true:非同期(デフォルト), false:同期
				dataType : 'json',
				data : {
                    json : request
                },

				success : function(data) {
					//if json error 
					 if(data == ""){
						window.location.href = 'error.jsp';
						}

					// var a = data;
					var table = document.getElementById("project");
					  while (table.rows[0])
			                table.deleteRow(0);
					// arr = JSON.stringify(data);
					var searchProjectDTO = data["searchProjectJsonDTO"];
                 var errorList = data["searchProjectErrorMsgJsonDTO"];
                  target = document.getElementById("errorList");
                 target.innerHTML = "";
                 if(errorList!=null){

                	 for (var i = 0; i < errorList.length; i++) {
                         target.innerHTML += errorList[i] + "<br>";
                     }

                 }
                 if(searchProjectDTO!=null){

                    // var searchProject =searchProjectJsonDTO;

                     for (var i = 0; i < searchProjectDTO.length; i++) {
                         var projectInfo = searchProjectDTO[i];
                         createData(table.insertRow(-1), i + 1, projectInfo);
                     }
                     new List('sort', options);

                 }

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
							window.location.href = 'error.jsp';
				}

			});// ajax

		});//

var options = {
	    valueNames : [ 'projectId', 'projectName', 'responsibleName',
	            'contratorName', 'clientName', 'inductryName',
	            'totalTeamsProject','totalEmpProject', 'projectStartDate' ,'projectCompleteDate']
	};
	var createData = function(row, num, projectInfo) {
	    var cell1 = row.insertCell(-1);
	    cell1.innerHTML = "    <form method=\"POST\" action=\"ShowProjectDetailAction\"><input type=\"hidden\" name=\"projectId\" id=\""
	            + projectInfo.projectId
	            + "\" value=\""
	            + projectInfo.projectId
	            + "\"><input type=\"submit\" value=\"詳細\"/></form>";
	    var cell2 = row.insertCell(-1);
	    cell2.innerHTML = projectInfo.projectId;
	    $(cell2).addClass("projectId");

	    var cell3 = row.insertCell(-1);
	    cell3.innerHTML = projectInfo.projectName;
	    $(cell3).addClass("projectName");

	    var cell4 = row.insertCell(-1);
	    cell4.innerHTML = projectInfo.responsibleName;
	    $(cell4).addClass("responsibleName");

	    var cell5 = row.insertCell(-1);
	    cell5.innerHTML = projectInfo.contratorName;
	    $(cell5).addClass("contratorName");

	    var cell6 = row.insertCell(-1);
	    cell6.innerHTML = projectInfo.clientName;
	    $(cell6).addClass("clientName");

	    var cell6 = row.insertCell(-1);
	    cell6.innerHTML = projectInfo.inductryName;
	    $(cell6).addClass("inductryName");

	    var cell7 = row.insertCell(-1);
	    if(projectInfo.totalTeamsProject!=null){
	    	cell7.innerHTML = projectInfo.totalTeamsProject;
	    }else{
	    	cell7.innerHTML = "0"
	    }
	    $(cell7).addClass("totalTeamsProject");

	    var cell8 = row.insertCell(-1);
	    cell8.innerHTML = projectInfo.totalEmpProject;
	    $(cell8).addClass("totalEmpProject");

	    var cell9 = row.insertCell(-1);
	    cell9.innerHTML = projectInfo.projectStartDate;
	    $(cell9).addClass("projectStartDate");

	    var cell10 = row.insertCell(-1);

	    if(projectInfo.projectCompleteDate==""){
	    	cell10.innerHTML = "-";
	    }else{
	    cell10.innerHTML = projectInfo.projectCompleteDate;
	    }

	    $(cell10).addClass("projectCompleteDate");
	}

