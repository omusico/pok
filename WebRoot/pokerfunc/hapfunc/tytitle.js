var fraPlay;
var fraMode;
function changeType(strMode,strPlay,type){
  fraPlay=parent.document.frames['fraplay'];
  fraMode=parent.document.frames['framodtit'];
  fraPlay.location.href="/lott/hap/play/"+strPlay+".jsp";
  fraMode.location.href="/lott/hap/mode/"+strMode+".jsp";
  var objCom=document.all("com");
  var objGrSix=document.all("grsix");
  //var objGrThr=document.all("grthr");
  //var objGrSin=document.all("grsin");
  //var objFrOn=document.all("fron");
  //var objFrTw=document.all("frtw");
  var arrBut=new Array(objCom,objGrSix);
  comExch(arrBut,type,"typechosen","typeready");
}

function chMode(strPlay){
  fraPlay=parent.document.frames['fraplay'];
  fraPlay.location.href="/lott/hap/play/"+strPlay+".jsp";  
}

function comExch(arr,obj,strCho,strRe){
  for(var i=0;i<arr.length;i++){
    arr[i].className=strRe;
    arr[i].style.color="black";
  }
  obj.className=strCho;
  obj.style.color="white";
}