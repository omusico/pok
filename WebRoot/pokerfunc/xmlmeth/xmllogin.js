    var subPage="/servlet/login";
    // ע�ắ��
    function userLogin() {
		var uname = document.formlogin.username.value;
		var psw = document.formlogin.password.value;
		var valc=document.formlogin.valcode.value;
		if(uname=="") {
			document.all("stlogmes").innerHTML="�û�������Ϊ�ա�";
			document.formlogin.username.focus();
			return false;
		}
		else if(psw=="") {
			document.all("stlogmes").innerHTML="���벻��Ϊ�ա�";
			document.formlogin.password.focus();
			return false;
		}
		/*�����ڼ䣬��ʱ����ע����
		else if(valc=="") {
			document.all("stlogmes").innerHTML="��֤�벻��Ϊ�ա�";
			document.formlogin.valcode.focus();
			return false;
		}
		*/
		else{
			sendRequest(subPage+"?username="+ uname + "&password=" + psw+"&valcode="+valc);
		}
	}
	//����������
	var XMLHttpReq;
	function sendRequest(url) {
		XMLHttpReq=createXMLHttpRequest();
		XMLHttpReq.open("GET", url, true);
		XMLHttpReq.onreadystatechange = processResponse;//ָ����Ӧ����
		XMLHttpReq.send(null);  // ��������
	}
	// ��������Ϣ����
    function processResponse() {
    	if (XMLHttpReq.readyState == 4) { // �ж϶���״̬
        	if (XMLHttpReq.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res=XMLHttpReq.responseText;
                document.formlogin.username.value="";
                document.formlogin.password.value="";
                document.formlogin.valcode.value="";
                //window.alert(res);
                //refreImg(res);
                window.location.href="login.jsp?loginmes="+res;
            } else { //ҳ�治����
					//alert("333="+XMLHttpReq.readyState);
                window.location.href="login.jsp?loginmes=���������ҳ�����쳣,��ˢ�º����µ�½";
            }
        }
    }
    
    function refreImg(res){
      window.location.reload();
    }
