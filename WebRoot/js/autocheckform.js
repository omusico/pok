/**
 * Title: 用于自动检查表单的输入是否符合要求的JS文件
 * Description:
 	1.使用方法(注意字母大小写)
 	在你要检查的控件上加上属性
		CK_TYPE 	要检查的类型，多种类型可以用逗号组合起来
		CK_NAME 	出错时，显示的出错字段名
		CK_MSG_XXXX  定制的出错信息,XXXX表示可以是任意要检查的类型

	举例(必须一次包含下列js文件):
	<SCRIPT language=javascript src="include/js/trimspace.js"></script>
	<Script language="JavaScript"  src="include/js/checkform.js"></Script>
	<Script language="JavaScript"  src="include/js/autocheckform.js"></Script>
	input name="postcode"  CK_TYPE="NotEmpty,Number,PostCode" CK_NAME="邮政编码"
	CK_MSG_NotEmpty = "邮政编码是无论如何都不可以为空的，请重新输入!"

	目前可以使用的类型有:
	NotEmpty		非空		
	Number			数字		0-9
	Int		    	数字    	1为是数字，0为不是数字
	Date			日期		验证格式为：yyyy_mm_dd	
	Double			 双精度数（整数也可）
	Pselect      	请选择	
	EMail			EMail地址
	Money        	货币		
	Postcode		邮政编码		6位数字
	Telphone		电话号码		号码中可含有:0123456789-()# 
    MobileNO  		手机号码
	NoSpace			不含空格
	Len_X			字符串长度要求为X,例如:Len_2 表示要求长度为2
    Float           可带小数点的数字。
    FloatNew        可带小数点的数字，可以是负数。
	MaxLen_X		字符串长度要求最大为X,例如:MaxLen_2 表示要求长度最大为2
    MinLen_X		字符串长度要求最小为X,例如:MinLen_2 表示要求长度最小为2
  	如何修订和改进本文件:
	如果要添加新的类型，请在函数autoCheckForm(objForm)中添加相关的处理代码,并在上面的可用类型
	列表中给出说明即可
*/

