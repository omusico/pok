var serverTimeParse;

var strBegDate;
var intBegIss;
var intBegTime;
var intDurSellSec;
var intDurWinSec;
var intHMIss;

function timeLoad(){
  initPar();
  getServerTimeParse();
  showservertime();
}
function initPar(){
  strBegDate=document.all("begdate").value;
  intBegIss=parseInt(document.all("begiss").value);
  var intBegH=parseInt(document.all("beghour").value);
  var intBegM=parseInt(document.all("begmin").value);
  intBegTime=intBegH*60*60+intBegM*60;
  var intDurSell=parseInt(document.all("dursell").value);
  intDurSellSec=intDurSell*60;
  var intDurWin=parseInt(document.all("durwin").value);
  intDurWinSec=intDurWin*60;
  intHMIss=parseInt(document.all("hmiss").value);
}
function getServerTimeParse()
{//取服务器时间后解析
    var sTYear = document.all("stimeyear");
    var sTMonth = document.all("stimemonth");
    var sTDay = document.all("stimeday");
	var sTHour = document.all("stimehour");
	var sTMinute = document.all("stimeminute");
	var sTSecond = document.all("stimesecond");
	var serverTime = new Date(sTYear.value,sTMonth.value,sTDay.value,sTHour.value,sTMinute.value,sTSecond.value);
    serverTimeParse = Date.parse(serverTime);
	return;
}

 
//显示服务器时间
function showservertime()
{
//	alert("ssc_timerun.js");
	//serverTimeParse = serverTimeParse+1000;

	//test....
	 ServerTime.getServerTime(function(serverTime){callShowservertime(serverTime);});
	
}


function callShowservertime(serverTime){

	//alert(serverTime);
	var  nowYear =  serverTime[0];
	var  nowMonth =  serverTime[1];
	var  nowDay =  serverTime[2];
	var  nowHour =  serverTime[3];
	var  nowMinute =  serverTime[4];
	var  nowSecond =  serverTime[5];

/*
	serverTimeParse = Date.parse( new Date(nowYear,nowMonth,nowDay,nowHour,nowMinute,nowSecond));

 	//serverTimeParse = serverTimeParse+1000;
	var dateparse = new Date (serverTimeParse);
	var serverYear = dateparse.getFullYear();
	var serverMonth = dateparse.getMonth();
	var serverDate = dateparse.getDate();
	var serverHour = dateparse.getHours();
	var serverMinute = dateparse.getMinutes();
	var serverSecond = dateparse.getSeconds();
*/

	var serverYear = serverTime[0];
	var serverMonth = serverTime[1];
	var serverDate = serverTime[2];
	var serverHour = serverTime[3];
	var serverMinute = serverTime[4];
	var serverSecond = serverTime[5];
	
	var pageTimeShow= document.all("servertime");
    var curIssueWagShow= document.all("currentissueshow");
    var issStatShow=document.all("issstatshow");
    var countDownWagShow= document.all("remainsecond");
	var frMulIssue=parent.document.frames['framulissue'];
    //服务器时间
    var strHMS=formatNum(serverHour)+":"+formatNum(serverMinute)+":"+formatNum(serverSecond);
    var strYMD=serverYear+""+formatNum(serverMonth)+""+formatNum(serverDate);
    pageTimeShow.innerHTML = serverYear+"年"+formatNum(serverMonth)+"月"+formatNum(serverDate)+"日 "+strHMS;

	//var serCurSec=serverHour*60*60+serverMinute*60+serverSecond;
	//alert("lautimerun:strYMD="+strYMD+",strBegDate="+strBegDate);
	var offsetFlag= document.all("offsetFlag").value;
	var offsetTime= document.all("offsetTime").value;
	var serCurSec=serverHour*60*60+serverMinute*60+serverSecond;;//+销售时间提前10s;-销售时间滞后
	serCurSec = eval(serCurSec+(offsetFlag + offsetTime));



	if(strYMD!=strBegDate){
	  curIssueWagShow.innerHTML="";
	  issStatShow.innerHTML="今天停止销售";
	}else{
	  caculTime(intBegIss,intBegTime,intDurSellSec,intDurWinSec,intHMIss,serCurSec,curIssueWagShow,issStatShow,countDownWagShow);
    }
    var strPageMark=parent.document.all("pagemark").value;
	//时时乐
    if(strPageMark=="laugh"){
      restMulIss();
      hasMulIss();
      hasSelling();
	  //刷新左侧显示号码
      //refreWinNum(countDownWagShow.innerHTML);alter by ljy on 2009-5-25
	  var framTime=parent.document.frames['fratime'];
      refreWinNum(framTime.document.all("remainsecond").innerHTML);

    }
	setTimeout("showservertime()",1000);
	return;
}
function getMulIssueValue(){
  var frMulIssue=parent.document.frames['framulissue'];
  var stringIssueTimes="";
  var issueLength=frMulIssue.document.all("issuelength").value;
  for (var i=0;i<issueLength;i++){
    var str="isscheck"+i;
    var objIssCheck=frMulIssue.document.all(str);
    if(objIssCheck.checked==true){
      var strIssTd="isstd"+i;
      var ValueIssTd=frMulIssue.document.all(strIssTd).innerText;
      var strTimesText="timestext"+i;
      var ValueTimesText=frMulIssue.document.all(strTimesText).value
      var oneIssueTimes=ValueIssTd+"|"+ValueTimesText;
      if(stringIssueTimes!=""){
        stringIssueTimes=stringIssueTimes+"$";
      }
      stringIssueTimes=stringIssueTimes+oneIssueTimes;
    }
  }
  return stringIssueTimes;
}

