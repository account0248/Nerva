
jQuery(function () {
    var $registInput =  $('#new-registory input[type=password],#new-registory input[type=text],#new-registory select');
    $($registInput).addClass('required4');
    $($registInput).on('change',function(){
        if($(this).val().length!=0){
            $(this).removeClass('required4');
        }else{
            $(this).addClass('required4');
        }
    });
    var $displayGrey = $('#display-grey');
    var $newRegistory = $('#new-registory');
    var $cancelbtn = $('.fa-times-circle');
    $('#regist_button').on('click', function () {
        $displayGrey.show();
        $newRegistory.show();
    });

    $displayGrey.on('click', function () {
        $(this).next().fadeOut(200, function () {
            $displayGrey.hide();
            $($registInput).val("");
            $($registInput).addClass('required4');
        });
    });

    $cancelbtn.on('click', function () {
        $newRegistory.fadeOut(200, function () {
            $displayGrey.hide();
            $($registInput).val("");
            $($registInput).addClass('required4');
        });
    });
});

