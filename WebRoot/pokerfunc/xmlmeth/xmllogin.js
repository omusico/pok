    var subPage="/servlet/login";
    // 注册函数
    function userLogin() {
		var uname = document.formlogin.username.value;
		var psw = document.formlogin.password.value;
		var valc=document.formlogin.valcode.value;
		if(uname=="") {
			document.all("stlogmes").innerHTML="用户名不能为空。";
			document.formlogin.username.focus();
			return false;
		}
		else if(psw=="") {
			document.all("stlogmes").innerHTML="密码不能为空。";
			document.formlogin.password.focus();
			return false;
		}
		/*测试期间，暂时不用注册码
		else if(valc=="") {
			document.all("stlogmes").innerHTML="验证码不能为空。";
			document.formlogin.valcode.focus();
			return false;
		}
		*/
		else{
			sendRequest(subPage+"?username="+ uname + "&password=" + psw+"&valcode="+valc);
		}
	}
	//发送请求函数
	var XMLHttpReq;
	function sendRequest(url) {
		XMLHttpReq=createXMLHttpRequest();
		XMLHttpReq.open("GET", url, true);
		XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		XMLHttpReq.send(null);  // 发送请求
	}
	// 处理返回信息函数
    function processResponse() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res=XMLHttpReq.responseText;
                document.formlogin.username.value="";
                document.formlogin.password.value="";
                document.formlogin.valcode.value="";
                //window.alert(res);
                //refreImg(res);
                window.location.href="login.jsp?loginmes="+res;
            } else { //页面不正常
					//alert("333="+XMLHttpReq.readyState);
                window.location.href="login.jsp?loginmes=您所请求的页面有异常,请刷新后重新登陆";
            }
        }
    }
    
    function refreImg(res){
      window.location.reload();
    }
