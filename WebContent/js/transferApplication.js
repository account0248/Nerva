
function approveBtn(item) {
	console.log(item);
	var aa =item.slice(7);//数字
	console.log(aa);
	var bb =item.substr( 0, 7);//承認、却下
	console.log(bb);
	var cc = "application"+aa;
	console.log(cc);
	var dd = "trasferApplication"+aa;
	var ee ="#"+dd;


	$('#trasferApplication').val(bb);
	$('#trasferApplications').val(aa);
	document.application.submit();

}


function rejectBtn(item) {

	console.log(item);
	var aa =item.slice(6);//数字
	console.log(aa);
	var bb =item.substr( 0, 6);//承認、却下
	console.log(bb);
	var cc = "application"+aa;
	console.log(cc);
	var dd = "trasferApplication";
	console.log(dd);
	var ee ="#"+dd;
	//trasferApplication3

	$('#trasferApplication').val(bb);
	$('#trasferApplications').val(aa);

	document.application.submit();
}
$(document).ready( function(){
	// ページ読み込み時に実行したい処理
	var errorTransferApplicationArr = $("#errorTransferApplication").val();

console.log(errorTransferApplicationArr);
	if(errorTransferApplicationArr!=null){
	fnOpenNormalDialog();
	}


	});
function fnOpenNormalDialog() {
    // Define the Dialog and its properties.
    $("#dialog-confirm").dialog({
        resizable: false,
        modal: false,
        title: "移管承認画面",
        width: 1000,
        buttons: {
            "閉じる": function () {
                $(this).dialog('close');
                callback(true);
            }
        }
    });
}
$('#btnOpenDialog').click(fnOpenNormalDialog);
function callback(value) {
    if (value) {
        document.form.submit();
    }
}