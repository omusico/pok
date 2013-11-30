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
  arrReal[0]="����:�Ӻڡ��졢÷�������ֻ�ɫ��������ѡ1�ֻ�ɫ�������Ͷע��<br>��ʽ������һ��ѡ������ɫ��ÿ����ɫ��ѡ������롣��:(3,4|3,4|3|4)��ϵͳ�ᰴ�ա���ѡһ�������ж�Ϊ6ע��<br>��ʽ:����һ��ѡ������ɫ����ÿ����ɫֻ��ѡһ�����롣��:(3|4|2|J)��ϵͳ�ᰴ�ա���ѡһ�������ж�Ϊ4ע��";
  arrReal[1]="����:�Ӻڡ��졢÷�������ֻ�ɫ��������ѡ2�ֻ�ɫ�������Ͷע��<br>��ʽ������һ��ѡ������ɫ��ÿ����ɫ��ѡ������롣��:(3,4|3,4|3|4)��ϵͳ�ᰴ�ա���ѡ���������ж�Ϊ13ע��<br>��ʽ:����һ��ѡ������ɫ����ÿ����ɫֻ��ѡһ�����롣��:(3|4|2|J)��ϵͳ�ᰴ�ա���ѡ���������ж�Ϊ6ע��";
  arrReal[2]="����:�Ӻڡ��졢÷�������ֻ�ɫ��������ѡ3�ֻ�ɫ�������Ͷע������[��ѡ����3]��[��ѡ����2]<br>��ʽ������һ��ѡ������ɫ��ÿ����ɫ��ѡ������롣��:(3,4|3,4|3|4)��ϵͳ�ᰴ�ա���ѡ���������ж�Ϊ12ע��<br>��ʽ:����һ��ѡ������ɫ����ÿ����ɫֻ��ѡһ�����롣��:(3|4|2|J)��ϵͳ�ᰴ�ա���ѡ���������ж�Ϊ4ע��";
  arrReal[3]="����:�Ӻڡ��졢÷�������ֻ�ɫ������ѡ��4�ֻ�ɫ�������Ͷע������[ѡ��ֱѡ��4]��[ѡ��ֱѡ��3<br>��ʽ������һ��ѡ������ɫ��ÿ����ɫ��ѡ������롣��:(3,4|3,4|3|4)��ϵͳ�ᰴ�ա�ѡ��ֱѡ�������ж�Ϊ4ע��<br>��ʽ:����һ��ѡ������ɫ����ÿ����ɫֻ��ѡһ�����롣��:(3|4|2|J)��ϵͳ�ᰴ�ա�ѡ��ֱѡ�������ж�Ϊ1ע��";
  arrReal[4]="����:�����ѡ4��������3��������ͬ����2223������4�ֲ�ͬ�����з�ʽ����Ϊѡ����ѡ4��";
  arrReal[5]="����:һ�Ų�Ʊ�а������֣������֣����ϵ�Ͷע������Ϊ���Ͷע��";
  var strReal=mapTran(strType,arrMark,arrReal);
  return strReal;
}

function getDescForGrMode(strType){
  var arrMark=new Array("gr4","gr6","gr12","gr24");
  var arrReal=new Array("","","","");
  arrReal[0]="����:�����ѡ4��������3��������ͬ����2223������4�ֲ�ͬ�����з�ʽ����Ϊѡ����ѡ4��";
  arrReal[1]="����:�����ѡ4��������������ͬ����2323������6�ֲ�ͬ�����з�ʽ����Ϊѡ����ѡ6��";
  arrReal[2]="����:�����ѡ4����������2����ͬ����2324��������2�����벻ͬ����12�ֲ�ͬ�����з�ʽ����Ϊѡ����ѡ12��";
  arrReal[3]="����:�����ѡ���������ͬ����2345��������24�ֲ�ͬ�����з�ʽ����Ͷע��ʽ��Ϊѡ����ѡ24��";

  var strReal=mapTran(strType,arrMark,arrReal);
  return strReal;
}