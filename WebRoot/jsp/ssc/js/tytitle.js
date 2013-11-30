var fraPlay;
var fraMode;

function changeType(strMode,strPlay,mode,type){
  var objOne=document.all("one");
  var objTwo=document.all("two");
  var objThr=document.all("three");
  var objFour=document.all("four");
  var objFourGr=document.all("fourgr");
  var objGrMulti=document.all("grmulti");
  var objGrDanTuo=document.all("grdantuo");
  var objComp=document.all("compound");
  var arrBut=new Array(objOne,objTwo,objThr,objFour,objFourGr,objGrMulti,objGrDanTuo,objComp);
  comExch(arrBut,type,"typechosen","typeready");
  
  parent.document.all("typechosenmark").value=type.id;
  parent.document.all("modechosenmark").value=mode;
  parent.document.all("expl").innerHTML=getDescForPlay(type.id);
  
  parent.document.frames['fraplay'].location.href="/lottpok/play/"+strPlay+".jsp";
  parent.document.frames['framodtit'].location.href="/lottpok/mode/"+strMode+".jsp";
  parent.document.frames['framulissue'].location.href="/servlet/servmulissue?play=poker";
}

function chModeCom(type){
  var objMul=document.all("multi");
  var objSing=document.all("sing");
  var arrBut=new Array(objMul,objSing);
  comExch(arrBut,type,"modechosen","modeready");
  
  parent.document.all("modechosenmark").value=type.id;
  parent.document.frames['fraplay'].location.href="/lottpok/play/com.jsp";
  parent.document.frames['framulissue'].location.href="/servlet/servmulissue?play=poker";
  
}

function chModeComTwo(type,strMode){
  var objMul=document.all("multi");
  var objSing=document.all("sing");
  var objTotal=document.all("total");
  var objDiff=document.all("diff");
  var arrBut=new Array(objMul,objSing,objTotal,objDiff);
  comExch(arrBut,type,"modechosen","modeready");
  
  parent.document.all("modechosenmark").value=type.id;
  parent.document.frames['fraplay'].location.href="/lottpok/play/"+strMode+".jsp";
  parent.document.frames['framulissue'].location.href="/servlet/servmulissue?play=poker";
}

function chModeGr(type){
  var objGr4=document.all("gr4");
  var objGr6=document.all("gr6");
  var objGr12=document.all("gr12");
  var objGr24=document.all("gr24");
  var arrBut=new Array(objGr4,objGr6,objGr12,objGr24);
  comExch(arrBut,type,"modechosen","modeready");
  
  parent.document.all("modechosenmark").value=type.id;
  
  var strPlayType=parent.document.all("typechosenmark").value;
  if(strPlayType=="fourgr"){
    parent.document.all("expl").innerHTML=getDescForGrMode(type.id);
    parent.document.frames['fraplay'].location.href="/lottpok/play/gro.jsp";
  }
  if(strPlayType=="grmulti"){
    parent.document.frames['fraplay'].location.href="/lottpok/play/grmul.jsp";
  }
  if(strPlayType=="grdantuo"){
    parent.document.frames['fraplay'].location.href="/lottpok/play/grdt.jsp";
  }
  parent.document.frames['framulissue'].location.href="/servlet/servmulissue?play=poker";
}

function comExch(arr,obj,strCho,strRe){
  for(var i=0;i<arr.length;i++){
    arr[i].className=strRe;
    arr[i].style.color="black";
  }
  obj.className=strCho;
  obj.style.color="white";
}

function getDescForPlay(strType){
  var arrMark=new Array("one","two","three","four","fourgr","compound");
  var arrReal=new Array("","","","","","");
  arrReal[0]="规则:从黑、红、梅、方四种花色号码中任选1种花色号码进行投注。<br>复式：可以一次选择多个花色，每个花色可选多个号码。如:(3,4|3,4|3|4)，系统会按照“任选一”规则判断为6注。<br>单式:可以一次选择多个花色，但每个花色只可选一个号码。如:(3|4|2|J)，系统会按照“任选一”规则判断为4注。";
  arrReal[1]="规则:从黑、红、梅、方四种花色号码中任选2种花色号码进行投注。<br>复式：可以一次选择多个花色，每个花色可选多个号码。如:(3,4|3,4|3|4)，系统会按照“任选二”规则判断为13注。<br>单式:可以一次选择多个花色，但每个花色只可选一个号码。如:(3|4|2|J)，系统会按照“任选二”规则判断为6注。";
  arrReal[2]="规则:从黑、红、梅、方四种花色号码中任选3种花色号码进行投注。包括[任选三中3]和[任选三中2]<br>复式：可以一次选择多个花色，每个花色可选多个号码。如:(3,4|3,4|3|4)，系统会按照“任选三”规则判断为12注。<br>单式:可以一次选择多个花色，但每个花色只可选一个号码。如:(3|4|2|J)，系统会按照“任选三”规则判断为4注。";
  arrReal[3]="规则:从黑、红、梅、方四种花色号码中选择4种花色号码进行投注。包括[选四直选中4]和[选四直选中3<br>复式：可以一次选择多个花色，每个花色可选多个号码。如:(3,4|3,4|3|4)，系统会按照“选四直选”规则判断为4注。<br>单式:可以一次选择多个花色，但每个花色只可选一个号码。如:(3|4|2|J)，系统会按照“选四直选”规则判断为1注。";
  arrReal[4]="规则:如果所选4号码中有3个号码相同（如2223），有4种不同的排列方式，称为选四组选4。";
  arrReal[5]="规则:一张彩票中包含两种（含两种）以上的投注方法称为组合投注。";
  var strReal=mapTran(strType,arrMark,arrReal);
  return strReal;
}

function getDescForGrMode(strType){
  var arrMark=new Array("gr4","gr6","gr12","gr24");
  var arrReal=new Array("","","","");
  arrReal[0]="规则:如果所选4号码中有3个号码相同（如2223），有4种不同的排列方式，称为选四组选4。";
  arrReal[1]="规则:如果所选4个号码中两两相同（如2323），有6种不同的排列方式，称为选四组选6。";
  arrReal[2]="规则:如果所选4个号码中有2个相同（如2324），其余2个号码不同，有12种不同的排列方式，称为选四组选12。";
  arrReal[3]="规则:如果所选号码各不相同（如2345），共有24种不同的排列方式，此投注方式称为选四组选24。";

  var strReal=mapTran(strType,arrMark,arrReal);
  return strReal;
}