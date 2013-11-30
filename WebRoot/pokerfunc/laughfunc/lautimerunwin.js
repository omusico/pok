
var timeOutFade=100;
var backTimeOut=10;

var winTimeRunMark=null;
var timeOutMark=null;
var backTimeOutMark=null;

var spadeRunFinish=false;
var heartRunFinish=false;
var clubRunFinish=false;
var diamondRunFinish=false;
var halfFinish=false;

var backRunStart=true;

function movieLoad(){
    winTimeRun();
	
}

function winTimeRun(){
	var framTime=parent.document.frames['fratime'];
	
    if(framTime.document.readyState=='complete'){
	    if(framTime.document.all("remainsecond").innerHTML==""){
	     // clearShowPr();
	    }else if(framTime.document.all("issstatshow").innerHTML=="正在开奖"){

	      if(framTime.document.all("remainsecond").innerHTML=="00:30"){
		    //document.location.href="servlet/startwinmovie";
			//清空中奖信息
			  document.all("hundredWin").innerHTML="";
			  document.all("tenWin").innerHTML="";
			  document.all("oneWin").innerHTML="";
			  getLauNum();
			  
		  }
		
		  if(framTime.document.all("remainsecond").innerHTML=="00:01"){
			  document.all("issuewinresult").innerHTML=document.getElementById("hidMaxLauIssNum").value;
			  document.all("hundredWin").innerHTML=document.getElementById("hidHundred").value;
			  document.all("tenWin").innerHTML=document.getElementById("hidTen").value;
			  document.all("oneWin").innerHTML=document.getElementById("hidOne").value;
		  }
	    }
    }
    //winTimeRunMark=setTimeout("winTimeRun()",1000);
	setTimeout("winTimeRun()",1000);
}


