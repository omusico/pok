
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
	//alert(framTime);
	//alert(":"+framTime.document.all("issueStateShow").innerHTML+";");
	//alert(":"+framTime.document.all("remainsecond").innerHTML+";");
    if(framTime.document.readyState=='complete'){
	    if(framTime.document.all("remainsecond").innerHTML==""){
			//alert("1111111111");
	      //clearShowPr();
		  setNullToPic();
	    }else if(framTime.document.all("issueStateShow").innerHTML=="���ڿ���"){
		  var temp = framTime.document.all("remainsecond").innerHTML;
		  var aryTemp = temp.split(":");
		  //alert(temp);
		  if(aryTemp[0] == "00" && parseInt(aryTemp[1])<=30){

	      // if(framTime.document.all("remainsecond").innerHTML=="00:30"){
		    //document.location.href="/servlet/startwinmovie";
			//�õ�������Ϣ
			DwrBean.getExbCurrentZjNum(zjNumCallBack);
						
			//ˢ����ҳ�Ŀ����б�
			parent.document.frames['frawinnumshow'].location.href = "/exb/exbPublicInfo.do?method=showExbZjNum&indexFlag=indexPage";
			//alert(parent.document.frames['frawinnumshow'].location.href);
		  }
		  /*
		  if(document.all("isgetwinval").value=="true"){
		      document.all("isgetwinval").value="false";
		      document.all("issuewinresult").innerHTML="0000000000";
		      document.all("spadewin").innerHTML="0";
		      document.all("heartwin").innerHTML="0";
		      document.all("clubwin").innerHTML="0";
		      document.all("diamondwin").innerHTML="0";
		      prizeBackMovie();//�õ��н���Ϣ��������������
		  }
		  if(backTimeOut==0){//��backTimeOutΪ0ʱ
		    backTimeOut=10;//�ָ�backTimeOut
		    clearTimeout(backTimeOutMark);//�ر�prizeBackMovie.
		    prizeMovie();//����prizeMovie
		  }*/

	    }
    }
    winTimeRunMark=setTimeout("winTimeRun()",10000);
}
//��û�п�ʼ��ʱ�����ö����ϵ�ֵΪ��
function setNullToPic(){
	
	$("#issuewinresult").attr("innerHTML","");
	$("#div1").attr("innerHTML","");
	$("#div2").attr("innerHTML","");
	$("#div3").attr("innerHTML","");
	$("#div4").attr("innerHTML","");
	$("#div5").attr("innerHTML","");
	$("#div6").attr("innerHTML","");
	$("#div7").attr("innerHTML","");
	$("#div8").attr("innerHTML","");
	
	
}
//�Կ�����Ϣ�Ļص�����
function zjNumCallBack(obj){
	//alert(data.wan);
	$("#issuewinresult").attr("innerHTML",obj.issueNum);
	$("#div1").attr("innerHTML",obj.position1 <10 ? ("0"+obj.position1) : obj.position1);
	$("#div2").attr("innerHTML",obj.position2 <10 ? ("0"+obj.position2) : obj.position2);
	$("#div3").attr("innerHTML",obj.position3 <10 ? ("0"+obj.position3) : obj.position3);
	$("#div4").attr("innerHTML",obj.position4 <10 ? ("0"+obj.position4) : obj.position4);
	$("#div5").attr("innerHTML",obj.position5 <10 ? ("0"+obj.position5) : obj.position5);
	$("#div6").attr("innerHTML",obj.position6 <10 ? ("0"+obj.position6) : obj.position6);
	$("#div7").attr("innerHTML",obj.position7 <10 ? ("0"+obj.position7) : obj.position7);
	$("#div8").attr("innerHTML",obj.position8 <10 ? ("0"+obj.position8) : obj.position8);
}
