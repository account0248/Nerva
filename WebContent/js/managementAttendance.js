
//変数宣言・挿入html宣言
//要素・属性
let txtArea =(name,className)=>{ return '<input type="text" class="'+className+'" name="'+name+'" value="">'};
let option =(value,text)=>{return '<option value="'+value+'">'+text+'</option>'};
let txtTime =(name,className)=>{return '<input type="time" class="'+className+'" name="'+name+'">'};

// 曜日配列
const WeekChars = [ "日", "月", "火", "水", "木", "金", "土" ];
const monthList = [4,5,6,7,8,9,10,11,12,1,2,3];

let selectHoliday ='<select name="holiday" class="attendanceSelect_width">';
selectHoliday += '<option value=""></option>';
selectHoliday += '<option value="有給(全休)">有給(全休)</option>';
selectHoliday += '<option value="有給(半休)">有給(半休)</option>';
selectHoliday += '<option value="欠勤(全休)">欠勤(全休)</option>';
selectHoliday += '<option value="欠勤(遅早)" >欠勤(遅早)</option>';
selectHoliday += '<option value="振休(所定)">振休(所定)</option>';
selectHoliday += '<option value="振休(法定)">振休(法定)</option>';
selectHoliday += '<option value="特休(全休)">特休(全休)</option>';
selectHoliday += '<option value="特休(半休)">特休(半休)</option>';
selectHoliday += '<option value="明け休">明け休</option>';
selectHoliday += '</select>';



//各メソッド

/**
 * 曜日取得メソッド
 * @param {*} year 
 * @param {*} month 
 * @param {*} day 
 * @returns 
 */
let getDayOfWeek=(year,month,day)=>{
   
 
   // 入力された数値から日付オブジェクトを作る
   let $date = new Date(year,month,day);
 
   return WeekChars[$date.getDay()];
}

/**
 * 2021年度から現在の年度までを取得
 * @param {*} year 
 * @returns 
 */
let getSelectYear =(year)=>{

    let defaultYear = [2021];

    for(i=defaultYear[0]+1;i<=year;i++){

        defaultYear.push(i);

    }
    return defaultYear;
    

}


/**
 * テーブルに挿入
 * @param {*} row 
 * @param {*} month 
 * @param {*} i 
 * @param {*} youbi 
 */
let createData =(row,month,i,youbi)=> {

	let cell1 = row.insertCell(-1);
	cell1.innerHTML = `${month}/${i}`;

    let cell2 = row.insertCell(-1);
    cell2.innerHTML = youbi;

    let cell3 = row.insertCell(-1);
    cell3.innerHTML = txtTime('start','attendanceText_width');

    let cell4 = row.insertCell(-1);
    cell4.innerHTML = txtTime('end','attendanceText_width');

    let cell5 = row.insertCell(-1);
	cell5.innerHTML = txtTime('','attendanceText_width');

    let cell6 = row.insertCell(-1);
    let cell6Str = txtTime('','attendanceText_width');
    if(youbi!='土')cell6Str = cell6Str.substring(0, cell6Str.length - 1)+'disabled>';
	cell6.innerHTML = cell6Str;

    let cell7 = row.insertCell(-1);
    cell7.innerHTML = selectHoliday;

    let cell8 = row.insertCell(-1);
    cell8.innerHTML = txtArea('','attendanceText_width');

    let cell9 = row.insertCell(-1);
    cell9.innerHTML = txtTime('','attendanceText_width');

    let cell10 = row.insertCell(-1);
    cell10.innerHTML = txtTime('','attendanceText_width');

    let cell11 = row.insertCell(-1);
    cell11.innerHTML = txtArea('','attendanceText_width');

}

 /**
 * Excel画面追加
 */
 let zissou = ()=>{
    let $table = document.getElementById("Attendance");
    let year = document.getElementById("year").value;
    let month = document.getElementById("month").value;

    //月の最終日取得
    if(month==1||month==2||month==3)year=year+1;
    let $date = new Date(year,month,0);
    let lastDay = $date.getDate();

    console.log(lastDay);

    //html挿入
    for(i=1;i<=lastDay;i++){
        
        createData($table.insertRow(-1),month,i,getDayOfWeek(year,month,i));
    }

}

 /**
 * セレクタにオプション追加メソッド
 * @param {*} selectDocument 
 * @param {*} value 
 * @param {*} textContent 
 */
let selectOptionInsert =(selectDocument,value ,textContent)=>{
    //オプション属性作成
    const $optionElement = document.createElement('option');
    
    $optionElement.value = value;
    $optionElement.textContent = textContent;

    //挿入
    selectDocument.appendChild($optionElement);
}


 //各実行メソッド
 /**
 *ページ開いたときに年セレクタの書き換え 
 */
 window.onload = ()=>{
    // ページ読み込み時に実行したい処理
    let $selectYear = document.getElementById("year");

    //現在日時取得
    let $date = new Date();
    //年取得
    let yearNow = $date.getFullYear();
    //月取得
    let monthNow = $date.getMonth()+1;

    //年度調整
    if(monthNow==1||monthNow==2||monthNow==3)yearNow=yearNow-1;

    //2021年度から現在までの年度取得
    let year = getSelectYear(yearNow);
    
    //オプションに追加
    for(i=0;i<year.length;i++){
    
        selectOptionInsert($selectYear,year[i],year[i]);

    }};


let $select = document.querySelector('[name="year"]');

//年度が変更されたときの処理
$select.onchange = event => { 

    console.log($select.value);
    let $selectMonth = document.getElementById("month");

    let l = $selectMonth.length;
    //let $selectMonth = $('select[name=month]');

     

    //オプション削除
    for(i=1;i<l;i++){
        //console.log($selectMonth.)
        let u = $selectMonth.length-1;
        console.log(u);
        
        $selectMonth.remove(u);
    }

    if($select.value.length!=0){

    
    let selectYear = $select.value;

    //現在日時取得
    let $date = new Date();
    //年取得
    let yearNow = $date.getFullYear();
    //月取得
    let monthNow = $date.getMonth()+1;

    //年度調整
    if(monthNow==1||monthNow==2||monthNow==3)yearNow=yearNow-1;

    //今年度だった場合の処理
    if(selectYear==yearNow){
        let i = 0;
        selectOptionInsert($selectMonth,monthList[i],monthList[i]);

        while(monthNow!==monthList[i]){

            selectOptionInsert($selectMonth,monthList[i+1],monthList[i+1]);

            i++;
        }

    }else{
        for(i=0;i<12;i++){

            selectOptionInsert($selectMonth,monthList[i],monthList[i]);
        }



    }
}
    
};

//実装()


let Attendance = ()=> {

	zissou();
	
};