var framPlay=parent.document.frames['fraplay'];
var timeFrm = parent.document.frames["fratime"];
	//��ʼ����־λ
//alert(document.all("tempIssuetype").value+","+ document.all("tempGetissuenumber").value);
function switchMulIss(){
  var objStartMulIssCheck=document.all("chestmuliss");
  if(objStartMulIssCheck.checked==true){//��ѡ���ڹ��ܺ�
    switButEnab(true);
	//document.all("mulTimes").disabled=false;
    document.all("curisswagnum").innerHTML=eval(document.all("mulTimes").value*framPlay.document.all("showtotalwager").innerHTML);
    document.all("curissmon").innerHTML=parseInt(eval(document.all("mulTimes").value*framPlay.document.all("showtotalwager").innerHTML))*2;
	//ֱ��־λ
	document.all("tempIssuetype").value="muti";
	document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimes").value;

	//parent.document.all("hidissuetype").value="muti";
	//parent.document.all("hidmulissvalue").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimesOld").value;

  }else{//ȡ����
    switButEnab(false);
	//document.all("mulTimes").disabled=true;
	document.all("mulTimes").value="1";
	document.all("readMulTimes").value="1";
    document.all("curisswagnum").innerHTML=document.all("mulTimes").value;
    document.all("curissmon").innerHTML=parseInt(document.all("mulTimes").value)*2;
	
	//ֱ��־λ
	document.all("tempIssuetype").value="singleissue";
	document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML;
	//parent.document.all("hidissuetype").value="singleissue";
	//parent.document.all("hidmulissvalue").value=timeFrm.document.all("currentissueshow").innerHTML;

//	parent.document.all("hidissuetype").value="singleissue";
//	parent.document.all("hidmulissvalue").value="";

  }
}

//ҳ���ϵİ�ť��ֹʹ��
function switButEnab(stat){
  var butPlus=document.all("butplus");
  var butRemove=document.all("butremove");
  var butEmpty=document.all("butempty");

  //������ť
  var mulTimesSub=document.all("mulTimesSub");//��
  var mulTimesPlus=document.all("mulTimesPlus");//��

  if(butPlus!=null &&  butRemove!=null &&  butEmpty!=null ){
    butPlus.disabled=stat;
    butRemove.disabled=stat;
    butEmpty.disabled=stat;
  }
  //�Ӽ���ť
  if(mulTimesSub != null && mulTimesPlus != null){
    mulTimesSub.disabled=!stat;
    mulTimesPlus.disabled=!stat;


  }

}
//ע���ı�ʱֵͬʱ��
function changeVal(){
    document.all("curisswagnum").innerHTML=eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML);
    document.all("curissmon").innerHTML=parseInt(eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML))*2;
		//ֱ��־λ
	document.all("tempIssuetype").value="muti";
	document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimes").value;
//	parent.document.all("hidissuetype").value="muti";
//	parent.document.all("hidmulissvalue").value=document.all("markiss").value+"|"+document.all("mulTimesOld").value;
	//parent.document.all("hidissuetype").value="muti";
	//parent.document.all("hidmulissvalue").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimesOld").value;


}
//�ж������Ƿ�Ϊ���� 
 function checkNumeric(value){
    if ( !( ((event.keyCode >= 48) && (event.keyCode <= 57))||event.keyCode==46 ||event.keyCode==45 ) ){
            event.keyCode = 0 ;
    }else if((value.indexOf(".")!=-1)&&event.keyCode==46){
           event.keyCode=0;
    }else if((value.indexOf("-")!=-1)&&event.keyCode==45){
     event.keyCode=0;
    }
   }
function tijiao(){
	var n = document.all("tempIssuetype").value;
	var t = document.all("tempGetissuenumber").value;
	alert(t+","+ n);
	return true;
}


	//��
	function sub_sum(){
		var obj = document.getElementById("readMulTimes");
		var val = obj.value;
		if(val == 1){
			alert("�����ټ���!");
			return;
		}else{
			obj.value = parseInt(val) - 1;
		}
		//������ֵ
		document.all("mulTimes").value = obj.value;
		//document.getElementById("curisswagnum").innerHTML = obj.value;
		//document.getElementById("curissmon").innerHTML = parseInt(obj.value)*2;

		document.all("curisswagnum").innerHTML=eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML);
		document.all("curissmon").innerHTML=parseInt(eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML))*2;
		//ֱ��־λ
		document.all("tempIssuetype").value="muti";
		document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimes").value;

	}
	//��
	function plus_sum(){
		var obj = document.getElementById("readMulTimes");
		var val = obj.value;
		obj.value = parseInt(val) + 1;
		//������ֵ
		document.all("mulTimes").value = obj.value;
		//document.getElementById("curisswagnum").innerHTML = obj.value;
		//document.getElementById("curissmon").innerHTML = parseInt(obj.value)*2;

		document.all("curisswagnum").innerHTML=eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML);
		document.all("curissmon").innerHTML=parseInt(eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML))*2;
		//ֱ��־λ
		document.all("tempIssuetype").value="muti";
		document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimes").value;

	}
