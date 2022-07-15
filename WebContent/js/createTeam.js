function fnOpenNormalDialog() {
    // Define the Dialog and its properties.
    $("#dialog-confirm").dialog({
        resizable: false,
        modal: true,
        title: "チーム作成画面",
        height: 150,
        width: 400,
        buttons: {
            "作成する": function () {
                $(this).dialog('close');
                callback(true);
            },
                "作成しない": function () {
                $(this).dialog('close');
                callback(false);
            }
        }
    });
}

$('#btnOpenDialog2').click(fnOpenNormalDialog);

function callback(value) {
    if (value) {
        document.createTeamAction.submit();
    }
}

