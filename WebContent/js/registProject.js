function fnOpenNormalDialog() {
    // Define the Dialog and its properties.
    $("#dialog-confirm").dialog({
        resizable: false,
        modal: true,
        title: "案件登録画面",
        height: 150,
        width: 400,
        buttons: {
            "登録する": function () {
                $(this).dialog('close');
                callback(true);
            },
                "登録しない": function () {
                $(this).dialog('close');
                callback(false);
            }
        }
    });
}

$('#btnOpenDialog2').click(fnOpenNormalDialog);

function callback(value) {
    if (value) {
        document.form.submit();
    }
}