let deleteTeamId;
let id;
let deleteTeamFlg = false;


function fnOpenNormalDialog() {

    // Define the Dialog and its properties.
    $("#dialog-confirm-employee-delete").dialog({
        resizable: false,
        modal: true,
        title: "チーム検索画面",
        height: 150,
        width: 400,
        buttons: {
            "削除する": function () {
                $(this).dialog('close');
                callback(true);
            },
                "削除しない": function () {
                $(this).dialog('close');
                callback(false);
            }
        }
    });
}

$('#btnDeleteDialog').click(fnOpenNormalDialog);

function callback(value) {
    if (value) {
    	deleteTeamCallback(this.deleteTeamFlg = true);
    }else{
    	deleteTeamCallback(this.deleteTeamFlg = false);
    }
}

var pushDelete = function(deleteTeamId1, id1) {
	this.deleteTeamId = deleteTeamId1;
	this.id = id1;
	console.log(deleteTeamFlg);
	fnOpenNormalDialog();
}

function deleteTeamCallback(deleteTeamFlg){
	console.log(deleteTeamFlg);

	if (deleteTeamFlg) {
		var teamInfoId = this.deleteTeamId.id;
		var request = {
			teamId : teamInfoId,
		};

		$.ajax({
			type : "POST", // GET / POST
			url : "./DeleteTeamAction", // 送信先のServlet URL
			data : request, // リクエストJSON
			async : true, // true:非同期(デフォルト), false:同期
			dataType : 'json',
			data : {
				json : request
			},
		}).then(function(data) {
			 if(data == ""){
						window.location.href = 'error.jsp';
						}
			var errorList = data["errorListDTO"];
			target = document.getElementById("errorList");
			target.innerHTML = "";
			// エラーがあった場合はエラーメッセージを表示
			if (errorList != null) {
				for (var i = 0; i < errorList.length; i++) {
					target.innerHTML += errorList[i] + "<br>";
				}
				scrollTo(0, 0);
			} else if (errorList == null) {
				/*// 検索しなおし
				createResultTeam();*/
				//該当のチームの行を削除
				globalThis.id.remove();
			}
		}, function(XMLHttpRequest, textStatus, errorThrown) {
			window.location.href = 'error.jsp';
		});
	}
}