/**
*自动检查表单的输入是否符合要求
*@param objForm 要检查的窗体
*/
function  autoCheckForm(objForm){
	var i;
	for (i=0;i<objForm.elements.length;i++) {	//遍历表单所有元素
		var objCheckItem;						//要检查的项
		var strItemCKType;						//要检查的格式列表，用逗号分隔
		var defTipMsg;							//自定义提示消息
		objCheckItem = objForm.elements[i];
		strItemCKType = objCheckItem.getAttribute("CK_TYPE") + ",";
		if (strItemCKType != null && strItemCKType != "undefined") {
			var iPosBegin = 0;
			var iPosEnd = strItemCKType.indexOf(",",iPosBegin);
			while (iPosEnd > 0) {				//遍历所有该字段要检查的格式要求
	            var  sCKType ;
	            sCKType = strItemCKType.substr(iPosBegin,iPosEnd - iPosBegin);
	            var sCKName = objCheckItem.getAttribute("CK_NAME");	
	           
	            //验证特殊字符开始 add by kongfanyu
	            //var objValue = objCheckItem.value;
	            //if(objValue != null && objValue != ""){
				            	//var iCharpos = objValue.search("['/\\\\|\"<>`~\^]");
								//if(iCharpos >= 0){
									//alert("输入的内容含有非法字符 '/\\|\"<>`~\^");
									//objCheckItem.focus();
									//return false;
								//}
	            //}
	            //验证特殊字符结束
	            
	            //默认提示的名字
	            //alert(sCKType);
				switch(sCKType){
					case "NotEmpty":
						if(JsTrim(objCheckItem.value).length <=0) {
							defTipMsg = objCheckItem.getAttribute("CK_MSG_NotEmpty");
		                	if (defTipMsg != null) {
		                    	alert(objCheckItem.CK_MSG_NotEmpty);
		                    }else {
		                    	alert(sCKName + "不能为空!");
							}
		                    objCheckItem.focus();
		                    return false;
						}
						break;
					case "Number":
						if (objCheckItem.value!="" && fucCheckNUM(objCheckItem.value) == 0) {
							defTipMsg = objCheckItem.getAttribute("CK_MSG_Number");
							if (defTipMsg != null) {
	                        	alert(defTipMsg);
	                      	}else {
	                      		alert(sCKName + "必须是半角数字!");
	                      	}
							objCheckItem.select();
							return false;
	                  	}
	                  	break;
	                case "Int":
	                	if (objCheckItem.value!=""&&fucCheckInt(objCheckItem.value) == 0) {
	                		defTipMsg = objCheckItem.getAttribute("CK_MSG_Int");
	                		if (defTipMsg != null) {
	                        	alert(defTipMsg);
	                      	}else {
	                        	alert(sCKName + "必须是整数!");
	                      	}
	                      	objCheckItem.select();
	                      	return false;
	                  	}
	                  	break;
	                case "Money":
	                	if (objCheckItem.value!="" && checkMoney(objCheckItem.value) == -1) {
	                		defTipMsg = objCheckItem.getAttribute("CK_MSG_Money");
                      		if (defTipMsg != null) {
                            	alert(defTipMsg);
                      		}else {
								alert(sCKName + "填写有误!");
                      		}
                      		objCheckItem.select();
                      		return false;
                  		}
	                	break;
	                case "Double":
	                	if (objCheckItem.value!="" && fucCheckDouble(objCheckItem.value) == 0) {
	                		defTipMsg = objCheckItem.getAttribute("CK_MSG_Double");
                        	if (defTipMsg != null) {
                            	 alert(defTipMsg);
                        	} else {
                            	alert(sCKName + "必须是小数!");
                        	}
                       		objCheckItem.select();
                   			return false;
                      	}
                      	break;
	                case "Float":
	                	if(objCheckItem.value!="" && objCheckItem.value!=null){
	                		defTipMsg = objCheckItem.getAttribute("CK_MSG_Float");
	                		if (chknbr(objCheckItem.value,1,0) == 0) {
	                			if (defTipMsg != null) {
									alert(defTipMsg);
			                	} else {
			                    	alert(sCKName + "必须是数字!");
			             		}
								objCheckItem.select();
								return false;
							}
						}
						break;
					case "Date":
	                	if(objCheckItem.value!="" && chkdate(objCheckItem.value) == 0){
	                		defTipMsg = objCheckItem.getAttribute("CK_MSG_Date");
							if (objCheckItem.value!="") {
	                			if (defTipMsg != null) {
									alert(defTipMsg);
			                	} else {
			                    	alert(sCKName + "的格式无效!正确格式：YYYY-MM-DD!");
			             		}
								objCheckItem.select();
								return false;
							}
						}
						break;
					case "English":
	                	if(isEnglish(objCheckItem.value)==0){
	                		defTipMsg = objCheckItem.getAttribute("CK_MSG_English");
							if (objCheckItem.value!=""&&chkdate(objCheckItem.value) == 0) {
	                			if (defTipMsg != null) {
									alert(defTipMsg);
			                	} else {
			                    	alert(sCKName + "只能含有英文字母和数字!");
			             		}
								objCheckItem.select();
								return false;
							}
						}
						break;
					case "Pselect":
						if(isEnglish(objCheckItem.value)==0){
							defTipMsg = objCheckItem.getAttribute("CK_MSG_Pselect");
							if (objCheckItem.value == "") {
								if (defTipMsg != null) {
									alert(defTipMsg);
								} else {
									alert("请选择"+sCKName);
								}
								objCheckItem.focus();
								return false;
							}
						}
						break;
					case "EMail":
						if(objCheckItem.value!="" && chkemail(objCheckItem.value) == 0){
							defTipMsg = objCheckItem.getAttribute("CK_MSG_EMail");
							if (defTipMsg != null) {
								alert(defTipMsg);
							} else {
								alert(sCKName + "的格式无效!正确格式：\n1. 必须有@和.符号\n2. 不能包含空格且@前至少要有三位字符");
							}
							objCheckItem.select();
							return false;
						}
						break;
					case "Postcode":
						if(objCheckItem.value!="" && fucCheckPostcode(objCheckItem.value) == 0){
							defTipMsg = objCheckItem.getAttribute("CK_MSG_Postcode");
							if (defTipMsg != null) {
								alert(defTipMsg);
							} else {
								alert(sCKName + "的格式无效!");
							}
							objCheckItem.select();
							return false;
						}
						break;
					case "Telphone":
						if(objCheckItem.value!="" && fucCheckTEL(objCheckItem.value)==0){
							defTipMsg = objCheckItem.getAttribute("CK_MSG_Telphone");
							if (defTipMsg != null) {
								alert(defTipMsg);
							} else {
								alert(sCKName + "的格式无效!");
							}
							objCheckItem.select();
							return false;
						}
						break;
					case "MobileNO":
						if(objCheckItem.value!="" && fucCheckNUM(objCheckItem.value)==0){
							defTipMsg = objCheckItem.getAttribute("CK_MSG_MobileNO");
							if (defTipMsg != null) {
								alert(defTipMsg);
							} else {
								alert(sCKName + "的格式无效!");
							}
							objCheckItem.select();
							return false;
						}
						break;
					case "NoSpace":
						if(chkspc(objCheckItem.value) == 0){
							defTipMsg = objCheckItem.getAttribute("CK_MSG_NoSpace");
							if (defTipMsg != null) {
										alert(defTipMsg);
							} else {
								alert(sCKName + "不能含有空格符!");
							}
							objCheckItem.select();
							return false;
						}
						break;
					case "FloatNew":
						if(fucCheckFloat(objCheckItem.value) == 0){
							defTipMsg = objCheckItem.getAttribute("CK_MSG_Float");
							if (defTipMsg != null) {
								alert(defTipMsg);
							} else {
								alert(sCKName + "格式无效!");
							}
							objCheckItem.select();
							return false;
						}
						break;
					default:
						if(sCKType.indexOf("MaxLen_") >=0){
							var iLen;
							iLen = sCKType.substr(sCKType.indexOf("_")+1);
							defTipMsg = objCheckItem.getAttribute("CK_MSG_MaxLen_X");
							if (objCheckItem.value.length > iLen) {
								if (defTipMsg != null) {
									alert(defTipMsg);
								} else {
									alert(sCKName + "长度最大为" + iLen + "!");
								}
								objCheckItem.select();
								return false;
							}
						}else if(sCKType.indexOf("MinLen_") >=0){
							var iLen;
							iLen = sCKType.substr(sCKType.indexOf("_")+1);
							defTipMsg = objCheckItem.getAttribute("CK_MSG_MixLen_X");
							if (objCheckItem.value.length < iLen) {
								if (defTipMsg != null) {
									alert(defTipMsg);
								}else {
									alert(sCKName + "长度最小为： " + iLen + "  ！  ");
								}
								objCheckItem.select();
								return false;
							}
						}else if(sCKType.indexOf("Len_") >=0){
							var iLen;
							iLen = sCKType.substr(sCKType.indexOf("_")+1);
							defTipMsg = objCheckItem.getAttribute("CK_MSG_Len_X");
							if (objCheckItem.value.length != iLen) {
								if (defTipMsg != null) {
									alert(defTipMsg);
								}else {
									alert(sCKName + "长度不满足要求!");
								}
								objCheckItem.select();
								return false;
							}
						}else if(sCKType == null){
							alert(sCKType + "没有定义此类型的检查函数！");
							objCheckItem.select();
							return false;
						}
						break;
				}
				iPosBegin =  iPosEnd +1;
              	iPosEnd = strItemCKType.indexOf(",",iPosBegin)
			}
		}
	}
}