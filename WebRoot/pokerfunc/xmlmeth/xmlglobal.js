//����XMLHttpRequest����       
function createXMLHttpRequest() {
    var XMLHttpReq;
	if(window.XMLHttpRequest) { //Mozilla �����
		XMLHttpReq = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) { // IE�����
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {window.alert("���󴴽�ʧ��");}
		}
	}
    return XMLHttpReq;
}