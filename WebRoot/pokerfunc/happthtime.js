var serverTimeParse;

var strBegDate;
var intBegIss;
var intBegTime;
var intDurSellSec;
var intDurWinSec;
var intEndTime;

function timeLoad(){
  initPar();
  getServerTimeParse();
  showservertime();
}
function initPar(){
 /* strBegDate=document.all("begdate").value;
  intBegIss=parseInt(document.all("begiss").value);
  var intBegH=parseInt(document.all("beghour").value);
  var intBegM=parseInt(document.all("begmin").value);
  var intHowManyIss = parseInt(document.all("howmanyiss").value);//add by ljy on 2009-6-3 第天销售多少期
  intBegTime=intBegH*60*60+intBegM*60;
//  alert("begin h="+intBegH+",m="+intBegM);
  var intDurSell=parseInt(document.all("dursell").value);
  //intDurSellSec=intDurSell*60;
  intDurSellSec=intDurSell*60*intHowManyIss;//add by ljy on 2009-6-3 乘以销售期数

  var intDurWin=parseInt(document.all("durwin").value);
  //intDurWinSec=intDurWin*60;
  intDurWinSec=intDurWin*60*intHowManyIss;//add by ljy on 2009-6-3 乘以销售期数

  intEndTime=intDurSellSec+intDurWinSec+intBegTime;
//  alert("end ="+intDurWin+","+intDurSell+","+intBegTime);
*/
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

function showservertime_old()
{
	serverTimeParse = serverTimeParse+1000;
	var dateparse = new Date (serverTimeParse);
	var serverYear = dateparse.getFullYear();
	var serverMonth = dateparse.getMonth()+1;
	var serverDate = dateparse.getDate();
	var serverHour = dateparse.getHours();
	var serverMinute = dateparse.getMinutes();
	var serverSecond = dateparse.getSeconds();
	
	var pageTimeShow= document.all("servertime");
    var curIssueWagShow= document.all("currentissueshow");
    var issStatShow=document.all("issstatshow");

    var countDownWagShow= document.all("remainsecond");
	var frMulIssue=parent.document.frames['framulissue'];

    //服务器时间
    var strHMS=formatNum(serverHour)+":"+formatNum(serverMinute)+":"+formatNum(serverSecond);
    var strYMD=serverYear+""+formatNum(serverMonth)+""+formatNum(serverDate);
    pageTimeShow.innerHTML = serverYear+"年"+formatNum(serverMonth)+"月"+formatNum(serverDate)+"日 "+strHMS;


	var serCurSec=serverHour*60*60+serverMinute*60+serverSecond;
	//alert("cur h="+serverHour+",m="+serverMinute);
	//alert("strYMD="+strYMD+",strBegDate="+strBegDate);
//	alert("serCurSec="+serCurSec+",intBegTime="+intBegTime+",intEndTime="+intEndTime);
	if(strYMD!=strBegDate){
	  curIssueWagShow.innerHTML="";
	  issStatShow.innerHTML="今天停止销售";
	/*
	}else if(serCurSec>intBegTime && serCurSec<intEndTime){
	  curIssueWagShow.innerHTML=intBegIss;
	  issStatShow.innerHTML="正在销售";
	}else{
	  curIssueWagShow.innerHTML="";
	  issStatShow.innerHTML="今天停止销售";
	  */
	}else{
	  caculTime(intBegIss,intBegTime,intDurSellSec,intDurWinSec,intHMIss,serCurSec,curIssueWagShow,issStatShow,countDownWagShow);
    }
	
	var strPageMark=parent.document.all("pagemark").value;
	if(strPageMark=="happy" || strPageMark=="pth"){
	  //  parent.document.all("hidmarkiss").value=curIssueWagShow.innerHTML;
	 //   parent.document.all("hidmulissvalue").value=curIssueWagShow.innerHTML; 
	 //   parent.document.all("hidissuetype").value="singleissue";
	//	restMulIss();
      hasMulIss();
      hasSelling();
	    hasSelling();
	    refreWinNum(strHMS);
    }

	setTimeout("showservertime()",1000);
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


	serverTimeParse = Date.parse( new Date(nowYear,nowMonth,nowDay,nowHour,nowMinute,nowSecond));

	//serverTimeParse = serverTimeParse+1000;
	var dateparse = new Date (serverTimeParse);
	var serverYear = dateparse.getFullYear();
	var serverMonth = dateparse.getMonth();
	var serverDate = dateparse.getDate();
	var serverHour = dateparse.getHours();
	var serverMinute = dateparse.getMinutes();
	var serverSecond = dateparse.getSeconds();
	
	var pageTimeShow= document.all("servertime");
    var curIssueWagShow= document.all("currentissueshow");
    var issStatShow=document.all("issstatshow");

    var countDownWagShow= document.all("remainsecond");
	var frMulIssue=parent.document.frames['framulissue'];

    //服务器时间
    var strHMS=formatNum(serverHour)+":"+formatNum(serverMinute)+":"+formatNum(serverSecond);
    var strYMD=serverYear+""+formatNum(serverMonth)+""+formatNum(serverDate);
    pageTimeShow.innerHTML = serverYear+"年"+formatNum(serverMonth)+"月"+formatNum(serverDate)+"日 "+strHMS;


	var serCurSec=serverHour*60*60+serverMinute*60+serverSecond;
 	if(strYMD!=strBegDate){
	  curIssueWagShow.innerHTML="";
	  issStatShow.innerHTML="今天停止销售";
 
	}else{
	  caculTime(intBegIss,intBegTime,intDurSellSec,intDurWinSec,intHMIss,serCurSec,curIssueWagShow,issStatShow,countDownWagShow);
    }
	
	var strPageMark=parent.document.all("pagemark").value;
	if(strPageMark=="happy" || strPageMark=="pth"){
       hasMulIss();
		hasSelling();
	    hasSelling();
	    refreWinNum(strHMS);
    }

	setTimeout("showservertime()",1000);
	return;
}


function hasSelling(){
  var issStatShow=document.all("issstatshow");
  if(issStatShow.innerHTML=="正在销售"){
    parent.document.all("hidselling").value="true";
  }else{
    parent.document.all("hidselling").value="false";
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
    var frMulIssue=parent.document.frames['fratime'];
    if(frMulIssue.document.readyState=='complete'){
	    var curIssueWagShow= frMulIssue.document.all("currentissueshow");
	    if(frMulIssue=="undefined"){
	        parent.document.all("hidmulissvalue").value=curIssueWagShow.innerHTML; 
		    parent.document.all("hidissuetype").value="singleissue";
	    }else{
		    if(document.all("remainsecond").innerHTML=="00:01"){
		     // frMulIssue.location.href="/servlet/servmulissue?play=laugh";
		    }
			var frmPlay=parent.document.frames['fraplay'];
			if(frmPlay.document.readyState=='complete'){
				//为issue赋值
				var objCheStMulIss=frmPlay.document.all("chestmuliss");
				parent.document.all("hidmarkiss").value=curIssueWagShow.innerHTML;
			
				if(!objCheStMulIss.checked){
				  parent.document.all("hidmulissvalue").value=curIssueWagShow.innerHTML; 
				  parent.document.all("hidissuetype").value="singleissue";
				  
				//	alert("2"+parent.document.all("hidmulissvalue").value);
				}else{
				  parent.document.all("hidmulissvalue").value=curIssueWagShow.innerHTML; 
				  parent.document.all("hidissuetype").value="mulissue";
				//	alert("3"+parent.document.all("hidmulissvalue").value);
				}
			}
	    }
    }
}
