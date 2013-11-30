var fraPlay;
var fraMode;
/**
  *modePage：相应方式的页面
  *playPage:相应玩法投注的页面
  *mode:相应方式对应的值
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
  //var objErxingZuxuan=document.all("erxingZuxuan");暂时不用,直接用二星
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
//单选按钮
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

	arrReal[0]="在00000－99999中选择一个五位数为投注号码进行投注，设三个固定奖奖级。当期每注投注号码可兼中兼得（特别设奖除外）。若选择的五位数号码与开奖号码数字全部相同且顺序一致，则中得“五星通选”一等奖，奖金20000元；若选择的五位数字的首或者尾连续三位与开奖号码的首或者尾连续三位数数字相同且顺序一致，则中得“五星通选”二等奖，奖金200元；若选择的五位数字的首或者尾连续两位与开奖号码的首或者尾连续两位数数字相同且顺序一致，则中得“五星通选”三等奖，奖金20元",
	arrReal[1]="在00000－99999中选择一个五位数为投注号码进行投注，单注投注号码的5个号码与当期开奖号码的5位号码按位全部相符，即中奖；单注固定奖100000元",
	arrReal[2]="";
	arrReal[3]="三星直选', '对开奖号的后三位（即百位、十位、个位）进行投注，单注投注号码与当期中奖号码的连续后3位号码按位相符（百位+十位+个位）即中奖；单注固定奖1000元",
	arrReal[4]="三星组选', '对开奖号的后三位（即百位、十位、个位）进行投注，分“组选三”和“组选六”。“组选三”，当期开奖号码的后三位中有任意两位数字相同，且投注号码与开奖号码的数字相同（顺序不限）即中奖；单注固定奖320元。“组选六”，当期开奖号码的后三位各不相同，单注投注号码的三个数字与当期开奖号码的后三位全部相同（顺序不限）即中奖；单注固定奖160元",
	arrReal[5]="二星直选', '所选的号码与当期开奖号码的后二位相同且顺序一致，即中奖；单注固定奖100元。",
	arrReal[6]="一星', '对开奖号的最后一位进行投注，单注投注号码与当期中奖号码的个位号码按位相符即中奖；单注固定奖10元",
	arrReal[7]="组合', '分“五星组合”、“三星组合”、“二星组合”。如五星组合，将一星、二星、三星、五星组合投注在一张彩票上，按实际中得奖等，合并计算奖金。奖金设置分别适用一星、二星、三星、五星中奖条件。如五星组合示例，如购买：4+5+6+7+8，该票共8元，由以下4注：45678(五星)、678(三星)、78(二星)、8(一星)构成",
	arrReal[8]="大小单双', '指猜 “个位”和“十位”这两个位置的“大小、单双”玩法，即把10个自然数按“大”、“小”或“单”、“双”性质分为两组，0-4为小号，5-9为大号，0、2、4、6、8为双号，1、3、5、7、9为单号。投注者可对“个位、十位”的数字进行“大小、单双”的指定投注。投注者投注的号码位置、性质与开奖号码位置、性质相同即中奖。单注固定奖4元",
	arrReal[9]="二星组选', '所选的号码与当期开奖号码的后两位相同（顺序不限）即中奖；单注固定奖50元。",
	arrReal[10]="二星组选', '所选的号码与当期开奖号码的后两位相同（顺序不限）即中奖；单注固定奖50元。"

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

/*
每行至少选1号码，三个奖级通吃，五次中奖机会，大奖20000元
每行至少选1个号码，奖金100000元。
从00000~99999中选一个五位数为一个投注号码，奖金100000元。

每行至少选1个号码，奖金1000元。
任选M（M≥3）个号码组合（投注号码不含对子和豹子），奖金1000元。
从000~999中选一个三位数为一个投注号码，奖金1000元。

从0~9这10个号码里任选2个或以上的号码，奖金320元。
从0~9这10个号码里任选3个或以上的号码，奖金160元。
任选1~2个胆投注，奖金与开奖对应，豹子1000元 / 组三320元 / 组六160元。
任意选择号码投注，奖金与开奖对应。豹子1000元 / 组三320元 / 组六160元。
任意选择一个非豹子的三位数为一个投注号码，组三320元 / 组六160元。


每行至少选1个号码，奖金100元
从00~99中选一个二位数为一个投注号码，奖金100元。
从0~9任意选择2个或以上的号码，奖金50元。（复式不包含对子 ）
从00~99中选一个二位数为一个投注号码，奖金50元。
任意选择号码投注，奖金50元（对子奖金100元）

从0~9任意选择1个号码为一个投注号码，奖金10元。


每行至少选1个号码，单注最高奖金101110元。
每行至少选1个号码，单注最高奖金1110元。
每行至少选1个号码，单注最高奖金110元。

分别从个、十位中任选一种性质组成一注投注号码，奖金4元
*/