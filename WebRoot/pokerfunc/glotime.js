/*
 ** begIss :��ʼ����
 ** begTime :��ʼʱ�� 
 ** durSellSec��ÿ������ʱ��
 ** durWinSec�� ÿ�ڿ���ʱ��
 ** hmIss : ÿ�����۶�����
 ** curTime:��ǰ������ʱ��
 **
 **
 **
 **
 */
function caculTime(begIss,begTime,durSellSec,durWinSec,hmIss,curTime,issShow,issStat,couDowShow){
	var my_temp = 3;
  var durSec=durSellSec+durWinSec;//10��������+5����
  var elapFromBeg=curTime-begTime;//������ʱ����9:00�Ĳ�
  var issNum = Math.floor(elapFromBeg/durSec);//������ʱ����9:00�Ĳ����15���ӣ��ӿ�ʼ�ڣ��õ��ں�
  var issElapSec= elapFromBeg%durSec;//����15����ȡ����,��õ�ǰ�ڹ��˶���ʱ��
  var issLeftSec = durSec-issElapSec;//���ڻ��ж�����
  var midNig=24*60*60;
  var endTime=durSec*hmIss+begTime;
 // alert("glotime:curTime="+curTime+",begin="+begTime+",end="+endTime);
  if(curTime>begTime && curTime<endTime){
	  if(issLeftSec<durWinSec){
	    var winLeftSec=issLeftSec;
	    issStat.innerHTML="���ڿ���";
	    cacuCouDow(winLeftSec,couDowShow);
	    //��ǰ�����ڿ���
	  }else{
	    var sellLeftSec=issLeftSec-durWinSec;
	    issStat.innerHTML="��������";
	    cacuCouDow(sellLeftSec,couDowShow);
	  }
	  issShow.innerHTML=issNum+begIss;
  }else{      
      issShow.innerHTML="";
      issStat.innerHTML="����ֹͣ����";
  }
}

function cacuCouDow(sec,show){
  var couDowMin=Math.floor(sec/60);
  var couDowSec=sec%60;
  show.innerHTML = formatNum(couDowMin)+":"+formatNum(couDowSec);
}
//���ܵ�����