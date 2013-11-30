//创建XMLHttpRequest对象       
function createXMLHttpRequest() {
    var XMLHttpReq;
	if(window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {window.alert("对象创建失败");}
		}
	}
    return XMLHttpReq;
}