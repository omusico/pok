var fraPlay;
var fraMode;
/**
  *modePage：相应方式的页面
  *playPage:相应玩法投注的页面
  *mode:相应方式对应的值
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
//单选按钮
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

	arrReal[0]="在00000－99999中选择一个五位数为投注号码进行投注，设三个固定奖奖级。当期每注投注号码可兼中兼得（特别设奖除外）。若选择的五位数号码与开奖号码数字全部相同且顺序一致，则中得“五星通选”一等奖，奖金20000元；若选择的五位数字的首或者尾连续三位与开奖号码的首或者尾连续三位数数字相同且顺序一致，则中得“五星通选”二等奖，奖金200元；若选择的五位数字的首或者尾连续两位与开奖号码的首或者尾连续两位数数字相同且顺序一致，则中得“五星通选”三等奖，奖金20元",
	arrReal[1]="在00000－99999中选择一个五位数为投注号码进行投注，单注投注号码的5个号码与当期开奖号码的5位号码按位全部相符，即中奖；单注固定奖100000元",
	arrReal[2]="三星直选', '对开奖号的后三位（即百位、十位、个位）进行投注，单注投注号码与当期中奖号码的连续后3位号码按位相符（百位+十位+个位）即中奖；单注固定奖1000元",
	arrReal[3]="三星组选', '对开奖号的后三位（即百位、十位、个位）进行投注，分“组选三”和“组选六”。“组选三”，当期开奖号码的后三位中有任意两位数字相同，且投注号码与开奖号码的数字相同（顺序不限）即中奖；单注固定奖320元。“组选六”，当期开奖号码的后三位各不相同，单注投注号码的三个数字与当期开奖号码的后三位全部相同（顺序不限）即中奖；单注固定奖160元",
	arrReal[4]="二星直选', '所选的号码与当期开奖号码的后二位相同且顺序一致，即中奖；单注固定奖100元。",
	arrReal[5]="一星', '对开奖号的最后一位进行投注，单注投注号码与当期中奖号码的个位号码按位相符即中奖；单注固定奖10元",
	arrReal[6]="组合', '分“五星组合”、“三星组合”、“二星组合”。如五星组合，将一星、二星、三星、五星组合投注在一张彩票上，按实际中得奖等，合并计算奖金。奖金设置分别适用一星、二星、三星、五星中奖条件。如五星组合示例，如购买：4+5+6+7+8，该票共8元，由以下4注：45678(五星)、678(三星)、78(二星)、8(一星)构成",
	arrReal[7]="大小单双', '指猜 “个位”和“十位”这两个位置的“大小、单双”玩法，即把10个自然数按“大”、“小”或“单”、“双”性质分为两组，0-4为小号，5-9为大号，0、2、4、6、8为双号，1、3、5、7、9为单号。投注者可对“个位、十位”的数字进行“大小、单双”的指定投注。投注者投注的号码位置、性质与开奖号码位置、性质相同即中奖。单注固定奖4元",
	arrReal[8]="二星组选', '所选的号码与当期开奖号码的后两位相同（顺序不限）即中奖；单注固定奖50元。",
	arrReal[9]="二星组选', '所选的号码与当期开奖号码的后两位相同（顺序不限）即中奖；单注固定奖50元。"

	 var strReal=mapTran(strType,arrMark,arrReal);
     return strReal;


}

