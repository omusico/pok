/**
 * Title: �����Զ������������Ƿ����Ҫ���JS�ļ�
 * Description:
 	1.ʹ�÷���(ע����ĸ��Сд)
 	����Ҫ���Ŀؼ��ϼ�������
		CK_TYPE 	Ҫ�������ͣ��������Ϳ����ö����������
		CK_NAME 	����ʱ����ʾ�ĳ����ֶ���
		CK_MSG_XXXX  ���Ƶĳ�����Ϣ,XXXX��ʾ����������Ҫ��������

	����(����һ�ΰ�������js�ļ�):
	<SCRIPT language=javascript src="include/js/trimspace.js"></script>
	<Script language="JavaScript"  src="include/js/checkform.js"></Script>
	<Script language="JavaScript"  src="include/js/autocheckform.js"></Script>
	input name="postcode"  CK_TYPE="NotEmpty,Number,PostCode" CK_NAME="��������"
	CK_MSG_NotEmpty = "����������������ζ�������Ϊ�յģ�����������!"

	Ŀǰ����ʹ�õ�������:
	NotEmpty		�ǿ�		
	Number			����		0-9
	Int		    	����    	1Ϊ�����֣�0Ϊ��������
	Date			����		��֤��ʽΪ��yyyy_mm_dd	
	Double			 ˫������������Ҳ�ɣ�
	Pselect      	��ѡ��	
	EMail			EMail��ַ
	Money        	����		
	Postcode		��������		6λ����
	Telphone		�绰����		�����пɺ���:0123456789-()# 
    MobileNO  		�ֻ�����
	NoSpace			�����ո�
	Len_X			�ַ�������Ҫ��ΪX,����:Len_2 ��ʾҪ�󳤶�Ϊ2
    Float           �ɴ�С��������֡�
    FloatNew        �ɴ�С��������֣������Ǹ�����
	MaxLen_X		�ַ�������Ҫ�����ΪX,����:MaxLen_2 ��ʾҪ�󳤶����Ϊ2
    MinLen_X		�ַ�������Ҫ����СΪX,����:MinLen_2 ��ʾҪ�󳤶���СΪ2
  	����޶��͸Ľ����ļ�:
	���Ҫ����µ����ͣ����ں���autoCheckForm(objForm)�������صĴ������,��������Ŀ�������
	�б��и���˵������
*/

/**
*�Զ������������Ƿ����Ҫ��
*@param objForm Ҫ���Ĵ���
*/
function  autoCheckForm(objForm){
	var i;
	for (i=0;i<objForm.elements.length;i++) {	//����������Ԫ��
		var objCheckItem;						//Ҫ������
		var strItemCKType;						//Ҫ���ĸ�ʽ�б��ö��ŷָ�
		var defTipMsg;							//�Զ�����ʾ��Ϣ
		objCheckItem = objForm.elements[i];
		strItemCKType = objCheckItem.getAttribute("CK_TYPE") + ",";
		if (strItemCKType != null && strItemCKType != "undefined") {
			var iPosBegin = 0;
			var iPosEnd = strItemCKType.indexOf(",",iPosBegin);
			while (iPosEnd > 0) {				//�������и��ֶ�Ҫ���ĸ�ʽҪ��
	            var  sCKType ;
	            sCKType = strItemCKType.substr(iPosBegin,iPosEnd - iPosBegin);
	            var sCKName = objCheckItem.getAttribute("CK_NAME");	
	           
	            //��֤�����ַ���ʼ add by kongfanyu
	            //var objValue = objCheckItem.value;
	            //if(objValue != null && objValue != ""){
				            	//var iCharpos = objValue.search("['/\\\\|\"<>`~\^]");
								//if(iCharpos >= 0){
									//alert("��������ݺ��зǷ��ַ� '/\\|\"<>`~\^");
									//objCheckItem.focus();
									//return false;
								//}
	            //}
	            //��֤�����ַ�����
	            
	            //Ĭ����ʾ������
	            //alert(sCKType);
				switch(sCKType){
					case "NotEmpty":
						if(JsTrim(objCheckItem.value).length <=0) {
							defTipMsg = objCheckItem.getAttribute("CK_MSG_NotEmpty");
		                	if (defTipMsg != null) {
		                    	alert(objCheckItem.CK_MSG_NotEmpty);
		                    }else {
		                    	alert(sCKName + "����Ϊ��!");
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
	                      		alert(sCKName + "�����ǰ������!");
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
	                        	alert(sCKName + "����������!");
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
								alert(sCKName + "��д����!");
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
                            	alert(sCKName + "������С��!");
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
			                    	alert(sCKName + "����������!");
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
			                    	alert(sCKName + "�ĸ�ʽ��Ч!��ȷ��ʽ��YYYY-MM-DD!");
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
			                    	alert(sCKName + "ֻ�ܺ���Ӣ����ĸ������!");
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
									alert("��ѡ��"+sCKName);
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
								alert(sCKName + "�ĸ�ʽ��Ч!��ȷ��ʽ��\n1. ������@��.����\n2. ���ܰ����ո���@ǰ����Ҫ����λ�ַ�");
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
								alert(sCKName + "�ĸ�ʽ��Ч!");
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
								alert(sCKName + "�ĸ�ʽ��Ч!");
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
								alert(sCKName + "�ĸ�ʽ��Ч!");
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
								alert(sCKName + "���ܺ��пո��!");
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
								alert(sCKName + "��ʽ��Ч!");
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
									alert(sCKName + "�������Ϊ" + iLen + "!");
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
									alert(sCKName + "������СΪ�� " + iLen + "  ��  ");
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
									alert(sCKName + "���Ȳ�����Ҫ��!");
								}
								objCheckItem.select();
								return false;
							}
						}else if(sCKType == null){
							alert(sCKType + "û�ж�������͵ļ�麯����");
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