var serverTimeParse;

var intBeginDate;
var intBeginIssue;
var intBeginTime;
var intSellSecond;
var intDayIssueTimes;
var intHMIss;

function timeLoad(){
  initPar();
  getServerTimeParse();
  showservertime();
}
function initPar(){
  //alert("initPar");
  intBeginDate=document.all("beginDate").value;
  intBeginIssue=parseInt(document.all("beginIssue").value);
  var intBegH=parseInt(document.all("beginHour").value);
  var intBegM=parseInt(document.all("begmin").value);
  intBeginTime=intBegH*60*60+intBegM*60;
  var intDurSell=parseInt(document.all("sellMin").value);
  intSellSecond=intDurSell*60;
  var intDurWin=parseInt(document.all("winMin").value);
  intDayIssueTimes=intDurWin*60;
  intHMIss=parseInt(document.all("dayIssueTimes").value);
}
function getServerTimeParse()
{//ȡ������ʱ������
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
//��ʾ������ʱ��
function showservertime_old()
{
//	alert("ssc_timerun.js");
	serverTimeParse = serverTimeParse+1000;
	var dateparse = new Date (serverTimeParse);
	var serverYear = dateparse.getFullYear();
	var serverMonth = dateparse.getMonth()+1;
	var serverDate = dateparse.getDate();
	var serverHour = dateparse.getHours();
	var serverMinute = dateparse.getMinutes();
	var serverSecond = dateparse.getSeconds();
	
	var pageTimeShow= document.all("servertime");
    var curIssueWagShow= document.all("currentIssueShow");
    var issueStateShow=document.all("issueStateShow");
    var countDownWagShow= document.all("remainsecond");
	
    //������ʱ��
    var strHMS=formatNum(serverHour)+":"+formatNum(serverMinute)+":"+formatNum(serverSecond);
    var strYMD=serverYear+"-"+formatNum(serverMonth)+"-"+formatNum(serverDate);
    pageTimeShow.innerHTML = serverYear+"��"+formatNum(serverMonth)+"��"+formatNum(serverDate)+"�� "+strHMS;

	var serCurSec=serverHour*60*60+serverMinute*60+serverSecond;
		alert("timerun.js:strYMD="+strYMD+",intBeginDate="+intBeginDate);

	if(strYMD!=intBeginDate){
	  curIssueWagShow.innerHTML="";
	  issueStateShow.innerHTML="����ֹͣ����";
	}else{
		//alert("test");

	  caculTime(intBeginIssue,intBeginTime,intSellSecond,intDayIssueTimes,intHMIss,serCurSec,curIssueWagShow,issueStateShow,countDownWagShow);
    }
	//��Ϊʱʱ��ʱ�����ж���Ͷע�ļ���
    var strPageMark=parent.document.all("pagemark").value;
	//alert("ssc_timerun.js strPageMark=" + strPageMark);

    if(strPageMark=="sxw"){
	  //alert("in ssc");
      restMulIss();
      hasMulIss(intSellSecond);
      hasSelling();
    }
	setTimeout("showservertime()",1000);
	return;
}

//��ʾ������ʱ��
function showservertime(){
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


	//serverTimeParse = Date.parse( new Date(nowYear,nowMonth,nowDay,nowHour,nowMinute,nowSecond));
	serverTimeParse = new Date(nowYear,nowMonth,nowDay,nowHour,nowMinute,nowSecond);
	/*
	//alert(serverTimeParse);
	alert(nowYear+"-"+nowMonth+"-"+nowDay);
	//var dateparse = new Date (serverTimeParse);
	var dateparse = new Date (nowYear,nowMonth,nowDay,nowHour,nowMinute,nowSecond);
	//alert(dateparse);
	var serverYear = dateparse.getFullYear();
	var serverMonth = dateparse.getMonth()-1;
	var serverDate = dateparse.getDate();
	var serverHour = dateparse.getHours();
	var serverMinute = dateparse.getMinutes();
	var serverSecond = dateparse.getSeconds();
	
	alert(serverYear+","+serverMonth+","+serverDate);
	*/
	var pageTimeShow= document.all("servertime");
    var curIssueWagShow= document.all("currentIssueShow");
    var issueStateShow=document.all("issueStateShow");
    var countDownWagShow= document.all("remainsecond");
	
    //������ʱ��
   // var strHMS=formatNum(serverHour)+":"+formatNum(serverMinute)+":"+formatNum(serverSecond);
  //  var strYMD=serverYear+"-"+formatNum(serverMonth)+"-"+formatNum(serverDate);

   
	//alert(strYMD);
  //  pageTimeShow.innerHTML = serverYear+"��"+formatNum(serverMonth)+"��"+formatNum(serverDate)+"�� "+strHMS;
   
	//var serCurSec=serverHour*60*60+serverMinute*60+serverSecond;
	//var serCurSec=nowHour*60*60+nowMinute*60+nowSecond;
    /*
   var offsetFlag= document.all("offsetFlag").value;
	var offsetTime= document.all("offsetTime").value;
	alert(""+offsetFlag+":"+offsetTime);
	var serCurSec=serverHour*60*60+serverMinute*60+serverSecond;//+����ʱ����ǰ10s;-����ʱ���ͺ�
	serCurSec = eval(serCurSec+(offsetFlag + offsetTime));
*/
	var offsetFlag= document.all("offsetFlag").value;
	var offsetTime= document.all("offsetTime").value;
	var serCurSec=nowHour*60*60+nowMinute*60+nowSecond;//+����ʱ����ǰ10s;-����ʱ���ͺ�
	serCurSec = eval(serCurSec+(offsetFlag + offsetTime));



	var strHMS=formatNum(nowHour)+":"+formatNum(nowMinute)+":"+formatNum(nowSecond);
    var strYMD=nowYear+"-"+formatNum(nowMonth)+"-"+formatNum(nowDay);

	pageTimeShow.innerHTML = nowYear+"��"+formatNum(nowMonth)+"��"+formatNum(nowDay)+"�� "+strHMS;

		//alert("timerun.js:strYMD="+strYMD+",intBeginDate="+intBeginDate);
	//alert(strYMD + ",intBeginDate="+intBeginDate);
	if(strYMD!=intBeginDate){
	  curIssueWagShow.innerHTML="";
	  issueStateShow.innerHTML="����ֹͣ����";
	}else{
		//alert("test");

	  caculTime(intBeginIssue,intBeginTime,intSellSecond,intDayIssueTimes,intHMIss,serCurSec,curIssueWagShow,issueStateShow,countDownWagShow);
    }
	//��Ϊʱʱ��ʱ�����ж���Ͷע�ļ���
    var strPageMark=parent.document.all("pagemark").value;
	//alert("ssc_timerun.js strPageMark=" + strPageMark);

    if(strPageMark=="sxw"){
	  //alert("in ssc");
      restMulIss();
      hasMulIss(intSellSecond);
      hasSelling();
    }
	setTimeout("showservertime()",1000);
	return;
}


//׷Ͷʱ���õ���Ӧ��Ͷע������Ӧ������
function getMulIssueValue(){
  var frMulIssue=parent.document.frames['frameZhuitou'];
  var stringIssueTimes="";
  var zhuitouIssueLen=frMulIssue.document.all("zhuitouIssueLen").value;
  for (var i=0;i<zhuitouIssueLen;i++){
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
//����
function restMulIss(){
//	alert("ssc_timerun.js restMulIss");
  var framePlay=parent.document.frames['frameTouzhu'];
  var frameZhuitou=parent.document.frames['frameZhuitou'];
  if(framePlay.document.readyState=='complete' && frameZhuitou.document.readyState=='complete'){
    var subRet=framePlay.document.all("subreturn").value;
	//alert(subRet);
    if(subRet!=""){
      frameZhuitou.location.href="/sxw/sxwPublicInfo.do?method=showSxwZhuitou";
      framePlay.document.all("subreturn").value="";
    }
  }
}

function hasMulIss(intDurSelSecTem){
  //�ж��Ƿ����Ͷע
    var frMulIssue=parent.document.frames['frameZhuitou'];
	//		alert("ssc_timerun.js,hasMulIss,frMulIssue="+frMulIssue);

    if(frMulIssue.document.readyState=='complete'){
	    var curIssueWagShow= document.all("currentIssueShow");
	    if(frMulIssue==undefined){
	        parent.document.all("hidmulissvalue").value=curIssueWagShow.innerHTML; 
		    parent.document.all("hidissuetype").value="singleissue";
	    }else{
	        //��ǰһ��ˢ�¶�����
	        var strDurSelSec=getStrSec(intDurSelSecTem);
		    if(document.all("remainsecond").innerHTML==strDurSelSec){
		      frMulIssue.location.href="/sxw/sxwPublicInfo.do?method=showSxwZhuitou";
		    }
		    //Ϊissue��ֵ
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
function getStrSec(intSelSecTem){
  var intSecTem=intSelSecTem-1;
  var intMin=Math.floor(intSecTem/60);
  var intSec=intSecTem%60;
  var strSec=formatNum(intMin)+":"+formatNum(intSec);
  return strSec;
}
function hasSelling(){
  var issueStateShow=document.all("issueStateShow");
  if(issueStateShow.innerHTML=="��������"){
    parent.document.all("hidselling").value="true";
  }else{
    parent.document.all("hidselling").value="false";
  }
}