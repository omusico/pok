var fraPlay;
var fraMode;
/**
  *modePage����Ӧ��ʽ��ҳ��
  *playPage:��Ӧ�淨Ͷע��ҳ��
  *mode:��Ӧ��ʽ��Ӧ��ֵ
  *type:
  */
function changeType(modePage,playPage,mode,type){

   var objWuxingTx=document.all("wuxingTx");
  var objWuxin=document.all("wuxing");
  var objWuxingZuxuan=document.all("wuxingZuxuan");
  var objSixing=document.all("sixing");
  var objSixingZuxuan=document.all("sixingZuxuan");
  var objSanxingZhixuan=document.all("sanxingZhixuan");
  var objSanxingZuxuan=document.all("sanxingZuxuan");
  var objErxing=document.all("erxing");
  //var objErxingZuxuan=document.all("erxingZuxuan");��ʱ����,ֱ���ö���
  var objYixing=document.all("yixing");
  var objZuhe=document.all("zuhe");
  var objDaxiaoDs=document.all("daxiaoDs");
  var objRenxuan=document.all("renxuan");
//  var arrBut=new Array(objWuxingTx,objWuxin,objSixing,objSanxingZhixuan,objSanxingZuxuan,objErxing,objErxingZuxuan,objYixing,objZuhe,objDaxiaoDs);
  var arrBut=new Array(objWuxingTx,objWuxin,objWuxingZuxuan,objSixing,objSixingZuxuan,objSanxingZhixuan,objSanxingZuxuan,objErxing,objYixing,objZuhe,objDaxiaoDs,objRenxuan);
  comExch(arrBut,type,"typechosen","typeready");
  
  parent.document.all("typechosenmark").value=type.id;
  parent.document.all("modechosenmark").value=mode;
  parent.document.all("expl").innerHTML=getDescForPlay(type.id);
  
  var tempPlayPage="/jsp/ssc/play/"+playPage+".jsp";
  var tempModePage="/jsp/ssc/mode/"+modePage+".jsp";
  var tempIssueTimesPage="/servlet/servmulissue?play=poker";

//alert("tempPlayPage="+tempPlayPage);
//alert("tempModePage="+tempModePage);
//alert(parent.document.frames['framodtit'].location.href);
  parent.document.frames['frameTouzhu'].location.href=tempPlayPage;
  parent.document.frames['framodtit'].location.href=tempModePage;
}
//��ѡ��ť
function chMode(playPage){
  fraPlay=parent.document.frames['frameTouzhu'];
  
  var tempPlayPage="/jsp/ssc/play/"+playPage+".jsp";

  fraPlay.location.href=tempPlayPage;  
}
function chModeCom(type){
  var objMul=document.all("multi");
  var objSing=document.all("sing");
  var arrBut=new Array(objMul,objSing);
  comExch(arrBut,type,"modechosen","modeready");
  
  parent.document.all("modechosenmark").value=type.id;
  parent.document.frames['frameTouzhu'].location.href="/lottpok/play/com.jsp";
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
  parent.document.frames['frameTouzhu'].location.href="/lottpok/play/"+strMode+".jsp";
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
    parent.document.frames["frameTouzhu"].location.href="/lottpok/play/gro.jsp";
  }
  if(strPlayType=="grmulti"){
    parent.document.frames["frameTouzhu"].location.href="/lottpok/play/grmul.jsp";
  }
  if(strPlayType=="grdantuo"){
    parent.document.frames["frameTouzhu"].location.href="/lottpok/play/grdt.jsp";
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
    var arrMark=new Array("wuxingTx","wuxing","sixing","sanxingZhixuan","sanxingZuxuan","erxingZhixuan","yixing","zuhe","daxiaoDs");
  var arrReal=new Array("","","","","","","","","","");

	arrReal[0]="��00000��99999��ѡ��һ����λ��ΪͶע�������Ͷע���������̶�������������ÿעͶע����ɼ��м�ã��ر��轱���⣩����ѡ�����λ�������뿪����������ȫ����ͬ��˳��һ�£����еá�����ͨѡ��һ�Ƚ�������20000Ԫ����ѡ�����λ���ֵ��׻���β������λ�뿪��������׻���β������λ��������ͬ��˳��һ�£����еá�����ͨѡ�����Ƚ�������200Ԫ����ѡ�����λ���ֵ��׻���β������λ�뿪��������׻���β������λ��������ͬ��˳��һ�£����еá�����ͨѡ�����Ƚ�������20Ԫ",
	arrReal[1]="��00000��99999��ѡ��һ����λ��ΪͶע�������Ͷע����עͶע�����5�������뵱�ڿ��������5λ���밴λȫ����������н�����ע�̶���100000Ԫ",
	arrReal[2]="";
	arrReal[3]="����ֱѡ', '�Կ����ŵĺ���λ������λ��ʮλ����λ������Ͷע����עͶע�����뵱���н������������3λ���밴λ�������λ+ʮλ+��λ�����н�����ע�̶���1000Ԫ",
	arrReal[4]="������ѡ', '�Կ����ŵĺ���λ������λ��ʮλ����λ������Ͷע���֡���ѡ�����͡���ѡ����������ѡ���������ڿ�������ĺ���λ����������λ������ͬ����Ͷע�����뿪�������������ͬ��˳���ޣ����н�����ע�̶���320Ԫ������ѡ���������ڿ�������ĺ���λ������ͬ����עͶע��������������뵱�ڿ�������ĺ���λȫ����ͬ��˳���ޣ����н�����ע�̶���160Ԫ",
	arrReal[5]="����ֱѡ', '��ѡ�ĺ����뵱�ڿ�������ĺ��λ��ͬ��˳��һ�£����н�����ע�̶���100Ԫ��",
	arrReal[6]="һ��', '�Կ����ŵ����һλ����Ͷע����עͶע�����뵱���н�����ĸ�λ���밴λ������н�����ע�̶���10Ԫ",
	arrReal[7]="���', '�֡�������ϡ�����������ϡ�����������ϡ�����������ϣ���һ�ǡ����ǡ����ǡ��������Ͷע��һ�Ų�Ʊ�ϣ���ʵ���еý��ȣ��ϲ����㽱�𡣽������÷ֱ�����һ�ǡ����ǡ����ǡ������н����������������ʾ�����繺��4+5+6+7+8����Ʊ��8Ԫ��������4ע��45678(����)��678(����)��78(����)��8(һ��)����",
	arrReal[8]="��С��˫', 'ָ�� ����λ���͡�ʮλ��������λ�õġ���С����˫���淨������10����Ȼ�������󡱡���С���򡰵�������˫�����ʷ�Ϊ���飬0-4ΪС�ţ�5-9Ϊ��ţ�0��2��4��6��8Ϊ˫�ţ�1��3��5��7��9Ϊ���š�Ͷע�߿ɶԡ���λ��ʮλ�������ֽ��С���С����˫����ָ��Ͷע��Ͷע��Ͷע�ĺ���λ�á������뿪������λ�á�������ͬ���н�����ע�̶���4Ԫ",
	arrReal[9]="������ѡ', '��ѡ�ĺ����뵱�ڿ�������ĺ���λ��ͬ��˳���ޣ����н�����ע�̶���50Ԫ��",
	arrReal[10]="������ѡ', '��ѡ�ĺ����뵱�ڿ�������ĺ���λ��ͬ��˳���ޣ����н�����ע�̶���50Ԫ��"

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

/*
ÿ������ѡ1���룬��������ͨ�ԣ�����н����ᣬ��20000Ԫ
ÿ������ѡ1�����룬����100000Ԫ��
��00000~99999��ѡһ����λ��Ϊһ��Ͷע���룬����100000Ԫ��

ÿ������ѡ1�����룬����1000Ԫ��
��ѡM��M��3����������ϣ�Ͷע���벻�����Ӻͱ��ӣ�������1000Ԫ��
��000~999��ѡһ����λ��Ϊһ��Ͷע���룬����1000Ԫ��

��0~9��10����������ѡ2�������ϵĺ��룬����320Ԫ��
��0~9��10����������ѡ3�������ϵĺ��룬����160Ԫ��
��ѡ1~2����Ͷע�������뿪����Ӧ������1000Ԫ / ����320Ԫ / ����160Ԫ��
����ѡ�����Ͷע�������뿪����Ӧ������1000Ԫ / ����320Ԫ / ����160Ԫ��
����ѡ��һ���Ǳ��ӵ���λ��Ϊһ��Ͷע���룬����320Ԫ / ����160Ԫ��


ÿ������ѡ1�����룬����100Ԫ
��00~99��ѡһ����λ��Ϊһ��Ͷע���룬����100Ԫ��
��0~9����ѡ��2�������ϵĺ��룬����50Ԫ������ʽ���������� ��
��00~99��ѡһ����λ��Ϊһ��Ͷע���룬����50Ԫ��
����ѡ�����Ͷע������50Ԫ�����ӽ���100Ԫ��

��0~9����ѡ��1������Ϊһ��Ͷע���룬����10Ԫ��


ÿ������ѡ1�����룬��ע��߽���101110Ԫ��
ÿ������ѡ1�����룬��ע��߽���1110Ԫ��
ÿ������ѡ1�����룬��ע��߽���110Ԫ��

�ֱ�Ӹ���ʮλ����ѡһ���������һעͶע���룬����4Ԫ
*/