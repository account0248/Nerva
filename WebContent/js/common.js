
/*機能制限 F12(ディベロッパーツール)禁止*/
window.document.onkeydown = function(event){
	/*押下されたキーがF12か確認*/
	if(event.key == "F12"){
		return false;
	};
};

/*機能制限 右クリックメニュー禁止*/
document.oncontextmenu = function () {
	return false;
};


/*ブラウザバック禁止*/
history.pushState(null, null, location.href);
window.addEventListener('popstate', (e) => {
  history.go(1);
});