function refreWinNum(strSec){
  
  //if(strSec=="10:00"){
  if(strSec=="00:01"){
    parent.document.frames['frawinnumshow'].location.href="/servlet/winnumshow?play=laugh";
  }
}

function restMulIss(){
  var framPlay=parent.document.frames['fraplay'];
  var frMulIssue=parent.document.frames['framulissue'];
  if(framPlay.document.readyState=='complete' && frMulIssue.document.readyState=='complete'){
    var subRet=framPlay.document.all("subreturn").value;
    if(subRet!=""){
      frMulIssue.location.href="/servlet/servmulissue?play=laugh";
      framPlay.document.all("subreturn").value="";
    }
  }
}

function hasMulIss(){
	//alert("ok");
    var frMulIssue=parent.document.frames['framulissue'];
    if(frMulIssue.document.readyState=='complete'){
	    var curIssueWagShow= document.all("currentissueshow");
	    if(frMulIssue==undefined){
	        parent.document.all("hidmulissvalue").value=curIssueWagShow.innerHTML; 
		    parent.document.all("hidissuetype").value="singleissue";
	    }else{
		    if(document.all("remainsecond").innerHTML=="00:01"){
		      frMulIssue.location.href="/servlet/servmulissue?play=laugh";
		    }
		    //为issue赋值
		    var objCheStMulIss=frMulIssue.document.all("chestmuliss");
		    parent.document.all("hidmarkiss").value=curIssueWagShow.innerHTML;
		    if(objCheStMulIss.checked==false){
		      parent.document.all("hidmulissvalue").value=curIssueWagShow.innerHTML; 
		      parent.document.all("hidissuetype").value="singleissue";
		    }else{
		      parent.document.all("hidmulissvalue").value=getMulIssueValue();
		      parent.document.all("hidissuetype").value="mulissue";
		    }
	    }
    }
}

function hasSelling(){
  var issStatShow=document.all("issstatshow");
  if(issStatShow.innerHTML=="正在销售"){
    parent.document.all("hidselling").value="true";
  }else{
    parent.document.all("hidselling").value="false";
  }
}