function switchMulIss(){
  var framPlay=parent.document.frames['fraplay'];
  var issueLength=document.all("issuelength").value;
  var objStartMulIssCheck=document.all("chestmuliss");
  if(objStartMulIssCheck.checked==true){//点选多期功能后
    switButEnab(true);
    document.all("curisswagnum").innerHTML=framPlay.document.all("showtotalwager").innerHTML;
    document.all("curissmon").innerHTML=parseInt(document.all("curisswagnum").innerHTML)*2;
  }else{//取消后
    switButEnab(false);
    document.all("curisswagnum").innerHTML=0;
    document.all("curissmon").innerHTML=0;
  }
  for (var i=0;i<issueLength;i++){
    var str="isscheck"+i;
    var strtext="timestext"+i;
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
      setNumMon(0,i);
    }
  }
  
  calMulIssWagMon();
}

function switButEnab(stat){
  var framPlay=parent.document.frames['fraplay'];
  var butPlus=framPlay.document.all("butplus");
  var butRemove=framPlay.document.all("butremove");
  var butEmpty=framPlay.document.all("butempty");
  var ranOne=framPlay.document.all("randone");
  var ranFive=framPlay.document.all("randfive");
  var ranTen=framPlay.document.all("randten");
  
  var objPagemark=parent.document.all("pagemark");
  var objPlaytype=framPlay.document.all("playtype");
  var objPlaymode=framPlay.document.all("playmode");
  
  if(ranOne!=null && ranFive!=null && ranTen!=null && objPlaymode.value!="复式"){
    ranOne.disabled=stat;
    ranFive.disabled=stat;
    ranTen.disabled=stat;
  }
  if(butPlus!=null &&  butRemove!=null &&  butEmpty!=null){
    butPlus.disabled=stat;
    butRemove.disabled=stat;
    butEmpty.disabled=stat;
  }

  if(objPagemark.value=="indexmain"){
	  //以下为扑克部分
	  if(objPlaytype.value=="组合投注"){
	    var arrSpade=framPlay.document.all("spade");
	    var arrHeart=framPlay.document.all("heart");
	    var arrClub=framPlay.document.all("club");
	    var arrDiamond=framPlay.document.all("diamond");
	    for(i=0;i<arrSpade.length;i++){
	      arrSpade[i].disabled=stat;
	      arrHeart[i].disabled=stat;
	      arrClub[i].disabled=stat;
	      arrDiamond[i].disabled=stat;
	    }
	  }
  }else if(objPagemark.value=="laugh"){
	  //以下为时时乐部分
	  if(objPlaytype.value=="单选三"){
	    if(objPlaymode.value=="和值" ||objPlaymode.value=="包号"){
		    var arrHundred=framPlay.document.all("hundred");
		    for(var j=0;j<arrHundred.length;j++){
		      arrHundred[j].disabled=stat;
		    }
	    }
	  }
	  if(objPlaytype.value=="组6" || objPlaytype.value=="组3"){
	    if(objPlaymode.value=="和值"){
		    var arrHundred=framPlay.document.all("hundred");
		    for(var j=0;j<arrHundred.length;j++){
		      arrHundred[j].disabled=stat;
		    }
	    }
	  }
  }
  
}

function plusTimes(timesTextIDTemp,ind){
  var objTimesText=document.all(timesTextIDTemp);
  if(parseInt(objTimesText.value)>0){
    objTimesText.value=parseInt(objTimesText.value)+1;
    var timesText=objTimesText.value;
    setNumMon(timesText,ind);
  }
  calMulIssWagMon();
}

function minusTimes(timesTextIDTemp,ind){
  var objTimesText=document.all(timesTextIDTemp);
  if(parseInt(objTimesText.value)>1){
    objTimesText.value=parseInt(objTimesText.value)-1;  
    var timesText=objTimesText.value;
    setNumMon(timesText,ind);
  }
  calMulIssWagMon();
}
//为每一期显示注数，金额
function setNumMon(times,ind){
  var curIssWagNum=document.all("curisswagnum").innerHTML;
  var num="wagnumid"+""+ind;
  var mon="wagmonid"+""+ind;
  document.all(num).innerHTML=curIssWagNum*times;
  document.all(mon).innerHTML=curIssWagNum*times*2;
}
//求多期总注数并显示
function calMulIssWagMon(){
  var totWag=0;
  var issLen=document.all("issuelength").value;
  for(var i=0;i<issLen;i++){
    var num="wagnumid"+""+i;
    totWag=totWag+parseInt(document.all(num).innerHTML);
  }
  document.all("mulisswagnum").innerHTML=totWag;
  document.all("mulissmon").innerHTML=totWag*2;
}

//设置enable每一期
function switchTimesText(iTemp){
  var str="timestext"+iTemp;
  if(document.all(str).value==0){
    document.all(str).value=1;
    setNumMon(1,iTemp);
  }else{
    document.all(str).value=0;
    setNumMon(0,iTemp);
  }
  calMulIssWagMon();
}

