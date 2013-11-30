/*
 ** begIss :开始期数
 ** begTime :开始时间 
 ** durSellSec：每期销售时间
 ** durWinSec： 每期开奖时间
 ** hmIss : 每天销售多少期
 ** curTime:当前服务器时间
 **
 **
 **
 **
 */
function caculTime(begIss,begTime,durSellSec,durWinSec,hmIss,curTime,issShow,issStat,couDowShow){
	var my_temp = 3;
  var durSec=durSellSec+durWinSec;//10分钟销售+5开奖
  var elapFromBeg=curTime-begTime;//服务器时间与9:00的差
  var issNum = Math.floor(elapFromBeg/durSec);//服务器时间与9:00的差，除以15分钟，加开始期，得到期号
  var issElapSec= elapFromBeg%durSec;//除以15分钟取余数,求得当前期过了多少时间
  var issLeftSec = durSec-issElapSec;//整期还有多少秒
  var midNig=24*60*60;
  var endTime=durSec*hmIss+begTime;
 // alert("glotime:curTime="+curTime+",begin="+begTime+",end="+endTime);
  if(curTime>begTime && curTime<endTime){
	  if(issLeftSec<durWinSec){
	    var winLeftSec=issLeftSec;
	    issStat.innerHTML="正在开奖";
	    cacuCouDow(winLeftSec,couDowShow);
	    //当前期正在开奖
	  }else{
	    var sellLeftSec=issLeftSec-durWinSec;
	    issStat.innerHTML="正在销售";
	    cacuCouDow(sellLeftSec,couDowShow);
	  }
	  issShow.innerHTML=issNum+begIss;
  }else{      
      issShow.innerHTML="";
      issStat.innerHTML="今天停止销售";
  }
}

function cacuCouDow(sec,show){
  var couDowMin=Math.floor(sec/60);
  var couDowSec=sec%60;
  show.innerHTML = formatNum(couDowMin)+":"+formatNum(couDowSec);
}
//差总的期数