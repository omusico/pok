var fraPlay;
var fraMode;
/**
  *modePage����Ӧ��ʽ��ҳ��
  *playPage:��Ӧ�淨Ͷע��ҳ��
  *mode:��Ӧ��ʽ��Ӧ��ֵ
  *type:
  */
function changeType(modePage,playPage,mode,type){

   var objXuanyi=document.all("xuanyi");
  var objXuaner=document.all("xuaner");
  var objXuansan=document.all("xuansan");
  var objXuansi=document.all("xuansi");
  var objXuanwu=document.all("xuanwu");
  var objXuan6=document.all("xuan6");
  var objXuan7=document.all("xuan7");
  var objXuan8=document.all("xuan8");
  
//  var arrBut=new Array(objWuxingTx,objWuxin,objSixing,objSanxingZhixuan,objSanxingZuxuan,objErxing,objErxingZuxuan,objYixing,objZuhe,objDaxiaoDs);
  var arrBut=new Array(objXuanyi,objXuaner,objXuansan,objXuansi,objXuanwu,objXuan6,objXuan7,objXuan8);
  comExch(arrBut,type,"typechosen","typeready");
  
  parent.document.all("typechosenmark").value=type.id;
  parent.document.all("modechosenmark").value=mode;
  parent.document.all("expl").innerHTML=getDescForPlay(type.id);
  
  var tempPlayPage="/jsp/sxw/play/"+playPage+".jsp";
  var tempModePage="/jsp/sxw/mode/"+modePage+".jsp";
  //var tempIssueTimesPage="/servlet/servmulissue?play=poker";

//alert("tempPlayPage="+tempPlayPage);
//alert("tempModePage="+tempModePage);
//alert(parent.document.frames['framodtit'].location.href);
  parent.document.frames['frameTouzhu'].location.href=tempPlayPage;
  parent.document.frames['framodtit'].location.href=tempModePage;
  parent.document.frames['frameZhuitou'].location.href = parent.document.frames['frameZhuitou'].location.href;
}
//��ѡ��ť
function chMode(playPage){
  fraPlay=parent.document.frames['frameTouzhu'];
  
  var tempPlayPage="/jsp/sxw/play/"+playPage+".jsp";

  fraPlay.location.href=tempPlayPage;  
  parent.document.frames['frameZhuitou'].location.href = parent.document.frames['frameZhuitou'].location.href;

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



function comExch(arr,obj,strCho,strRe){
  for(var i=0;i<arr.length;i++){
    arr[i].className=strRe;
    arr[i].style.color="black";
  }
  obj.className=strCho;
  obj.style.color="white";
}

function getDescForPlay(strType){
    var arrMark=new Array("wuxingTx","wuxing","sixing","sanxingZhixuan","sanxingZuxuan","erxingZhixuan","erxingZuxuan","yixing","zuhe","daxiaoDs");
  var arrReal=new Array("","","","","","","","","","");

	arrReal[0]="��00000��99999��ѡ��һ����λ��ΪͶע�������Ͷע���������̶�������������ÿעͶע����ɼ��м�ã��ر��轱���⣩����ѡ�����λ�������뿪����������ȫ����ͬ��˳��һ�£����еá�����ͨѡ��һ�Ƚ�������20000Ԫ����ѡ�����λ���ֵ��׻���β������λ�뿪��������׻���β������λ��������ͬ��˳��һ�£����еá�����ͨѡ�����Ƚ�������200Ԫ����ѡ�����λ���ֵ��׻���β������λ�뿪��������׻���β������λ��������ͬ��˳��һ�£����еá�����ͨѡ�����Ƚ�������20Ԫ",
	arrReal[1]="��00000��99999��ѡ��һ����λ��ΪͶע�������Ͷע����עͶע�����5�������뵱�ڿ��������5λ���밴λȫ����������н�����ע�̶���100000Ԫ",
	arrReal[2]="����ֱѡ', '�Կ����ŵĺ���λ������λ��ʮλ����λ������Ͷע����עͶע�����뵱���н������������3λ���밴λ�������λ+ʮλ+��λ�����н�����ע�̶���1000Ԫ",
	arrReal[3]="������ѡ', '�Կ����ŵĺ���λ������λ��ʮλ����λ������Ͷע���֡���ѡ�����͡���ѡ����������ѡ���������ڿ�������ĺ���λ����������λ������ͬ����Ͷע�����뿪�������������ͬ��˳���ޣ����н�����ע�̶���320Ԫ������ѡ���������ڿ�������ĺ���λ������ͬ����עͶע��������������뵱�ڿ�������ĺ���λȫ����ͬ��˳���ޣ����н�����ע�̶���160Ԫ",
	arrReal[4]="����ֱѡ', '��ѡ�ĺ����뵱�ڿ�������ĺ��λ��ͬ��˳��һ�£����н�����ע�̶���100Ԫ��",
	arrReal[5]="һ��', '�Կ����ŵ����һλ����Ͷע����עͶע�����뵱���н�����ĸ�λ���밴λ������н�����ע�̶���10Ԫ",
	arrReal[6]="���', '�֡�������ϡ�����������ϡ�����������ϡ�����������ϣ���һ�ǡ����ǡ����ǡ��������Ͷע��һ�Ų�Ʊ�ϣ���ʵ���еý��ȣ��ϲ����㽱�𡣽������÷ֱ�����һ�ǡ����ǡ����ǡ������н����������������ʾ�����繺��4+5+6+7+8����Ʊ��8Ԫ��������4ע��45678(����)��678(����)��78(����)��8(һ��)����",
	arrReal[7]="��С��˫', 'ָ�� ����λ���͡�ʮλ��������λ�õġ���С����˫���淨������10����Ȼ�������󡱡���С���򡰵�������˫�����ʷ�Ϊ���飬0-4ΪС�ţ�5-9Ϊ��ţ�0��2��4��6��8Ϊ˫�ţ�1��3��5��7��9Ϊ���š�Ͷע�߿ɶԡ���λ��ʮλ�������ֽ��С���С����˫����ָ��Ͷע��Ͷע��Ͷע�ĺ���λ�á������뿪������λ�á�������ͬ���н�����ע�̶���4Ԫ",
	arrReal[8]="������ѡ', '��ѡ�ĺ����뵱�ڿ�������ĺ���λ��ͬ��˳���ޣ����н�����ע�̶���50Ԫ��",
	arrReal[9]="������ѡ', '��ѡ�ĺ����뵱�ڿ�������ĺ���λ��ͬ��˳���ޣ����н�����ע�̶���50Ԫ��"

	 var strReal=mapTran(strType,arrMark,arrReal);
     return strReal;


}

