/**
 *
 */

var lastNameAry = new Array("佐","鈴","高","田","渡","伊","山","中","小","加","吉","松","井","斎","木","林","清","池","阿","森","橋","石","前","藤","岡","後","長","村","近","坂","遠","青","西","福","太","三","斉","原","竹","金","和","上","柴","酒","工","横","宮","内","安","谷","大","今","丸","河","武","杉","増","菅","平","久","千","岩","野","菊","新","辺","本","口","水","部","下","川","島","浦","子","塚","保","葉","尾","地");
var firstNameAry = new Array("蓮","湊","大","陽","悠","樹","朝","蒼","颯","律","結","新","匠","陸","晴","奏","碧","翔","瑛","旭","伊","仁","海","光","暖","朔","歩","優","絢","遥","誠","健","一","慶","太","成","楓","司","琉","岳","航","葵","圭","涼","快","翼","綾","善","怜","昊","泰","賢","聡","龍","杏","さ","凛","芽","紬","莉","美","澪","あ","琴","咲","は","凜","愛","朱","心","花","紗","華","蘭","柚","ひ","す","夏","菫","詩","百","和","明","彩","玲","唯","菜","音","小","千","文","由","桜","鈴","栞","凪","帆","奈","真","人","斗","向","智","生","織","汰","士","多","知","輝","空","夢","介","吾","地","之","月","く","依","子","衣","か","良","な","春","葉","央","希","ま","み","羽","尋","里","乃","緒","梨","那","ら","り","れ","た");
var companyAry = new Array("株式会社V","K株式会社","株式会社O");
var bossAry = new Array("原 陽子","高橋 友恵","古川 克","松岡 幸美","今野 次生","山岡 保二","菊地 運吉","柴田 権一");
var projectName = new Array("Morbid Rusty Python","Rough Waffle","Square Drill","Quality Essential Bulldozer","Rough Metaphor","Western Storm","Accidentally Zeus");
var button = '<input type="button" value="詳細" onclick="location.href=\'./detailTeam.html\'" />';
var deleteButton = '<input type="button" value="削除" onclick="pushDelete()" />';

var options = {
	valueNames: ['teamId', 'teamName', "companyName", "bossName", "leaderName", "projectName", "memberNum"]
};

var createData = function(row,num) {
	var cell1 = row.insertCell(-1);
	cell1.innerHTML = button;

    var cell2 = row.insertCell(-1);
    cell2.innerHTML = "I" + num;
    $(cell2).addClass("teamId");

    var leaderName = lastNameAry[Math.floor(Math.random() * lastNameAry.length)] + lastNameAry[Math.floor(Math.random() * lastNameAry.length)] + "　" + firstNameAry[Math.floor(Math.random() * firstNameAry.length)] + firstNameAry[Math.floor(Math.random() * firstNameAry.length)];

    var cell3 = row.insertCell(-1);
    cell3.innerHTML = leaderName.substr(0,2) + "チーム"
    $(cell3).addClass("teamName");

	var cell4 = row.insertCell(-1);
    cell4.innerHTML = companyAry[Math.floor(Math.random() * companyAry.length)]
    $(cell4).addClass("companyName");


    var cell5 = row.insertCell(-1);
    cell5.innerHTML = bossAry[Math.floor(Math.random() * bossAry.length)]
    $(cell5).addClass("bossName");


    var cell6 = row.insertCell(-1);
    cell6.innerHTML = leaderName;
    $(cell6).addClass("leaderName");

	var cell7 = row.insertCell(-1);
	cell7.innerHTML = projectName[Math.floor(Math.random() * projectName.length)]
	$(cell7).addClass("projectName");

    var cell8 = row.insertCell(-1);
	cell8.innerHTML =  Math.floor(Math.random() * (20 - 3 + 1)) + 3;
	$(cell8).addClass("memberNum");

    var cell9 = row.insertCell(-1);
	cell9.innerHTML =  deleteButton;

}

var createResultTeam = function() {
	var table = document.getElementById("team");

	for (  var i = 1;  i <= 100;  i++  ) {
		createData(table.insertRow(-1),i);
	}
	 new List('sort', options)
};

var pushDelete = function() {
	if (window.confirm('削除してよろしいですか？')) {
		window.location.href = "./searchTeam.html"
	}
}




