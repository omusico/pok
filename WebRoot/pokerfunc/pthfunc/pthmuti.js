var framPlay=parent.document.frames['fraplay'];
var timeFrm = parent.document.frames["fratime"];
	//初始化标志位
//alert(document.all("tempIssuetype").value+","+ document.all("tempGetissuenumber").value);
function switchMulIss(){
  var objStartMulIssCheck=document.all("chestmuliss");
  if(objStartMulIssCheck.checked==true){//点选多期功能后
    switButEnab(true);
	//document.all("mulTimes").disabled=false;
    document.all("curisswagnum").innerHTML=eval(document.all("mulTimes").value*framPlay.document.all("showtotalwager").innerHTML);
    document.all("curissmon").innerHTML=parseInt(eval(document.all("mulTimes").value*framPlay.document.all("showtotalwager").innerHTML))*2;
	//直标志位
	document.all("tempIssuetype").value="muti";
	document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimes").value;

	//parent.document.all("hidissuetype").value="muti";
	//parent.document.all("hidmulissvalue").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimesOld").value;

  }else{//取消后
    switButEnab(false);
	//document.all("mulTimes").disabled=true;
	document.all("mulTimes").value="1";
	document.all("readMulTimes").value="1";
    document.all("curisswagnum").innerHTML=document.all("mulTimes").value;
    document.all("curissmon").innerHTML=parseInt(document.all("mulTimes").value)*2;
	
	//直标志位
	document.all("tempIssuetype").value="singleissue";
	document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML;
	//parent.document.all("hidissuetype").value="singleissue";
	//parent.document.all("hidmulissvalue").value=timeFrm.document.all("currentissueshow").innerHTML;

//	parent.document.all("hidissuetype").value="singleissue";
//	parent.document.all("hidmulissvalue").value="";

  }
}

//页面上的按钮禁止使用
function switButEnab(stat){
  var butPlus=document.all("butplus");
  var butRemove=document.all("butremove");
  var butEmpty=document.all("butempty");

  //倍数按钮
  var mulTimesSub=document.all("mulTimesSub");//减
  var mulTimesPlus=document.all("mulTimesPlus");//加

  if(butPlus!=null &&  butRemove!=null &&  butEmpty!=null ){
    butPlus.disabled=stat;
    butRemove.disabled=stat;
    butEmpty.disabled=stat;
  }
  //加减按钮
  if(mulTimesSub != null && mulTimesPlus != null){
    mulTimesSub.disabled=!stat;
    mulTimesPlus.disabled=!stat;


  }

}
//注数改变时值同时变
function changeVal(){
    document.all("curisswagnum").innerHTML=eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML);
    document.all("curissmon").innerHTML=parseInt(eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML))*2;
		//直标志位
	document.all("tempIssuetype").value="muti";
	document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimes").value;
//	parent.document.all("hidissuetype").value="muti";
//	parent.document.all("hidmulissvalue").value=document.all("markiss").value+"|"+document.all("mulTimesOld").value;
	//parent.document.all("hidissuetype").value="muti";
	//parent.document.all("hidmulissvalue").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimesOld").value;


}
//判断输入是否为数字 
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


	//减
	function sub_sum(){
		var obj = document.getElementById("readMulTimes");
		var val = obj.value;
		if(val == 1){
			alert("不能再减了!");
			return;
		}else{
			obj.value = parseInt(val) - 1;
		}
		//置隐藏值
		document.all("mulTimes").value = obj.value;
		//document.getElementById("curisswagnum").innerHTML = obj.value;
		//document.getElementById("curissmon").innerHTML = parseInt(obj.value)*2;

		document.all("curisswagnum").innerHTML=eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML);
		document.all("curissmon").innerHTML=parseInt(eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML))*2;
		//直标志位
		document.all("tempIssuetype").value="muti";
		document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimes").value;

	}
	//加
	function plus_sum(){
		var obj = document.getElementById("readMulTimes");
		var val = obj.value;
		obj.value = parseInt(val) + 1;
		//置隐藏值
		document.all("mulTimes").value = obj.value;
		//document.getElementById("curisswagnum").innerHTML = obj.value;
		//document.getElementById("curissmon").innerHTML = parseInt(obj.value)*2;

		document.all("curisswagnum").innerHTML=eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML);
		document.all("curissmon").innerHTML=parseInt(eval(document.all("mulTimes").value*document.all("showtotalwager").innerHTML))*2;
		//直标志位
		document.all("tempIssuetype").value="muti";
		document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimes").value;

	}
