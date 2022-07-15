
jQuery(function () {
    var updatething = $('td .updating');
    var inputVal = [];
    var  updating   = function(tr,inputVal){
        $(updatething).on('change', function () {
            var $getVal = $(this).val();
            var $valIndex = $(tr).index(this);
            if ($getVal != inputVal[$valIndex]) {
                $(this).addClass('text-updated');
                console.log($(this).val().length);
                if($(this).val().length ==0){
                    $(this).removeClass('text-updated');
                    $(this).addClass('required4');
                }else{
                    $(this).removeClass('required4');
                }
            } else {
                $(this).removeClass('text-updated');
            }
        })
     
        $('#registed_button').on('click', function () {
            getInputVal()
        });
    };

       var getInputVal = function(inputVal){
            inputVal.length = 0;
            updatething.each(function(i, elem) {
                inputVal.push($(elem).val());
            });
        }

    getInputVal(inputVal);
    updating(updatething,inputVal);
});