var objFrameTouzhu=parent.document.frames["frameTouzhu"];
var timeFrm = parent.document.frames["fratime"];
 
 
//追投时，选择多期
function switchMulIss(){
  var zhuitouIssueLen=document.all("zhuitouIssueLen").value;
  var objStartMulIssCheck=document.all("chestmuliss");

   if(objStartMulIssCheck.checked==true){//选中后
     switchButtonEnable(true);

    document.all("divIssueTzNum").innerHTML = objFrameTouzhu.document.all("curIssueTzNum").innerHTML;
    document.all("divIssueTzMoney").innerHTML=parseInt(objFrameTouzhu.document.all("curIssueTzNum").innerHTML)*2;
	
  }else{//取消后
    switchButtonEnable(false);
	document.all("divIssueTzNum").innerHTML=0;
    document.all("divIssueTzMoney").innerHTML=0;
  }
  //将剩余的期数置为可选
  for (var i=0;i<zhuitouIssueLen;i++){
    var str="isscheck"+i;
    var strtext="timestext"+i;
	//alert(strtext);
    if(objStartMulIssCheck.checked==true){
      document.all(str).disabled=false;
      if(str=="isscheck0"){
        document.all("isscheck0").disabled=true;
        document.all("isscheck0").checked=true; 
        switchTimesText("0");
      }
    }else{
      document.all(str).disabled=true;
      document.all(str).checked=false; 
      document.all(strtext).value=0;     
      setOtherIssueMoney(0,i);
    }
  }
 
   
}



//为每一期显示注数，金额
function setOtherIssueMoney(times,ind){
  var curIssWagNum=document.all("divIssueTzNum").innerHTML;
  var num="wagnumid"+""+ind;
  var mon="wagmonid"+""+ind;
  document.all(num).innerHTML=curIssWagNum*times;
  document.all(mon).innerHTML=curIssWagNum*times*2;
}

//求多期总注数并显示
function calMulIssWagMon(){
  var totWag=0;
  var issLen=document.all("zhuitouIssueLen").value;
  for(var i=0;i<issLen;i++){
    var num="wagnumid"+""+i;
    totWag=totWag+parseInt(document.all(num).innerHTML);
  }
  document.all("zhuitouTzNum").innerHTML=totWag;
  document.all("zhuitouTzMoney").innerHTML=totWag*2;
}

//设置enable每一期
function switchTimesText(iTemp){
  var str="timestext"+iTemp;
   if(document.all(str).value==0){
    document.all(str).value=1;
    setOtherIssueMoney(1,iTemp);
  }else{
    document.all(str).value=0;
    setOtherIssueMoney(0,iTemp);
  }
  

  calMulIssWagMon();
}
/**追投区的增加倍数**/
 function plusTimes(timesTextIDTemp,ind){
  var objTimesText=document.all(timesTextIDTemp);
  if(parseInt(objTimesText.value)>0){
    objTimesText.value=parseInt(objTimesText.value)+1;
    var timesText=objTimesText.value;
    setOtherIssueMoney(timesText,ind);
  }

  calMulIssWagMon();
}
/**追投区的减少倍数**/
function minusTimes(timesTextIDTemp,ind){
  var objTimesText=document.all(timesTextIDTemp);
  if(parseInt(objTimesText.value)>1){
    objTimesText.value=parseInt(objTimesText.value)-1;  
    var timesText=objTimesText.value;
    setOtherIssueMoney(timesText,ind);
  }

  calMulIssWagMon();
}


//点击追加后，改变页面上其他按钮状态
function switchButtonEnable(stat){
  //+,-,清空按钮
   var buttonPlus=objFrameTouzhu.document.all("buttonPlus");
   var buttonRemove=objFrameTouzhu.document.all("buttonRemove");
  var buttonEmpty=objFrameTouzhu.document.all("buttonEmpty");

  //机选一注/五注/十注
  var ranOne=objFrameTouzhu.document.all("randone");
  var ranFive=objFrameTouzhu.document.all("randfive");
  var ranTen=objFrameTouzhu.document.all("randten");
  
  var objPagemark=parent.document.all("pagemark");
  var objPlaytype=objFrameTouzhu.document.all("playType");
  var objPlaymode=objFrameTouzhu.document.all("playMode");

  if(ranOne!=null && ranFive!=null && ranTen!=null && objPlaymode.value!="复式"){
    ranOne.disabled=stat;
    ranFive.disabled=stat;
    ranTen.disabled=stat;
  }
  if(buttonPlus!=null &&  buttonRemove!=null &&  buttonEmpty!=null){
    buttonPlus.disabled=stat;
    buttonRemove.disabled=stat;
    buttonEmpty.disabled=stat;
  }
}

//页面上的按钮禁止使用
function switButEnab(stat){
  var buttonPlus=document.all("buttonPlus");
  var buttonRemove=document.all("buttonRemove");
  var buttonEmpty=document.all("buttonEmpty");

  //倍数按钮
  var mulTimesSub=document.all("mulTimesSub");//减
  var mulTimesPlus=document.all("mulTimesPlus");//加

  if(buttonPlus!=null &&  buttonRemove!=null &&  buttonEmpty!=null ){
    buttonPlus.disabled=stat;
    buttonRemove.disabled=stat;
    buttonEmpty.disabled=stat;
  }
  //加减按钮
  if(mulTimesSub != null && mulTimesPlus != null){
    mulTimesSub.disabled=!stat;
    mulTimesPlus.disabled=!stat;


  }

}
//注数改变时值同时变
function changeVal(){
    document.all("divIssueTzNum").innerHTML=eval(document.all("mulTimes").value*document.all("curIssueTzNum").innerHTML);
    document.all("divIssueTzMoney").innerHTML=parseInt(eval(document.all("mulTimes").value*document.all("curIssueTzNum").innerHTML))*2;
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
	//alert(t+","+ n);
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
		//document.getElementById("divIssueTzNum").innerHTML = obj.value;
		//document.getElementById("divIssueTzMoney").innerHTML = parseInt(obj.value)*2;

		document.all("divIssueTzNum").innerHTML=eval(document.all("mulTimes").value*document.all("curIssueTzNum").innerHTML);
		document.all("divIssueTzMoney").innerHTML=parseInt(eval(document.all("mulTimes").value*document.all("curIssueTzNum").innerHTML))*2;
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
		//document.getElementById("divIssueTzNum").innerHTML = obj.value;
		//document.getElementById("divIssueTzMoney").innerHTML = parseInt(obj.value)*2;

		document.all("divIssueTzNum").innerHTML=eval(document.all("mulTimes").value*document.all("curIssueTzNum").innerHTML);
		document.all("divIssueTzMoney").innerHTML=parseInt(eval(document.all("mulTimes").value*document.all("curIssueTzNum").innerHTML))*2;
		//直标志位
		document.all("tempIssuetype").value="muti";
		document.all("tempGetissuenumber").value=timeFrm.document.all("currentissueshow").innerHTML+"|"+document.all("mulTimes").value;

	}
